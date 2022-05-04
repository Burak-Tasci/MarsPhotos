package com.tsci.marsphotostask.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tsci.marsphotostask.databinding.MarsphotoListitemBinding
import com.tsci.marsphotostask.domain.model.MarsPhoto


private const val TAG = "MarsPhotoPagedAdapter.kt"

class MarsPhotoPagedAdapter :
    PagingDataAdapter<MarsPhoto, MarsPhotoPagedAdapter.MarsPhotoViewHolder>(diffCallback) {

    inner class MarsPhotoViewHolder(val binding: MarsphotoListitemBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MarsPhoto>() {
            override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        holder.binding.photo = getItem(position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsPhotoPagedAdapter.MarsPhotoViewHolder {
        return MarsPhotoViewHolder(
            MarsphotoListitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}