package com.tsci.marsphotostask.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tsci.marsphotostask.adapters.MarsPhotoPagedAdapter
import com.tsci.marsphotostask.common.Constants
import com.tsci.marsphotostask.common.Constants.Rovers
import com.tsci.marsphotostask.data.remote.dto.Rover
import com.tsci.marsphotostask.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A placeholder fragment containing a simple view.
 */
@AndroidEntryPoint
class MainFragment : Fragment() {

    private val pageViewModel: PageViewModel by viewModels()
    private lateinit var mAdapter: MarsPhotoPagedAdapter
    private var _binding: FragmentMainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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

        mAdapter = MarsPhotoPagedAdapter()

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            adapter = mAdapter
            setHasFixedSize(true)
        }
        lifecycleScope.launch {
            pageViewModel.listData.collect{ pagingData ->
                mAdapter.submitData(pagingData)
            }
        }

    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_ROVER_NAME = "rover_name"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(rover: Rovers): MainFragment {
            return MainFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ROVER_NAME, rover.ordinal)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}