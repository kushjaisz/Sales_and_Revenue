package com.example.milkrevenuetracker.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.milkrevenuetracker.R
import com.example.milkrevenuetracker.databinding.FragmentDashboardBinding
import com.example.milkrevenuetracker.others.Constants.Companion.KEY_NAME
import com.example.milkrevenuetracker.ui.ViewModel.DashboardViewMOdel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    var totalSell: Int? = null
    var totalPrice: Int? = null
    var totalQty: Int? = null
    var totalPriceLastDay: Int? = null
    var totalPriceLastweek: Int? = null
    var totalPriceLastmonth: Int? = null
    var totalPriceLastyear: Int? = null
    var totalPriceAvg: Float? = null
    var totalQtyAvg: Float? = null
    @Inject
    lateinit var sharedPref: SharedPreferences

    private val viewModel: DashboardViewMOdel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        loadData()

        viewModel.totalSell?.observe(viewLifecycleOwner, Observer { it?.let {
             totalSell = it
        } })
        viewModel.totalPrice?.observe(viewLifecycleOwner, Observer { it?.let {
             totalPrice = it
        } })
        viewModel.totalQty?.observe(viewLifecycleOwner, Observer { it?.let {
             totalQty = it
        } })
        viewModel.avgSellPrice?.observe(viewLifecycleOwner, Observer { it?.let {
             totalPriceAvg = it
        } })
        viewModel.avgQty?.observe(viewLifecycleOwner, Observer { it?.let {
             totalQtyAvg = it
        } })
        viewModel.totalPriceLastDay?.observe(viewLifecycleOwner, Observer { it?.let {
            totalPriceLastDay = it
        } })
        viewModel.totalPriceLastweek?.observe(viewLifecycleOwner, Observer { it?.let {
            totalPriceLastweek = it
        } })
        viewModel.totalPriceLastYear?.observe(viewLifecycleOwner, Observer { it?.let {
            totalPriceLastyear = it
        } })
        viewModel.totalPriceLastMonthly?.observe(viewLifecycleOwner, Observer { it?.let {
            totalPriceLastmonth = it
        } })





        _binding?.addSell?.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_inputDetail)

        }

        _binding?.setting?.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_settingFragment)

        }
        _binding?.viewSell?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "all")
            findNavController().navigate(R.id.action_dashboardFragment_to_sellList,bundle)

        }

        return  _binding!!.root

    }

    private fun loadData()  {





        Handler().postDelayed({

            if(totalSell!! > 0){
                _binding?.totalText?.text = "Namaste " + sharedPref.getString(KEY_NAME, "") + "\nYou served ${totalSell} customers with ${totalQty} Liters of milk and you earned ${totalPrice} revenue."
                _binding?.totalAvgPrice?.text = "${totalPriceAvg}"
                _binding?.totalAvgQty?.text = "${totalQtyAvg}"
                _binding?.TotalSaleWeek?.text = "${totalPriceLastweek}"
                _binding?.TotalSaleINADAy?.text = "${totalPriceLastDay}"
                _binding?.totalSaleMonthly?.text = "${totalPriceLastmonth}"
                _binding?.TotalSaleYear?.text = "${totalPriceLastyear}"


                _binding?.imgYear?.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("type", "year")
                    findNavController().navigate(R.id.action_dashboardFragment_to_sellList,bundle)

                }
                _binding?.imgMonth?.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("type", "month")
                    findNavController().navigate(R.id.action_dashboardFragment_to_sellList,bundle)

                }
                _binding?.imgWeek?.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("type", "week")
                    findNavController().navigate(R.id.action_dashboardFragment_to_sellList,bundle)

                }
                _binding?.imgDaily?.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("type", "daily")
                    findNavController().navigate(R.id.action_dashboardFragment_to_sellList,bundle)

                }

            }

            else
            {
                findNavController().navigate(R.id.action_dashboardFragment_to_inputDetail)

            }



        }, 200)
    }

}