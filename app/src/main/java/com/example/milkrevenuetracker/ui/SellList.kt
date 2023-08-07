package com.example.milkrevenuetracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.milkrevenuetracker.Adapter.SellAdapter
import com.example.milkrevenuetracker.databinding.FragmentSellListBinding
import com.example.milkrevenuetracker.ui.ViewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint


class SellList : Fragment() {

    private var _binding: FragmentSellListBinding? = null
    private val viewModel: MainViewModel by viewModels()

    lateinit var sellAdapter: SellAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSellListBinding.inflate(inflater, container, false)

        sellAdapter = SellAdapter()

        setupRecyclerView()





        if(getArguments()?.getString("type") == "all")
        {
            viewModel.milkall.observe(viewLifecycleOwner, Observer { runs ->
                sellAdapter.submitList(runs)
            })
        }else if(getArguments()?.getString("type") == "year"){





            _binding?.saleList?.text = "Yearly Sale List"
            viewModel.getlastYearDays.observe(viewLifecycleOwner, Observer { runs ->
                sellAdapter.submitList(runs)
            })
        }else if(getArguments()?.getString("type") == "month"){
            _binding?.saleList?.text = "Monthly Sale List"

            viewModel.milkThirty.observe(viewLifecycleOwner, Observer { runs ->
                sellAdapter.submitList(runs)
            })
        }else if(getArguments()?.getString("type") == "week"){
            _binding?.saleList?.text = "Weekly Sale List"

            viewModel.milkSeven.observe(viewLifecycleOwner, Observer { runs ->
                sellAdapter.submitList(runs)
            })
        }else if(getArguments()?.getString("type") == "daily"){
            _binding?.saleList?.text = "Today Sale List"

            viewModel.getlastDays.observe(viewLifecycleOwner, Observer { runs ->
                sellAdapter.submitList(runs)
            })
        }



        return  _binding!!.root


    }

    private val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.layoutPosition
            val run = sellAdapter.differ.currentList[position]
//            viewModel.deleteRun(run)
            Snackbar.make(requireView(), "Successfully deleted run", Snackbar.LENGTH_LONG).apply {
                setAction("Undo") {
                    viewModel.insertRun(run)
                }
                show()
            }
        }
    }



    private fun setupRecyclerView() = _binding?.rvRuns?.apply {
        adapter = sellAdapter
        layoutManager = LinearLayoutManager(activity)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(this)
    }


}