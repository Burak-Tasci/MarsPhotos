package com.tsci.marsphotostask.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.tsci.marsphotostask.R
import com.tsci.marsphotostask.common.Constants
import com.tsci.marsphotostask.common.Constants.Rovers.CURIOSITY
import com.tsci.marsphotostask.databinding.FilterLayoutBinding
import com.tsci.marsphotostask.presentation.BaseViewModel

private const val TAG = "FilterWindow.kt"

class FilterWindow(private val roverName: String) :
    DialogFragment(R.layout.filter_layout) {


    private val viewModel: BaseViewModel by activityViewModels()

    private var _binding: FilterLayoutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        _binding = FilterLayoutBinding.inflate(inflater, container, false)
        setVisibilities()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeCheckboxes()
        binding.submitButton.setOnClickListener {
            filterButtonClicked()
            dismiss()
        }
    }

    private fun initializeCheckboxes() {
        val cameras = viewModel.cameras.get(roverName)?.value
        for (child in binding.checkboxes.children) {
            if (child is CheckBox && child.visibility == View.VISIBLE) {
                if (cameras!!.contains(child.text.toString())) child.isChecked = true
                else child.isChecked = false
            }
        }
    }

    private fun filterButtonClicked() {

        val filters = mutableListOf<String>()
        for (child in binding.checkboxes.children) {
            if (child is CheckBox && child.visibility == View.VISIBLE) {
                if (child.isChecked) filters.add(child.text.toString())
            }
        }
        viewModel.cameras.get(roverName)?.value = filters
    }


    private fun setVisibilities() {

        val cameras = when(roverName){
            CURIOSITY.name -> resources.getStringArray(R.array.curiosity_cameras)
            else -> resources.getStringArray(R.array.opportunity_and_spirit_cameras)
        }
        for (child in binding.checkboxes.children) {
            if (child is CheckBox) {
                if (cameras.contains(child.text))
                    child.visibility = View.VISIBLE
                else
                    child.visibility = View.GONE
            }
        }
    }
}

