package com.example.authmetest.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.authmetest.R
import com.example.authmetest.databinding.ContentListFooterBinding
import com.example.authmetest.databinding.ContentListItemBinding
import com.example.authmetest.interfaces.OnSelectItemListener
import com.example.github_user_sdk.network.json.User

class RvUserAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal enum class ItemType { ITEM, FOOTER }

    private var mDataList: MutableList<User> = mutableListOf()
    private var mOnSelectItemListener: OnSelectItemListener<User>? = null

    private val mRequestOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.drawable.circle)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .dontTransform()

    private var mNeedMoreLoading = false

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ItemType.ITEM.ordinal) {
            ItemViewHolder(
                ContentListItemBinding.inflate(
                    LayoutInflater.from(viewGroup.context),
                    viewGroup,
                    false
                )
            )
        } else {
            FooterViewHolder(
                ContentListFooterBinding.inflate(
                    LayoutInflater.from(viewGroup.context),
                    viewGroup,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        try {
            when (viewHolder) {
                is ItemViewHolder -> viewHolder.bind(position)
                is FooterViewHolder -> viewHolder.bind()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            mDataList.lastIndex -> ItemType.FOOTER.ordinal
            else -> ItemType.ITEM.ordinal
        }
    }

    fun setData(newDataList: MutableList<User>?, needMoreLoading: Boolean) {
        mNeedMoreLoading = needMoreLoading
        mDataList = newDataList ?: mutableListOf()

        if (mDataList.size > 0) {
            //在最尾端添加一筆空物件，做 footer view 顯示用
            mDataList.add(User(null, null, null, null))
        }
        notifyDataSetChanged()
    }

    fun setOnSelectItemListener(onSelectItemListener: OnSelectItemListener<User>?) {
        mOnSelectItemListener = onSelectItemListener
    }

    inner class ItemViewHolder(private val binding: ContentListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val data = mDataList[position]
            Glide.with(binding.root.context)
                .load(data.avatar_url)
                .apply(mRequestOptions)
                .into(binding.ivHeader)
            binding.tvLogin.text = data.login
            binding.tvStaff.visibility = if (data.site_admin == true) View.VISIBLE else View.GONE
            binding.root.setOnClickListener { mOnSelectItemListener?.onClick(data) }
        }
    }

    inner class FooterViewHolder(private val binding: ContentListFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.tvFooter.setText(if (mNeedMoreLoading) R.string.loading else R.string.footer)
        }
    }
}
