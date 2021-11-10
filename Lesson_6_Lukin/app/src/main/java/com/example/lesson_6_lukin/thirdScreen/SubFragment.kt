package com.example.lesson_6_lukin.thirdScreen

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.lesson_6_lukin.R

class SubFragment : Fragment(R.layout.fragment_sub) {
    companion object {
        const val CAT = "cat"
        const val TEXT = "text"
        fun newInstance(text: String, icon: Int): SubFragment {
            return SubFragment().apply { arguments = bundleOf(CAT to icon, TEXT to text) }
        }
    }

    private val catIcon by lazy { arguments?.getInt(CAT) }
    private val catText by lazy { arguments?.getString(TEXT).orEmpty() }
    private var thirdFragmentListener: ThirdFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ThirdFragmentListener) {
            thirdFragmentListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imageViewCat = view.findViewById<ImageView>(R.id.imageViewCat)
        super.onViewCreated(view, savedInstanceState)
        catIcon?.let { imageViewCat.setImageResource(it) }
        view.findViewById<TextView>(R.id.textViewCat).text = catText
        imageViewCat.setOnClickListener {
            thirdFragmentListener?.onImageClick(catText)
        }


    }
}