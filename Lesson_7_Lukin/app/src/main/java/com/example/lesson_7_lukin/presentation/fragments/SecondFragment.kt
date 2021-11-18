package com.example.lesson_7_lukin.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.lesson_7_lukin.R
import com.example.lesson_7_lukin.databinding.FragmentSecondBinding
import com.example.lesson_7_lukin.data.model.BridgeToSend

class SecondFragment : Fragment(R.layout.fragment_second) {
    companion object {
        const val BRIDGE = "bridge"
        fun newInstance(bridge: BridgeToSend): SecondFragment {
            return SecondFragment().apply {
                arguments = bundleOf(
                    BRIDGE to bridge
                )
            }
        }
    }

    private val binding by viewBinding(FragmentSecondBinding::bind)
    private var firstFragmentListener: FirstFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FirstFragmentListener) {
            firstFragmentListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bridge = arguments?.get(BRIDGE) as BridgeToSend
        bridge.icon?.let { binding.include.imageViewBridge.setImageResource(it) }
        Glide.with(this).load(bridge.picture).into(binding.imageViewMainBridge)
        binding.include.textViewBridgeName.text = bridge.name
        binding.include.textViewTime.text = bridge.closeTime
        binding.textViewBridgeDescription.text = bridge.description
        binding.toolbar.setNavigationOnClickListener {
            firstFragmentListener?.backClick()
        }
        binding.buttonHelp.setOnClickListener {
            Toast.makeText(context, R.string.dont_work, Toast.LENGTH_SHORT).show()
        }

    }

}