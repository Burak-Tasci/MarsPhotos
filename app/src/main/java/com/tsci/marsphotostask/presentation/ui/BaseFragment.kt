package com.tsci.marsphotostask.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tsci.marsphotostask.presentation.adapters.MarsPhotoPagedAdapter
import com.tsci.marsphotostask.databinding.FragmentMainBinding
import com.tsci.marsphotostask.presentation.PageViewModel
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "BaseFragment.kt"

/**
 * A placeholder fragment containing a simple view.
 */
@AndroidEntryPoint
open class BaseFragment() : Fragment() {

    protected val viewModel: PageViewModel by activityViewModels()
    internal lateinit var mAdapter: MarsPhotoPagedAdapter

    private var _binding: FragmentMainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = MarsPhotoPagedAdapter(requireContext())

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            adapter = mAdapter
            setHasFixedSize(true)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}