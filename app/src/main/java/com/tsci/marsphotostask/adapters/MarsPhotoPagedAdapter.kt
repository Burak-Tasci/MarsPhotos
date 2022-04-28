package com.tsci.marsphotostask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tsci.marsphotostask.R
import com.tsci.marsphotostask.databinding.MarsphotoItemBinding
import com.tsci.marsphotostask.domain.model.MarsPhoto

class MarsPhotoPagedAdapter: PagingDataAdapter<MarsPhoto, MarsPhotoPagedAdapter.MarsPhotoViewHolder>(diffCallback) {

    inner class MarsPhotoViewHolder(val binding: MarsphotoItemBinding):
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

        holder.binding.apply {

            holder.itemView.apply {

                photo.load(currentItem?.imgSrc){
                    crossfade(true)
                    crossfade(1000)
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_baseline_broken_image_24)
                }
                currentItem?.also {
                    roverName.text = it.roverName
                    dateText.text = it.earthDate
                    cameraName.text = it.cameraName
                    missionStatus.text = it.status
                    launchDateText.text = it.launchDate
                    landingDateText.text = it.landingDate
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder {
        return MarsPhotoViewHolder(
            MarsphotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}