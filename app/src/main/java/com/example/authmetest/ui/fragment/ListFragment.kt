package com.example.authmetest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.authmetest.databinding.FragmentListBinding
import com.example.authmetest.interfaces.OnSelectItemListener
import com.example.authmetest.ui.activity.MainViewModel
import com.example.github_user_sdk.network.json.User
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ListFragment : BaseFragment() {
    private val viewModel: MainViewModel by activityViewModel()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private var needMoreLoading = true //判斷滑到底是否需要繼續加載
    private val adapter = RvUserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initButton()
        initRecycleView()
        initObserve()
        resetList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initButton() {
        binding.refreshLayout.setOnRefreshListener { resetList() }
    }

    private fun initRecycleView() {
        binding.list.adapter = adapter
        adapter.setOnSelectItemListener(object : OnSelectItemListener<User> {
            override fun onClick(select: User) {
                goToUserDetailFragment(select.login?: "")
            }
        })

        //滑動至底監聽，判斷是否要再 query 資料
        binding.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                // RecyclerView.canScrollVertically(1) 的值表示是否能向上滚动，false表示已经滚动到底部
                // RecyclerView.canScrollVertically(-1) 的值表示是否能向下滚动，false表示已经滚动到顶部
                if (needMoreLoading && !recyclerView.canScrollVertically(1))
                    getMoreUserList()
            }
        })
    }

    private fun initObserve() {
        viewModel.userListResult.observe(viewLifecycleOwner) {
            binding.refreshLayout.isRefreshing = false
            needMoreLoading = (it?.size ?: 0) < 100
            adapter.setData(it?.toMutableList(), needMoreLoading)
        }
    }

    private fun resetList() {
        adapter.setData(null, false) //清空資料
        viewModel.getFirstUserList()
    }

    private fun getMoreUserList() {
        viewModel.getMoreUserList()
    }

    private fun goToUserDetailFragment(login: String) {
        val action = ListFragmentDirections.actionListFragmentToUserDetailFragment(login)
        findNavController().navigate(action)
    }
}
