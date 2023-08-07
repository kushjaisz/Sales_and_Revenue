package com.example.milkrevenuetracker.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.milkrevenuetracker.R
import com.example.milkrevenuetracker.databinding.FragmentUserInfoBinding
import com.example.milkrevenuetracker.others.Constants.Companion.KEY_FIRST_TIME_TOGGLE
import com.example.milkrevenuetracker.others.Constants.Companion.KEY_NAME
import com.example.milkrevenuetracker.others.Constants.Companion.KEY_ORG
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserInfo : Fragment() {

    private var _binding: FragmentUserInfoBinding? = null

    @Inject
    lateinit var sharedPref: SharedPreferences

    @set:Inject
    var firstTimeAppOpen: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)

        if(sharedPref.getString(KEY_NAME, "")?.length!! > 2)
        {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.userInfo, true)
                .build()
            findNavController().navigate(R.id.action_userInfo_to_dashboardFragment,savedInstanceState,navOptions)

        }


        _binding?.cardView?.setOnClickListener {
            val success = writePersonalDataToSharedPref()
            if (success) {
                findNavController().navigate(R.id.action_userInfo_to_inputPrice)
            } else {
                Snackbar.make(requireView(), "Please enter all the fields.", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

        return  _binding!!.root

    }

    private fun writePersonalDataToSharedPref(): Boolean {
        val name = _binding?.nameTextInput?.text.toString()
        val orgText = _binding?.orgTextInput?.text.toString()
        if (name.isEmpty() || orgText.isEmpty()) {
            return false
        }
        sharedPref.edit()
            .putString(KEY_NAME, name)
            .putString(KEY_ORG, orgText)
            .putBoolean(KEY_FIRST_TIME_TOGGLE, false)
            .apply()
//        val toolbarText = "Let's go, $name!"
//        requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }


}