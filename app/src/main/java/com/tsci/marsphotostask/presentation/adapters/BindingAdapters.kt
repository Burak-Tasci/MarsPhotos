package com.tsci.marsphotostask.presentation.adapters

import android.app.AlertDialog
import android.app.Dialog
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.tsci.marsphotostask.R
import com.tsci.marsphotostask.domain.model.MarsPhoto
import com.tsci.marsphotostask.presentation.ui.ItemPopupWindow
import dagger.hilt.android.internal.managers.ViewComponentManager

@BindingAdapter("bindShapeableImage")
fun bindShapeableImage(imgView: ShapeableImageView, imgUrl: String?) {
    imgUrl?.let {

        imgView.load(
            imgUrl
        ) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_baseline_broken_image_24)
        }
        val shapeAppearanceModel = ShapeAppearanceModel()
            .toBuilder()
            .setAllCorners(
                CornerFamily.ROUNDED,
                imgView.context.resources.getDimension(R.dimen.corner_radius)
            )
            .build()

        imgView.shapeAppearanceModel = shapeAppearanceModel
    }
}

@BindingAdapter("bindImage")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgView.load(imgUrl) {
        crossfade(true)
        crossfade(1000)
        placeholder(R.drawable.loading_animation)
        error(R.drawable.ic_baseline_broken_image_24)
    }
}

@BindingAdapter("openPopupWindow")
fun openPopupWindow(imgView: ImageView, item: MarsPhoto?){


    val mContext = if (imgView.context is ViewComponentManager.FragmentContextWrapper)
        (imgView.context as ViewComponentManager.FragmentContextWrapper).baseContext
    else
        imgView.context
    imgView.setOnLongClickListener {

        if (item != null) {
            ItemPopupWindow(
                item
            ).show(
            (mContext as AppCompatActivity).supportFragmentManager,
                "Item Popup Window"
            )
        }
        else{
            val alertDialog = AlertDialog.Builder(mContext as AppCompatActivity).create()
            alertDialog.setTitle(R.string.dialog_error)
            alertDialog.setMessage(
                it.context.getString(R.string.dialog_message)
            )
            alertDialog.setIcon(R.drawable.ic_baseline_error_24)
            alertDialog.setButton(
                Dialog.BUTTON_NEGATIVE,
                "OK"
            ) { _, _ -> alertDialog.cancel() }
            alertDialog.show()
        }
        true
    }
}