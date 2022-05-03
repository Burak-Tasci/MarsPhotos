package com.tsci.marsphotostask.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
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
        binding.item = item
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}