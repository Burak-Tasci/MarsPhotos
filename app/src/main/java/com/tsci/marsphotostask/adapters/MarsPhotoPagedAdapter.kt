package com.tsci.marsphotostask.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.tsci.marsphotostask.R
import com.tsci.marsphotostask.databinding.MarsphotoItemBinding
import com.tsci.marsphotostask.databinding.MarsphotoListitemBinding
import com.tsci.marsphotostask.domain.model.MarsPhoto
private const val TAG = "MarsPhotoPagedAdapter.kt"
class MarsPhotoPagedAdapter: PagingDataAdapter<MarsPhoto, MarsPhotoPagedAdapter.MarsPhotoViewHolder>(diffCallback) {

    inner class MarsPhotoViewHolder(val binding: MarsphotoListitemBinding):
        RecyclerView.ViewHolder(binding.root)

    companion object{
        val diffCallback = object: DiffUtil.ItemCallback<MarsPhoto>(){
            override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {

        val currentItem = getItem(position)
        Log.d(TAG, "onBindViewHolder: ${currentItem.toString()}")
        holder.binding.apply {

            listItem.load(currentItem?.imgSrc){
                crossfade(true)
                crossfade(1000)
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_baseline_broken_image_24)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoPagedAdapter.MarsPhotoViewHolder {
        return MarsPhotoViewHolder(
            MarsphotoListitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}