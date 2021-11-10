package com.example.lesson_6_lukin

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.lesson_6_lukin.thirdScreen.ThirdFragmentListener

class ThirdFragment : Fragment(R.layout.fragment_third) {

    private var thirdFragmentListener: ThirdFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ThirdFragmentListener) {
            thirdFragmentListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            thirdFragmentListener?.onButtonClick()
        }
    }

    override fun onDetach() {
        thirdFragmentListener = null
        super.onDetach()
    }
}