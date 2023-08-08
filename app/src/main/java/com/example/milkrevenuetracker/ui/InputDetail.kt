package com.example.milkrevenuetracker.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.milkrevenuetracker.R
import com.example.milkrevenuetracker.databinding.FragmentDashboardBinding
import com.example.milkrevenuetracker.databinding.FragmentInputDetailBinding
import com.example.milkrevenuetracker.db.MilkEntity
import com.example.milkrevenuetracker.others.Constants
import com.example.milkrevenuetracker.others.Constants.Companion.BUFFALO_MILK_RATE
import com.example.milkrevenuetracker.others.Constants.Companion.COW_MILK_RATE
import com.example.milkrevenuetracker.others.Constants.Companion.HAVE_BUFFALO
import com.example.milkrevenuetracker.others.Constants.Companion.HAVE_COW
import com.example.milkrevenuetracker.ui.ViewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject


@AndroidEntryPoint
class InputDetail : Fragment() {


    var hasCow = ""
    var hasBuffalo = ""
    var cowPrice = ""
    var BuffaloPrice = ""
    var milkPrice = 0
    var spinnerVal = 0
    var invoiceNum = ""


    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentInputDetailBinding? = null
    @Inject
    lateinit var sharedPref: SharedPreferences

    var quantity = 0
    var cal = Calendar.getInstance()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputDetailBinding.inflate(inflater, container, false)

        loadFieldsFromSharedPref()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val invoice = DateTimeFormatter.ofPattern("ddMMyyyyhhmmss")
        val current = LocalDateTime.now().format(formatter)
         invoiceNum = LocalDateTime.now().format(invoice)
        _binding?.selectDate!!.text = current
        _binding?.invoice!!.text = invoiceNum


        _binding?.selectDate?.setOnClickListener {

            val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                       dayOfMonth: Int) {
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateDateInView()

                }

    }

            _binding?.selectDate?.setOnClickListener {
                activity?.let { it1 ->
                    DatePickerDialog(
                        it1,
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()

                }
            }

        }

        _binding?.spFilter?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(adapterView: AdapterView<*>?) {}

            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long

            ) {
                spinnerVal = pos




            }
        }




        _binding?.discreteSlider?.addOnChangeListener { slider, value, fromUser ->



            quantity = value.toInt()
            _binding?.quantity?.text = value.toInt().toString()
            if(hasCow == "true" && hasBuffalo == "true")
            {

                if(spinnerVal == 0)
                {
                    milkPrice = quantity * cowPrice.toInt()
                    _binding?.Totalprice?.text = milkPrice.toString()

                }
                if(spinnerVal == 1)
                {
                    milkPrice = quantity * BuffaloPrice.toInt()

                    _binding?.Totalprice?.text = milkPrice.toString()

                }


            }
            else if (hasCow == "true")
            {
                milkPrice = quantity * cowPrice.toInt()

                _binding?.Totalprice?.text = milkPrice.toString()

            }else if (hasBuffalo == "true")
            {
                milkPrice = quantity * BuffaloPrice.toInt()

                _binding?.Totalprice?.text = milkPrice.toString()

            }


        }





        _binding?.cardView?.setOnClickListener {
            insertSellData()

            Snackbar.make(requireView(), "Data Inserted", Snackbar.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_inputDetail_to_dashboardFragment)

        }

        return  _binding!!.root

    }


    @SuppressLint("SuspiciousIndentation")
    fun insertSellData(){
      val name = _binding?.nameTextInput?.text.toString()


        if(hasCow == "true" && hasBuffalo == "true")
        {
            _binding?.spFilter?.visibility = View.VISIBLE

            if(spinnerVal == 0)
            {
                milkPrice = quantity * cowPrice.toInt()

                val milkEntity = MilkEntity(name,quantity,milkPrice,invoiceNum,"Cow Milk",cal.timeInMillis)
                viewModel.insertRun(milkEntity)

            }
            if(spinnerVal == 1)
            {
                milkPrice = quantity * BuffaloPrice.toInt()

                val milkEntity = MilkEntity(name,quantity,milkPrice,invoiceNum,"Buffalo Milk",cal.timeInMillis)
                viewModel.insertRun(milkEntity)

            }


        }
        else if (hasCow == "true")
        {
            milkPrice = quantity * cowPrice.toInt()

            val milkEntity = MilkEntity(name,quantity,milkPrice,invoiceNum,"Cow Milk",cal.timeInMillis)
            viewModel.insertRun(milkEntity)

        }else if (hasBuffalo == "true")
        {
            milkPrice = quantity * BuffaloPrice.toInt()

            val milkEntity = MilkEntity(name,quantity,milkPrice,invoiceNum,"Buffalo Milk",cal.timeInMillis)
            viewModel.insertRun(milkEntity)

        }

    }


    private fun loadFieldsFromSharedPref() {
         hasCow = sharedPref.getString(HAVE_COW, "").toString()
         hasBuffalo = sharedPref.getString(HAVE_BUFFALO, "").toString()
         cowPrice = sharedPref.getString(COW_MILK_RATE,"").toString()
         BuffaloPrice = sharedPref.getString(BUFFALO_MILK_RATE, "").toString()

        if(hasCow == "true" && hasBuffalo == "true") {
            _binding?.spFilter?.visibility = View.VISIBLE
            _binding?.milktype?.text = "Choose the types of Milk"

        }
       else if(hasCow == "true" ) {
            _binding?.spFilter?.visibility = View.GONE
            _binding?.milktype?.visibility = View.GONE

        }
       else if(hasBuffalo == "true" ) {
            _binding?.spFilter?.visibility = View.GONE
            _binding?.milktype?.visibility = View.GONE

        }
        }

     fun updateDateInView() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        _binding?.selectDate!!.text = sdf.format(cal.timeInMillis)
//        _binding?.selectDate!!.text = cal.timeInMillis.toString()
    }


}