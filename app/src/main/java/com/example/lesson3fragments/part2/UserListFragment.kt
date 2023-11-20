package com.example.lesson3fragments.part2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.lesson3fragments.FRAGMENT_USER_EDIT_TAG
import com.example.lesson3fragments.R
import com.example.lesson3fragments.REQUEST_KEY_EDIT
import com.example.lesson3fragments.databinding.FragmentABinding
import com.example.lesson3fragments.databinding.FragmentUserListBinding
import com.example.lesson3fragments.ui.main.OnButtonsClickListener
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UserListFragment : Fragment() {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserListViewModel by viewModels()
    private val adapter = ContactsAdapter { contacts -> onItemClick(contacts) }

    private var onButtonsClickListener: OnButtonsClickListener? = null

    private var id = 0
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
        setFragmentResultListener(REQUEST_KEY_EDIT) { _, bundle ->
            id = bundle.getInt("id")
            firstName = bundle.getString("firstName", "")
            lastName = bundle.getString("lastName", "")
            phoneNumber = bundle.getString("phoneNumber", "")
            url = bundle.getString("url", "")
            adapter.updateContact(Users(id, firstName, lastName, phoneNumber, url))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = adapter

        viewModel.users.onEach {
            adapter.updateAll(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun onItemClick(item: Users) {
        val bundle = bundleOf("user" to item)
        onButtonsClickListener?.onForwardButtonClicked(FRAGMENT_USER_EDIT_TAG, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}