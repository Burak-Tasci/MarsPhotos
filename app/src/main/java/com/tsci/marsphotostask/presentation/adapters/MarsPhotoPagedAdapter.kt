package com.tsci.marsphotostask.presentation.adapters

import android.app.AlertDialog
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
internal class MarsPhotoPagedAdapter(
    private val fragmentManager: FragmentManager
): PagingDataAdapter<MarsPhoto, MarsPhotoPagedAdapter.MarsPhotoViewHolder>(diffCallback) {

    internal inner class MarsPhotoViewHolder(val binding: MarsphotoListitemBinding):
        RecyclerView.ViewHolder(binding.root)

    companion object{
        private val diffCallback = object: DiffUtil.ItemCallback<MarsPhoto>(){
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

            listItem.load(currentItem?.imgSrc){
                crossfade(true)
                crossfade(1000)
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_baseline_broken_image_24)
            }

            listItem.setOnLongClickListener {

                if (currentItem != null) {
                    ItemPopupWindow(
                        currentItem
                    ).show(
                        fragmentManager,
                        "Item Popup Window"
                    )
                }
                else{
                    val alertDialog = AlertDialog.Builder(holder.itemView.context as AppCompatActivity).create()
                    alertDialog.setTitle(R.string.dialog_error)
                    alertDialog.setMessage(
                        it.context.getString(R.string.dialog_message)
                    )
                    alertDialog.setIcon(R.drawable.ic_baseline_error_24)
                    alertDialog.setButton(
                        "OK"
                    ) { dialog, which -> alertDialog.cancel() }
                    alertDialog.show()
                }
                true
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