package com.example.lesson_7_lukin.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_7_lukin.data.viewholder.BridgeAdapter
import com.example.lesson_7_lukin.R
import com.example.lesson_7_lukin.databinding.FragmentFirstBinding
import com.example.lesson_7_lukin.data.model.Bridge
import com.example.lesson_7_lukin.data.remote.BridgesApi

class FirstFragment : Fragment(R.layout.fragment_first) {

    private val binding by viewBinding(FragmentFirstBinding::bind)
    private var firstFragmentListener: FirstFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FirstFragmentListener) {
            firstFragmentListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadBridges()
        binding.toolbar.menu.findItem(R.id.map).setOnMenuItemClickListener {
            Toast.makeText(this.context, it.title.toString(), Toast.LENGTH_SHORT).show()
            true
        }
        binding.toolbar.menu.findItem(R.id.info).setOnMenuItemClickListener {
            Toast.makeText(this.context, it.title.toString(), Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun loadBridges() {
        lifecycleScope.launchWhenCreated {
            try {
                setStateLoading()
                val bridges = BridgesApi.apiService.getBridges()
                if (bridges.isEmpty()) {
                    setStateEmpty()
                } else {
                    setStateData(bridges)
                }
            } catch (e: java.lang.Exception) {
                setStateError(e)
            }
        }
    }

    private fun setStateLoading() {
        binding.progressBar.isVisible = true
    }

    private fun setStateData(bridges: List<Bridge>) {
        binding.progressBar.isVisible = false
        val gridLayoutManager = GridLayoutManager(this.context, 1, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = gridLayoutManager
        val adapter = BridgeAdapter()
        binding.recyclerView.adapter = adapter
        adapter.setItems(bridges)
        adapter.onItemCLick = { bridge ->
            firstFragmentListener?.click(bridge)
        }
        adapter.onBellClick = {
            Toast.makeText(context, R.string.bell, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setStateEmpty() {
        val textViewEmpty = view?.findViewById<TextView>(R.id.textViewEmpty)
        textViewEmpty?.isVisible = true
        binding.progressBar.isVisible = false
        textViewEmpty?.text = getString(R.string.alert)
    }

    private fun setStateError(e: Exception) {
        binding.progressBar.isVisible = false
        val dialogAlert = context?.let { AlertDialog.Builder(it, R.style.button) }
        dialogAlert?.setTitle(R.string.something_goes_wrong)
        dialogAlert?.setMessage(e.toString())
        dialogAlert?.setPositiveButton(
            R.string.positive_button
        ) { dialog, _ ->
            loadBridges()
            dialog.dismiss()
        }
        val alert = dialogAlert?.create()
        alert?.show()
    }

}