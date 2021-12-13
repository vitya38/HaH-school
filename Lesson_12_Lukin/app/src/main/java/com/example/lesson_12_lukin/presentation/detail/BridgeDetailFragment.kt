package com.example.lesson_12_lukin.presentation.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.lesson_12_lukin.R
import com.example.lesson_12_lukin.data.model.Bridge
import com.example.lesson_12_lukin.data.model.BridgeListState
import com.example.lesson_12_lukin.databinding.FragmentBridgeDetailBinding
import com.example.lesson_12_lukin.presentation.map.BridgeFragmentListener
import com.example.lesson_12_lukin.presentation.base.BaseFragment
import java.lang.Exception

class BridgeDetailFragment : BaseFragment(R.layout.fragment_bridge_detail) {
    companion object {
        const val ID = "id"
        fun newInstance(id: String): BridgeDetailFragment {
            return BridgeDetailFragment().apply {
                arguments = bundleOf(
                    ID to id
                )
            }
        }
    }


    private val binding by viewBinding(FragmentBridgeDetailBinding::bind)
    private val viewModel: BridgeDetailViewModel by appViewModels()
    private var bridgeFragmentListener: BridgeFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BridgeFragmentListener)
            bridgeFragmentListener = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.getString(ID)
        lifecycleScope.launchWhenStarted {
            if (id != null) {
                viewModel.loadBridge(id)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bridgeLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                BridgeListState.Loading -> setStateLoading()
                is BridgeListState.Data -> {
                    setStateData(state.bridge)
                }
                is BridgeListState.Error -> setStateError(state.exception)
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            bridgeFragmentListener?.backClick()
        }
    }

    override fun onDetach() {
        super.onDetach()
        bridgeFragmentListener = null
    }

    private fun setStateLoading() {
        binding.progressBar.isVisible = true
    }

    private fun setStateData(bridge: Bridge) {
        binding.progressBar.isVisible = false
        binding.bridgeView.setBridge(bridge)
        binding.textViewBridgeDescription.text = bridge.description
        Glide.with(this).load(bridge.photoCloseUrl).into(binding.imageViewMainBridge)
    }

    private fun setStateError(e: Exception) {
        binding.progressBar.isVisible = false
        binding.textViewBridgeDescription.text = e.toString()
    }
}