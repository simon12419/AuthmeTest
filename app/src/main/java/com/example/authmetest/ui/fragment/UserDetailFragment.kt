package com.example.authmetest.ui.fragment

import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.authmetest.R
import com.example.authmetest.databinding.FragmentUserDetailBinding
import com.example.authmetest.ui.activity.MainViewModel
import com.example.authmetest.util.JumpUtil
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class UserDetailFragment : BaseFragment() {
    private val args: UserDetailFragmentArgs by navArgs()
    private val viewModel: MainViewModel by activityViewModel()

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    private val mRequestOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.drawable.circle)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .dontTransform()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initButton()
        getUserDetail()
        initObserve()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initButton() {
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getUserDetail() {
        viewModel.getUserDetail(args.login)
    }

    private fun initObserve() {
        viewModel.userDetailResult.observe(viewLifecycleOwner) {
            try {
                Glide.with(binding.root.context)
                    .load(it?.avatar_url)
                    .apply(mRequestOptions)
                    .into(binding.ivHeader)

                binding.tvName.text = it?.name
                binding.tvBio.text = it?.bio
                binding.tvLogin.text = it?.login
                binding.tvStaff.visibility = if (it?.site_admin == true) View.VISIBLE else View.GONE
                binding.tvLocation.text = it?.location
                binding.tvLink.text = createHyperlink(it?.blog)
                binding.tvLink.setOnClickListener { _ ->
                    JumpUtil.toExternalWeb(binding.root.context, it?.blog)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun createHyperlink(link: String?): Spanned {
        val htmlString = "<a href='${link}'>${link}</a>"
        return HtmlCompat.fromHtml(htmlString, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}
