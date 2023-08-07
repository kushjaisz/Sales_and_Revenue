package com.example.milkrevenuetracker.ui

import android.content.SharedPreferences
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.milkrevenuetracker.R
import com.example.milkrevenuetracker.databinding.FragmentInputPriceBinding
import com.example.milkrevenuetracker.databinding.FragmentUserInfoBinding
import com.example.milkrevenuetracker.others.Constants
import com.example.milkrevenuetracker.others.Constants.Companion.BUFFALO_MILK_RATE
import com.example.milkrevenuetracker.others.Constants.Companion.COW_MILK_RATE
import com.example.milkrevenuetracker.others.Constants.Companion.HAVE_BUFFALO
import com.example.milkrevenuetracker.others.Constants.Companion.HAVE_COW
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InputPrice : Fragment() {

    private var have_cow: Boolean = false;
    private var have_buffalo: Boolean = false;
    private var _binding: FragmentInputPriceBinding? = null


    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentInputPriceBinding.inflate(inflater, container, false)

        _binding?.cardView?.setOnClickListener {
            val success = setRateData()
            if (success) {

                findNavController().navigate(R.id.action_inputPrice_to_dashboardFragment)
            } else {
                Snackbar.make(requireView(), "Please enter all the fields.", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

        inputVisible()


        return  _binding!!.root
    }

    fun inputVisible(){

        _binding?.radioButton?.setOnClickListener {
            have_cow =true
            _binding?.radioButton?.isActivated=true
            _binding?.radioButtonNO?.isChecked =false
            _binding?.priceCowTextField?.visibility = View.VISIBLE
        }
        _binding?.radioButtonNO?.setOnClickListener {
            have_cow =false
            _binding?.radioButtonNO?.isActivated=true
            _binding?.radioButton?.isChecked =false
            _binding?.priceCowTextField?.visibility = View.GONE
        }

        _binding?.radioButton2?.setOnClickListener {
            have_buffalo =true
            _binding?.radioButton2?.isActivated=true
            _binding?.radioButton3?.isChecked =false
            _binding?.priceBufTextField?.visibility = View.VISIBLE
        }
        _binding?.radioButton3?.setOnClickListener {
            have_buffalo =false
            _binding?.radioButton3?.isActivated=true
            _binding?.radioButton2?.isChecked =false
            _binding?.priceBufTextField?.visibility = View.GONE
        }
    }

    fun setRateData(): Boolean {

        val cowMilkRate = _binding?.priceCowTextInput?.text.toString()
        val buffaloMilkRate = _binding?.priceBufTextInput?.text.toString()

        if (have_cow == true && have_buffalo == true) {

            if(cowMilkRate.isEmpty() || buffaloMilkRate.isEmpty())
            {
                return false
            }
            sharedPref.edit()
                .putString(HAVE_COW, "true")
                .putString(HAVE_BUFFALO, "true")
                .putString(COW_MILK_RATE, cowMilkRate)
                .putString(BUFFALO_MILK_RATE, buffaloMilkRate)
                .apply()
//            Toast.makeText(activity,"${cowMilkRate}",Toast.LENGTH_SHORT).show()

            return true
           }
        else if (have_cow == true ) {
            if(cowMilkRate.isEmpty() )
            {
                return false
            }
            sharedPref.edit()
                .putString(Constants.HAVE_COW, "true")
                .putString(Constants.HAVE_BUFFALO, "false")
                .putString(Constants.COW_MILK_RATE, cowMilkRate)
                .putString(Constants.BUFFALO_MILK_RATE, "")
                .apply()
//            Toast.makeText(activity,"${cowMilkRate}",Toast.LENGTH_SHORT).show()

            return true
           }
        else if (have_buffalo == true ) {
            if( buffaloMilkRate.isEmpty())
            {
                return false
            }
            sharedPref.edit()
                .putString(Constants.HAVE_COW,"false")
                .putString(Constants.HAVE_BUFFALO, "true")
                .putString(Constants.COW_MILK_RATE,"")
                .putString(Constants.BUFFALO_MILK_RATE, buffaloMilkRate)
                .apply()
//            Toast.makeText(activity,"${buffaloMilkRate}",Toast.LENGTH_SHORT).show()

            return true
           }

        return  false
    }

}