package com.example.lesson_8_lukin.presentation.second


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.color.colorChooser
import com.example.lesson_8_lukin.R
import com.example.lesson_8_lukin.data.db.entity.NoteEntity
import com.example.lesson_8_lukin.databinding.FragmentSecondBinding
import com.example.lesson_8_lukin.presentation.FragmentListener


class SecondFragment : Fragment(R.layout.fragment_second) {

    companion object {
        val colors = intArrayOf(
            Color.parseColor("#E51B23"),
            Color.parseColor("#E81E63"),
            Color.parseColor("#9C27B0"),
            Color.parseColor("#663AB7"),
            Color.parseColor("#5577FC"),
            Color.parseColor("#03A9F4"),
            Color.parseColor("#05BCD4"),
            Color.parseColor("#049788"),
            Color.parseColor("#8CC34A"),
            Color.parseColor("#CDDC37")
        )
        const val NOTE = "note"
        fun newInstance(noteEntity: NoteEntity): SecondFragment {
            return SecondFragment().apply { arguments = bundleOf(NOTE to noteEntity) }
        }
    }

    private val binding by viewBinding(FragmentSecondBinding::bind)

    private val viewModel: SecondViewModel by viewModels()

    private var fragmentListener: FragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentListener) {
            fragmentListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var myColor = Color.parseColor("#FFFFFF")
        binding.toolbar.menu.findItem(R.id.color).setOnMenuItemClickListener {
            MaterialDialog(requireContext()).show {
                title(R.string.color)
                colorChooser(colors = colors) { _, color ->
                    myColor = color
                }
                positiveButton(R.string.color)
            }
            true
        }
        var id: Int? = null
        var title: String
        var text: String
        if (arguments != null) {
            val note = arguments?.get(NOTE) as NoteEntity
            binding.editTextText.setText(note.note)
            binding.editTextTitle.setText(note.title)
            id = note.id
            myColor = note.color
        }
        binding.toolbar.setNavigationOnClickListener {
            title = binding.editTextTitle.text.toString()
            text = binding.editTextText.text.toString()
            viewModel.createNote(it.context, title, text, myColor, id)
            fragmentListener?.backClick()
        }
    }
}