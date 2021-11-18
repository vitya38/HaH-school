package com.example.lesson_8_lukin.presentation.mainWindow

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_8_lukin.R
import com.example.lesson_8_lukin.data.db.entity.NoteEntity
import com.example.lesson_8_lukin.presentation.mainWindow.viewholder.NoteAdapter
import com.example.lesson_8_lukin.databinding.FragmentFirstBinding
import com.example.lesson_8_lukin.presentation.mainWindow.viewholder.Decorator
import com.example.lesson_8_lukin.presentation.FragmentListener

class mainWindowFragment : Fragment(R.layout.fragment_first) {

    private val binding by viewBinding(FragmentFirstBinding::bind)

    private val viewModel: mainWindowViewModel by viewModels()

    private var fragmentListener: FragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentListener) {
            fragmentListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.subscribeToNotes(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStateLoading()
        val offset = resources.getDimensionPixelOffset(R.dimen.card_margin)
        val decorator = Decorator(offset, offset, offset, offset)
        binding.recyclerView.addItemDecoration(decorator)
        viewModel.notesLiveData.observe(viewLifecycleOwner) { notes ->
            setStateData(notes)
        }
        binding.floatingActionButton.setOnClickListener {
            fragmentListener?.newNoteClick()
        }
    }

    private fun setStateData(notes: List<NoteEntity>) {
        binding.progressBar.isVisible = false
        binding.textViewEmpty.isVisible = false
        val gridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = gridLayoutManager
        val adapter = NoteAdapter()
        binding.recyclerView.adapter = adapter
        adapter.setItems(notes)
        adapter.onItemClick = {
            fragmentListener?.noteClick(it)
        }
        adapter.onLongItemClick = { note ->
            val context = this.requireContext()
            val dialog = AlertDialog.Builder(this.context)
            dialog.setTitle(R.string.what_to_do)
            dialog.setPositiveButton(R.string.delete) { _, _ ->
                viewModel.deleteNotes(context, note)
            }
            dialog.setNegativeButton(R.string.archive) { _, _ ->
                viewModel.archiveNote(context, note)
            }
            val alert = dialog.create()
            alert.show()
        }
        if (notes.isEmpty())
            setStateEmpty()
    }

    private fun setStateLoading() {
        binding.progressBar.isVisible = true
        binding.textViewEmpty.isVisible = false
    }

    private fun setStateEmpty() {
        binding.textViewEmpty.isVisible = true
        binding.progressBar.isVisible = false
    }
}