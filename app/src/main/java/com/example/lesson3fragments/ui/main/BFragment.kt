package com.example.lesson3fragments.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson3fragments.FRAGMENT_A_TAG
import com.example.lesson3fragments.FRAGMENT_C_TAG
import com.example.lesson3fragments.STRING_TO_C_KEY
import com.example.lesson3fragments.databinding.FragmentBBinding

class BFragment : Fragment() {

    companion object {
        fun newInstance() = BFragment()
    }

    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!
    private var onButtonsClickListener: OnButtonsClickListener? = null
    private val bundle = Bundle()
    private val textToC =  "Hello Fragment C"

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
        _binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            navToC.setOnClickListener {
                bundle.putString(STRING_TO_C_KEY, textToC)
                onButtonsClickListener?.onForwardButtonClicked(FRAGMENT_C_TAG, bundle)
            }

            navToA.setOnClickListener {
                onButtonsClickListener?.onBackClicked(FRAGMENT_A_TAG)
            }
        }
    }
}