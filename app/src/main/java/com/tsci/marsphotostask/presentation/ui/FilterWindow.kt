package com.tsci.marsphotostask.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.tsci.marsphotostask.R
import com.tsci.marsphotostask.databinding.ActivityMainBinding
import com.tsci.marsphotostask.databinding.FilterLayoutBinding
import com.tsci.marsphotostask.presentation.BaseViewModel

private const val TAG = "FilterWindow.kt"
class FilterWindow:
    DialogFragment(R.layout.filter_layout) {


    private val viewModel: BaseViewModel by activityViewModels()

    private var _binding: FilterLayoutBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    protected val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);
        _binding = FilterLayoutBinding.inflate(inflater, container, false)
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
            Log.d(TAG, "onViewCreated: ${viewModel.filters.value}")
            dismiss()
            
        }
    }

    private fun initializeCheckboxes() {
        binding.run {
            for (index in 0 until checkboxes.childCount){
                val child: View = checkboxes.getChildAt(index)
                if (child is CheckBox){
                    if (viewModel.filters.value!!.contains(child.text.toString()))
                        child.isChecked = true
                    else
                        child.isChecked = false
                }
            }
        }
    }

    private fun filterButtonClicked(){
        val filters :MutableList<String> = mutableListOf()

        binding.run {
            for (index in 0 until checkboxes.childCount){
                val child: View = checkboxes.getChildAt(index)
                if (child is CheckBox){
                    if (child.isChecked) filters.add(child.text.toString())
                }
            }
        }
        viewModel.filters.value = filters
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ${viewModel.filters.value}")
    }
}