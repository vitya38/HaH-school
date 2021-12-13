package com.example.lesson_12_lukin.presentation.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_12_lukin.R
import com.example.lesson_12_lukin.data.model.Bridge
import com.example.lesson_12_lukin.data.model.BridgesListState
import com.example.lesson_12_lukin.databinding.FragmentBridgeListBinding
import com.example.lesson_12_lukin.presentation.map.BridgeFragmentListener
import com.example.lesson_12_lukin.presentation.base.BaseFragment
import java.lang.Exception

class BridgeListFragment : BaseFragment(R.layout.fragment_bridge_list) {
    private val binding by viewBinding(FragmentBridgeListBinding::bind)
    private val viewModel: BridgeListViewModel by appViewModels()
    private var bridgeFragmentListener: BridgeFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BridgeFragmentListener) {
            bridgeFragmentListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.loadBridges()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bridgesLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                BridgesListState.Loading -> setStateLoading()
                is BridgesListState.Data -> {
                    if (state.bridges.isNotEmpty()) {
                        setStateData(state.bridges)
                    } else {
                        setStateEmpty()
                    }
                }
                is BridgesListState.Error -> setStateError(state.exception)
            }
        }
        binding.toolbar.menu.findItem(R.id.map).setOnMenuItemClickListener {
            bridgeFragmentListener?.mapClick()
            true
        }
    }

    override fun onDetach() {
        super.onDetach()
        bridgeFragmentListener = null
    }

    private fun setStateLoading() {
        binding.progressBar.isVisible = true
        binding.textViewSupport.isVisible = false
    }

    private fun setStateData(bridges: List<Bridge>) {
        binding.progressBar.isVisible = false
        binding.textViewSupport.isVisible = false
        val gridLayoutManager = GridLayoutManager(this.context, 1, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = gridLayoutManager
        val adapter = BridgeListAdapter()
        binding.recyclerView.adapter = adapter
        adapter.setItems(bridges)
        adapter.onItemClick = {
            bridgeFragmentListener?.click(it)
        }
    }

    private fun setStateEmpty() {
        binding.progressBar.isVisible = false
        binding.textViewSupport.isVisible = true
        binding.textViewSupport.text = getText(R.string.empty)
    }

    private fun setStateError(e: Exception) {
        binding.progressBar.isVisible = false
        binding.textViewSupport.isVisible = true
        binding.textViewSupport.text = e.toString()
    }

}