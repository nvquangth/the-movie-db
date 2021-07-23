package com.example.cleanarchitecture.detailfeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

//@AndroidEntryPoint
//class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
//    override val viewModel: DetailViewModel by viewModels()
//
//    override val layoutRes: Int = R.layout.fragment_detail
//
//}

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
}