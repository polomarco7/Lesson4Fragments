package com.example.lesson3fragments.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson3fragments.FRAGMENT_A_TAG
import com.example.lesson3fragments.FRAGMENT_D_TAG
import com.example.lesson3fragments.STRING_TO_C_KEY
import com.example.lesson3fragments.databinding.FragmentCBinding

class CFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle) = CFragment().apply {
            arguments = bundle
        }
    }

    private var _binding: FragmentCBinding? = null
    private val binding get() = _binding!!
    private var onButtonsClickListener: OnButtonsClickListener? = null
    private var textFromB: String? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            textFromB = it.getString(STRING_TO_C_KEY)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            textView.text = textFromB
            navToD.setOnClickListener {
                onButtonsClickListener?.onForwardButtonClicked(FRAGMENT_D_TAG)
            }

            navToA.setOnClickListener {
                onButtonsClickListener?.onBackClicked(FRAGMENT_A_TAG)
            }
        }
    }
}