package com.example.lesson3fragments.part2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResult
import com.example.lesson3fragments.FRAGMENT_USER_LIST_TAG
import com.example.lesson3fragments.REQUEST_KEY_EDIT
import com.example.lesson3fragments.databinding.FragmentUserEditBinding
import com.example.lesson3fragments.ui.main.OnButtonsClickListener

class UserEditFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle) = UserEditFragment().apply {
            arguments = bundle
        }
    }

    private var _binding: FragmentUserEditBinding? = null
    private val binding get() = _binding!!
    private val bundle = Bundle()
    private var onButtonsClickListener: OnButtonsClickListener? = null

    private var users = Users()

    private var firstName = ""
    private var lastName = ""
    private var phoneNumber = ""
    private var url = ""

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
        arguments.let {
            if (it != null) {
                users = it.getParcelable("user")!!
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            nameTextEdit.setText(users.firstName)
            lastNameTextEdit.setText(users.lastName)
            phoneTextEdit.setText(users.phoneNumber)
            urlTextEdit.setText(users.imageUrl)

            lastName = users.lastName
            firstName = users.firstName
            phoneNumber = users.phoneNumber
            url = users.imageUrl

            nameTextEdit.addTextChangedListener {
                if (it.isNullOrEmpty())
                    firstName = it.toString()
            }

            lastNameTextEdit.addTextChangedListener {
                if (it.isNullOrEmpty())
                    lastName = it.toString()
            }
            phoneTextEdit.addTextChangedListener {
                if (it.isNullOrEmpty())
                    phoneNumber = it.toString()
            }
            urlTextEdit.addTextChangedListener {
                if (it.isNullOrEmpty())
                    url = it.toString()
            }

            editBtn.setOnClickListener {
                bundle.apply {
                    putInt("id", users.id!!)
                    putString("firstName", firstName)
                    putString("lastName", lastName)
                    putString("phoneNumber", phoneNumber)
                    putString("url", url)
                }
                setFragmentResult(REQUEST_KEY_EDIT, bundle)
                onButtonsClickListener?.onBackClicked(FRAGMENT_USER_LIST_TAG)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}