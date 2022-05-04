package com.tsci.marsphotostask.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tsci.marsphotostask.R
import com.tsci.marsphotostask.databinding.FragmentMainBinding
import com.tsci.marsphotostask.presentation.BaseViewModel
import com.tsci.marsphotostask.presentation.adapters.MarsPhotoPagedAdapter
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "BaseFragment.kt"

/**
 * A placeholder fragment containing a simple view.
 */
@AndroidEntryPoint
open class BaseFragment : Fragment() {

    protected val viewModel: BaseViewModel by activityViewModels()
    protected val mAdapter: MarsPhotoPagedAdapter = MarsPhotoPagedAdapter()

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }


    private fun setupRecyclerView(){
        binding.recyclerView.apply {
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }
}