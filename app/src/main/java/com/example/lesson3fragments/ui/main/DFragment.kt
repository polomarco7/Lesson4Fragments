package com.example.lesson3fragments.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson3fragments.FRAGMENT_B_TAG
import com.example.lesson3fragments.R
import com.example.lesson3fragments.databinding.FragmentDBinding

class DFragment : Fragment() {

    companion object {
        fun newInstance() = DFragment()
    }

    private var _binding: FragmentDBinding? = null
    private val binding get() = _binding!!
    private var onButtonsClickListener: OnButtonsClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnButtonsClickListener) {
            onButtonsClickListener = context
        } else {
            throw ClassCastException(
                context.toString()
                        + " must implement OnButtonsClickListener"
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            navToB.setOnClickListener {
                onButtonsClickListener?.onForwardButtonClicked(FRAGMENT_B_TAG)
            }
        }
    }
}