package com.example.milkrevenuetracker.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.milkrevenuetracker.R
import com.example.milkrevenuetracker.databinding.FragmentDashboardBinding
import com.example.milkrevenuetracker.databinding.FragmentSettingBinding
import com.example.milkrevenuetracker.others.Constants
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : Fragment() {

    @Inject
    lateinit var sharedPref: SharedPreferences


    private var have_cow: Boolean = false;
    private var have_buffalo: Boolean = false;
    var hasCow = ""
    var hasBuffalo = ""
    var cowPrice = ""
    var BuffaloPrice = ""
    private var _binding: FragmentSettingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater, container, false)



        _binding?.cardView?.setOnClickListener {
            val success = setRateData()
            if (success) {

                findNavController().navigate(R.id.action_settingFragment_to_dashboardFragment)
            } else {
                Snackbar.make(requireView(), "Please enter all the fields.", Snackbar.LENGTH_SHORT)
                    .show()
            }

        }

        inputVisible()
        loadData()

        return  _binding!!.root

    }

    fun  loadData(){

        hasCow = sharedPref.getString(Constants.HAVE_COW, "").toString()
        hasBuffalo = sharedPref.getString(Constants.HAVE_BUFFALO, "").toString()
        cowPrice = sharedPref.getString(Constants.COW_MILK_RATE,"").toString()
        BuffaloPrice = sharedPref.getString(Constants.BUFFALO_MILK_RATE, "").toString()


        if(hasCow == "true" && hasBuffalo == "true") {
            _binding?.textView3?.visibility = View.GONE
            _binding?.cowRadio?.visibility = View.GONE
            _binding?.textView2?.visibility = View.GONE
            _binding?.bufRadio?.visibility = View.GONE
            _binding?.priceBufTextField?.visibility = View.VISIBLE
            _binding?.priceCowTextField?.visibility = View.VISIBLE
        }


        if(hasCow == "true")
        {
            _binding?.textView3?.visibility = View.GONE
            _binding?.cowRadio?.visibility = View.GONE
            _binding?.priceCowTextField?.visibility = View.VISIBLE

            _binding?.textView2?.visibility = View.VISIBLE
            _binding?.bufRadio?.visibility = View.VISIBLE


        }
        if(hasBuffalo == "true")
        {
            _binding?.textView2?.visibility = View.GONE
            _binding?.bufRadio?.visibility = View.GONE
            _binding?.priceBufTextField?.visibility = View.VISIBLE

            _binding?.textView3?.visibility = View.VISIBLE
            _binding?.cowRadio?.visibility = View.VISIBLE


        }


        _binding?.nameTextInput?.text = sharedPref.getString(Constants.KEY_NAME, "")?.toEditable()
        _binding?.orgTextInput?.text = sharedPref.getString(Constants.KEY_ORG, "")?.toEditable()
        _binding?.priceBufTextInput?.text = sharedPref.getString(Constants.BUFFALO_MILK_RATE, "")?.toEditable()
        _binding?.priceCowTextInput?.text = sharedPref.getString(Constants.COW_MILK_RATE, "")?.toEditable()
    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
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

        val name = _binding?.nameTextInput?.text.toString()
        val orgText = _binding?.orgTextInput?.text.toString()
        if (name.isEmpty() || orgText.isEmpty()) {
            return false
        }
        sharedPref.edit()
            .putString(Constants.KEY_NAME, name)
            .putString(Constants.KEY_ORG, orgText)
            .apply()

        val cowMilkRate = _binding?.priceCowTextInput?.text.toString()
        val buffaloMilkRate = _binding?.priceBufTextInput?.text.toString()

        if (have_cow == true && have_buffalo == true) {

            if(cowMilkRate.isEmpty() || buffaloMilkRate.isEmpty())
            {
                return false
            }
            sharedPref.edit()
                .putString(Constants.HAVE_COW, "true")
                .putString(Constants.HAVE_BUFFALO, "true")
                .putString(Constants.COW_MILK_RATE, cowMilkRate)
                .putString(Constants.BUFFALO_MILK_RATE, buffaloMilkRate)
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
//                .putString(Constants.HAVE_BUFFALO, "false")
                .putString(Constants.COW_MILK_RATE, cowMilkRate)
                .putString(Constants.BUFFALO_MILK_RATE, buffaloMilkRate)
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
//                .putString(Constants.HAVE_COW,"false")
                .putString(Constants.HAVE_BUFFALO, "true")
                .putString(Constants.COW_MILK_RATE,cowMilkRate)
                .putString(Constants.BUFFALO_MILK_RATE, buffaloMilkRate)
                .apply()
//            Toast.makeText(activity,"${buffaloMilkRate}",Toast.LENGTH_SHORT).show()

            return true
        }


        return  false
    }



}