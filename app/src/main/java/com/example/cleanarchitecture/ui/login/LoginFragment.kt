package com.example.cleanarchitecture.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bt.presentation.base.extension.hideKeyBoard
import com.bt.presentation.base.extension.setValidation
import com.bt.presentation.base.model.Result
import com.bt.presentation.base.model.RetryCallback
import com.bt.presentation.base.ui.BaseFragment
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        observe()

        viewBinding.callback = object : RetryCallback {

            override fun retry() {
                viewModel.login()
            }
        }

        KeyboardVisibilityEvent.setEventListener(
            activity ?: return,
            viewLifecycleOwner,
            { isShowKeyboard ->
                if (isShowKeyboard) {
                    viewBinding.motionLayoutLogin.transitionToEnd()
                } else {
                    viewBinding.motionLayoutLogin.transitionToStart()
                }
            }
        )
    }

    private fun observe() {
        with(viewModel) {
            usernameValidation.observe(viewLifecycleOwner) {
                viewBinding.layoutUsername.setValidation(it)
            }
            passwordValidation.observe(viewLifecycleOwner) {
                viewBinding.layoutPassword.setValidation(it)
            }
            loginResult.observe(viewLifecycleOwner) { result ->
                this@LoginFragment.hideKeyBoard()

                result.getContentIfNotHandled()?.let {
                    when (it) {
                        is Result.Loading -> {
                            showDialogLoading()
                        }
                        is Result.Success -> {
                            hideDialogLoading()

                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToPopularMovieFragment())
                        }
                        is Result.Error -> {
                            hideDialogLoading()
                        }
                    }
                }
            }
        }
    }

    private fun setupListener() {
        viewBinding.buttonLogin.setOnClickListener {
            viewModel.login()
        }
    }
}
