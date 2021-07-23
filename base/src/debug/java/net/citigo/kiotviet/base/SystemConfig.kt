package com.bt.presentation.base

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.parcel.Parcelize

class SystemConfigBottomSheetDialog : BottomSheetDialogFragment(), ViewPager.OnPageChangeListener {

    companion object {
        const val TAG = "SystemConfigBottomSheetDialog"

        fun newInstance() = SystemConfigBottomSheetDialog()
    }

    private var server: Server? = null
    private var account: Account? = null
    private var listener: ((Account) -> Unit)? = null

    private lateinit var pager: ViewPager
    private lateinit var buttonNavigate: ImageView
    private lateinit var titleConfig: TextView
    private lateinit var textSwitcher: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_system_config, container, false)
        pager = view.findViewById(R.id.pagerConfig)
        buttonNavigate = view.findViewById(R.id.buttonNavigate)
        titleConfig = view.findViewById(R.id.titleConfig)
        textSwitcher = view.findViewById(R.id.textSwitcher)

        val configPagerAdapter = ConfigPagerAdapter(childFragmentManager)

        pager.apply {
            adapter = configPagerAdapter
            addOnPageChangeListener(this@SystemConfigBottomSheetDialog)
        }

        buttonNavigate.setOnClickListener {
            if (pager.currentItem == 0) {
                dismiss()
            }
            if (pager.currentItem == 1) {
                pager.currentItem = 0
            }
        }

        return view
    }

    fun onServerSelected(server: Server) {

        if (this.server == null) {
            this.server = server
        } else {
            this.server = server
            pager.currentItem = 1
        }
    }

    fun onAccountSelected(account: Account) {
        listener?.invoke(account)
        dismiss()
    }

    fun setAccountSelectedListener(listener: ((Account) -> Unit)? = null) {
        this.listener = listener
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        if (position == 0) {
            buttonNavigate.setImageResource(R.drawable.ic_baseline_clear_24)
            textSwitcher.text = "Server"
        }

        if (position == 1) {
            val fragment =
                childFragmentManager.findFragmentByTag("android:switcher:" + R.id.pagerConfig + ":" + pager.currentItem)

            if (fragment is AccountFragment) {
                fragment.setServer(server ?: return)
            }

            buttonNavigate.setImageResource(R.drawable.ic_baseline_arrow_back_24)
            textSwitcher.text = server?.name
        }
    }
}

class ServerFragment : Fragment() {

    companion object {

        fun newInstance() = ServerFragment()
    }

    private lateinit var serverRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        serverRecycler = RecyclerView(requireContext())

        serverRecycler.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        return serverRecycler
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val servers = getServers()

        if (parentFragment is SystemConfigBottomSheetDialog) {
            (parentFragment as SystemConfigBottomSheetDialog).onServerSelected(servers[0])
        }

        val serverAdapter = ServerAdapter(servers) {
            if (parentFragment is SystemConfigBottomSheetDialog) {
                (parentFragment as SystemConfigBottomSheetDialog).onServerSelected(it)
            }
        }

        serverRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = serverAdapter
        }
    }
}

class AccountFragment : Fragment() {

    companion object {

        fun newInstance() = AccountFragment()
    }

    private lateinit var accountRecycler: RecyclerView
    private var accountAdapter: AccountAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountRecycler = RecyclerView(requireContext())

        accountRecycler.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        return accountRecycler
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accountAdapter = AccountAdapter {
            if (parentFragment is SystemConfigBottomSheetDialog) {
                (parentFragment as SystemConfigBottomSheetDialog).onAccountSelected(it)
            }
        }

        accountRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = accountAdapter
        }
    }

    fun setServer(server: Server) {
        accountAdapter?.setAccounts(server.accounts)
    }
}

class ConfigPagerAdapter(
    fm: FragmentManager,
    behavior: Int = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) : FragmentPagerAdapter(fm, behavior) {

    companion object {
        private const val TAB_COUNT = 2
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0) return ServerFragment.newInstance()
        return AccountFragment.newInstance()
    }

    override fun getCount(): Int = TAB_COUNT
}

class ServerAdapter(
    private val servers: List<Server>,
    private val listener: ((Server) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var posSelected = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_checked, parent, false)

        return ServerViewHolder(view)
    }

    override fun getItemCount(): Int = servers.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ServerViewHolder) {
            holder.bindView(servers[position])
        }
    }

    inner class ServerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val checkedTextView: CheckedTextView = itemView.findViewById(android.R.id.text1)
        private var server: Server? = null

        init {
            checkedTextView.setOnClickListener {
                server?.let {
                    listener?.invoke(it)
                }

                val tmp = posSelected
                posSelected = adapterPosition

                notifyItemChanged(tmp)
                notifyItemChanged(posSelected)
            }
        }

        fun bindView(server: Server) {
            this.server = server
            checkedTextView.text = server.name
            checkedTextView.isChecked = posSelected == adapterPosition
        }
    }
}

class AccountAdapter(
    private val listener: ((Account) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var accounts: List<Account> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_checked, parent, false)

        return AccountViewHolder(view, listener)
    }

    override fun getItemCount(): Int = accounts.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AccountViewHolder) {
            holder.bindView(accounts[position])
        }
    }

    fun setAccounts(acc: List<Account>) {
        accounts = acc
        notifyDataSetChanged()
    }

    inner class AccountViewHolder(
        itemView: View,
        private val listener: ((Account) -> Unit)? = null
    ) : RecyclerView.ViewHolder(itemView) {

        private val checkedTextView: CheckedTextView = itemView.findViewById(android.R.id.text1)
        private var account: Account? = null

        init {
            checkedTextView.setOnClickListener {
                account?.let {
                    listener?.invoke(it)
                }
            }
            checkedTextView.setOnLongClickListener {
                Toast.makeText(
                    itemView.context,
                    "${account?.username} - ${account?.password}",
                    Toast.LENGTH_SHORT
                ).show()
                true
            }
        }

        fun bindView(account: Account) {
            this.account = account
            checkedTextView.text = account.retailer
        }
    }
}

fun getServers(): List<Server> = listOf(

)

@Parcelize
data class Server(
    val name: String,
    val endPoint: String,
    val accounts: List<Account>
) : Parcelable

@Parcelize
data class Account(
    val retailer: String,
    val username: String,
    val password: String
) : Parcelable
