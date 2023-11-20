package com.example.lesson3fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.lesson3fragments.part2.UserEditFragment
import com.example.lesson3fragments.part2.UserListFragment
import com.example.lesson3fragments.ui.main.AFragment
import com.example.lesson3fragments.ui.main.BFragment
import com.example.lesson3fragments.ui.main.CFragment
import com.example.lesson3fragments.ui.main.DFragment
import com.example.lesson3fragments.ui.main.OnButtonsClickListener

class MainActivity : AppCompatActivity(), OnButtonsClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AFragment.newInstance())
                .addToBackStack(FRAGMENT_A_TAG)
                .commit()
        }
    }

    override fun onForwardButtonClicked(targetName: String, bundle: Bundle?) {
        val target = when (targetName) {
            FRAGMENT_B_TAG -> BFragment.newInstance()
            FRAGMENT_C_TAG -> bundle?.let { CFragment.newInstance(it) }
            FRAGMENT_D_TAG -> DFragment.newInstance()
            FRAGMENT_USER_LIST_TAG -> UserListFragment.newInstance()
            FRAGMENT_USER_EDIT_TAG -> bundle?.let { UserEditFragment.newInstance(it)}
            else -> {
                throw java.lang.RuntimeException("No such fragment tag")
            }
        }
        target?.let {
            supportFragmentManager.commit {
                replace(R.id.container, it)
                addToBackStack(targetName)
            }
        }
    }

    override fun onBackClicked(targetName: String) {
        val fragmentManager = supportFragmentManager
        while (fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name != targetName) {
            fragmentManager.popBackStackImmediate()
        }
    }
}