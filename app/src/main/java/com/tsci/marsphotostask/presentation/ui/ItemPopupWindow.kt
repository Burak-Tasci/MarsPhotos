package com.tsci.marsphotostask.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import coil.load
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.tsci.marsphotostask.R
import com.tsci.marsphotostask.databinding.MarsphotoItemBinding
import com.tsci.marsphotostask.domain.model.MarsPhoto

class ItemPopupWindow(
    private val item: MarsPhoto
): DialogFragment(R.layout.marsphoto_item) {

    private var _binding: MarsphotoItemBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        _binding = MarsphotoItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {

            photo.load(
                item.imgSrc
            ){
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_baseline_broken_image_24)
            }
            val shapeAppearanceModel = ShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED,
                    resources.getDimension(R.dimen.corner_radius)
                )
                .build()

            photo.shapeAppearanceModel = shapeAppearanceModel

            roverName.text = item.roverName
            earthDate.text = item.earthDate
            cameraName.text = resources.getString(R.string.camera_name, item.cameraName)
            landingDate.text = resources.getString(R.string.landing_date, item.landingDate)
            launchDate.text = resources.getString(R.string.launch_date, item.launchDate)
            missionStatus.text = resources.getString(R.string.mission_status, item.status)

        }
    }


}