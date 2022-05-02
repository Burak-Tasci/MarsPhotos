package com.tsci.marsphotostask.presentation.adapters

import android.app.AlertDialog
import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tsci.marsphotostask.R
import com.tsci.marsphotostask.databinding.MarsphotoListitemBinding
import com.tsci.marsphotostask.domain.model.MarsPhoto
import com.tsci.marsphotostask.presentation.ui.ItemPopupWindow


private const val TAG = "MarsPhotoPagedAdapter.kt"

internal class MarsPhotoPagedAdapter :
    PagingDataAdapter<MarsPhoto, MarsPhotoPagedAdapter.MarsPhotoViewHolder>(diffCallback) {

    internal inner class MarsPhotoViewHolder(val binding: MarsphotoListitemBinding) :
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