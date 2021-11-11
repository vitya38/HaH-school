package com.example.lesson_7_lukin

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.delay

class FirstFragment : Fragment(R.layout.fragment_first) {

    private val progressBar by lazy { view?.findViewById<ProgressBar>(R.id.progressBar) }
    private val recyclerView by lazy { view?.findViewById<RecyclerView>(R.id.recyclerView) }

    private var firstFragmentListener: FirstFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FirstFragmentListener) {
            firstFragmentListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadBridges()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.menu.findItem(R.id.map).setOnMenuItemClickListener {
            Toast.makeText(this.context, it.title.toString(), Toast.LENGTH_SHORT).show()
            true
        }
        toolbar.menu.findItem(R.id.info).setOnMenuItemClickListener {
            Toast.makeText(this.context, it.title.toString(), Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun loadBridges() {
        lifecycleScope.launchWhenCreated {
            try {
                delay(2000)
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
        progressBar?.isVisible = true
    }

    private fun setStateData(bridges: List<Bridge>) {
        progressBar?.visibility = View.GONE
        val gridLayoutManager = GridLayoutManager(this.context, 1, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        val adapter = BridgeAdapter()
        recyclerView?.adapter = adapter
        adapter.setItems(bridges)
        adapter.onItemCLick = { icon, picture, name, time, desc ->
            firstFragmentListener?.click(icon, picture, name, time, desc)
        }
    }

    private fun setStateEmpty() {
        val textViewEmpty = view?.findViewById<TextView>(R.id.textViewEmpty)
        textViewEmpty?.isVisible = true
        progressBar?.isVisible = false
        textViewEmpty?.text = getString(R.string.alert)
    }

    private fun setStateError(e: Exception) {
        progressBar?.isVisible = false
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