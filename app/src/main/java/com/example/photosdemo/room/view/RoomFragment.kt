package com.example.photosdemo.room.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.RequestManager
import com.example.photosdemo.common.base.BaseFragment
import com.example.photosdemo.common.util.disableChangeAnimations
import com.example.photosdemo.common.util.setVisibilityAnimated
import com.example.photosdemo.databinding.FragmentRoomBinding
import com.example.photosdemo.room.model.RoomPhotos
import com.example.photosdemo.room.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoomFragment : BaseFragment() {

    @Inject
    lateinit var glide: RequestManager
    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RoomViewModel by viewModels()
    private lateinit var roomPhotosDataAdapter: RoomPagingDataAdapter<RoomPhotos>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initView()
        getData()
    }

    private fun initAdapter() {
        roomPhotosDataAdapter = RoomPagingDataAdapter(glide)
        roomPhotosDataAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.NotLoading -> {
                    binding.recyclerView.setVisibilityAnimated(View.VISIBLE)
                    binding.loadingList.root.setVisibilityAnimated(View.GONE)
                    binding.loadingError.root.setVisibilityAnimated(View.GONE, duration = 0)
                    if (roomPhotosDataAdapter.itemCount == 0) {
                        binding.noResultsFound.root.setVisibilityAnimated(
                            View.VISIBLE,
                            duration = 0
                        )
                    } else {
                        binding.noResultsFound.root.setVisibilityAnimated(
                            View.GONE,
                            duration = 0
                        )
                    }
                }
                is LoadState.Loading -> {
                    binding.loadingError.root.setVisibilityAnimated(View.GONE, duration = 0)
                    binding.recyclerView.setVisibilityAnimated(View.GONE, duration = 0)
                    binding.loadingList.root.setVisibilityAnimated(View.VISIBLE, duration = 0)
                    binding.noResultsFound.root.setVisibilityAnimated(View.GONE, duration = 0)
                }
                is LoadState.Error -> {
                    binding.recyclerView.setVisibilityAnimated(View.GONE, duration = 0)
                    binding.loadingList.root.setVisibilityAnimated(View.GONE, duration = 0)
                    binding.loadingError.root.setVisibilityAnimated(
                        View.VISIBLE,
                        duration = 0
                    )
                    binding.noResultsFound.root.setVisibilityAnimated(View.GONE, duration = 0)
                }
            }
        }
    }

    private fun initView() {
        binding.recyclerView.adapter = roomPhotosDataAdapter
        binding.recyclerView.disableChangeAnimations()
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) 2 else 1
            }
        }
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.loadingError.tryAgainButton.setOnClickListener {
            roomPhotosDataAdapter.retry()
        }
    }

    private fun getData() {
        viewModel.searchPhotos("a")?.observe(viewLifecycleOwner, {
            roomPhotosDataAdapter.submitData(lifecycle, it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}