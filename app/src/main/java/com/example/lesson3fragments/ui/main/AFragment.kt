package com.example.lesson3fragments.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson3fragments.FRAGMENT_B_TAG
import com.example.lesson3fragments.FRAGMENT_USER_LIST_TAG
import com.example.lesson3fragments.databinding.FragmentABinding

class AFragment : Fragment() {

    companion object {
        fun newInstance() = AFragment()
    }

    private var _binding: FragmentABinding? = null
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
        _binding = FragmentABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navToB.setOnClickListener {
            onButtonsClickListener?.onForwardButtonClicked(FRAGMENT_B_TAG)
        }

        binding.secondTask.setOnClickListener {
            onButtonsClickListener?.onForwardButtonClicked(FRAGMENT_USER_LIST_TAG)
        }
    }
}