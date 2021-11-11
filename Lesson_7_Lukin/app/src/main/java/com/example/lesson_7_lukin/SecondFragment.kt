package com.example.lesson_7_lukin

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class SecondFragment : Fragment(R.layout.fragment_second) {
    companion object {
        const val ICON = "icon"
        const val PICTURE = "picture"
        const val NAME = "name"
        const val TIME = "time"
        const val DESC = "desc"
        fun newInstance(icon: Int, picture: String?, name: String?, time: String, description: String?): SecondFragment {
            return SecondFragment().apply {
                arguments = bundleOf(
                    ICON to icon, PICTURE to picture,
                    NAME to name, TIME to time, DESC to description
                )
            }
        }
    }

    private var firstFragmentListener: FirstFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FirstFragmentListener) {
            firstFragmentListener = context
        }
    }

    private val icon by lazy { arguments?.getInt(ICON) }
    private val picture by lazy { arguments?.getString(PICTURE) }
    private val name by lazy { arguments?.getString(NAME) }
    private val time by lazy { arguments?.getString(TIME) }
    private val description by lazy { arguments?.getString(DESC) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView1 = view.findViewById<ImageView>(R.id.imageViewBridge)
        val imageView2 = view.findViewById<ImageView>(R.id.imageViewMainBridge)
        val textViewName = view.findViewById<TextView>(R.id.textViewBridgeName)
        val textViewTime = view.findViewById<TextView>(R.id.textViewTime)
        val textViewDesc = view.findViewById<TextView>(R.id.textViewBridgeDescription)
        icon?.let { imageView1.setImageResource(it) }
        Glide.with(this).load(picture).into(imageView2)
        textViewName.text = name
        textViewTime.text = time
        textViewDesc.text = description
        view.findViewById<Button>(R.id.buttonBack).setOnClickListener {
            firstFragmentListener?.backClick()
        }
        view.findViewById<Button>(R.id.buttonHelp).setOnClickListener {
            Toast.makeText(context, R.string.dont_work, Toast.LENGTH_SHORT).show()
        }
    }

}