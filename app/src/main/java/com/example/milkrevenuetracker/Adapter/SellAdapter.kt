package com.example.milkrevenuetracker.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.milkrevenuetracker.R
import com.example.milkrevenuetracker.db.MilkEntity
import java.text.SimpleDateFormat
import java.util.Locale

class SellAdapter: RecyclerView.Adapter<SellAdapter.SellViewHolder>() {



    private val diffCallback = object : DiffUtil.ItemCallback<MilkEntity>() {
        override fun areItemsTheSame(oldItem: MilkEntity, newItem: MilkEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MilkEntity, newItem: MilkEntity): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    inner  class SellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textView: TextView = itemView.findViewById(R.id.TextName)
        val invoice: TextView = itemView.findViewById(R.id.InvoiceNumber)
        val date: TextView = itemView.findViewById(R.id.date)
        val qty: TextView = itemView.findViewById(R.id.Total_qty)
        val price: TextView = itemView.findViewById(R.id.Total_price)
        val milkType: TextView = itemView.findViewById(R.id.milkType)

    }

    fun submitList(list: List<MilkEntity>) = differ.submitList(list)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellViewHolder {
     return    SellViewHolder(   LayoutInflater.from(parent.context).inflate(
         R.layout.sellname,
         parent,
         false
     ))
    }

    override fun getItemCount(): Int {
        Log.d("cin", "onCreateView: ${differ.currentList.size}")

        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SellViewHolder, position: Int) {
        val run = differ.currentList[position]
        // set item data
        holder.itemView.apply {
            "${run.custName}".also {
               holder.textView.text ="Customer Name : "+ it
            }
            holder.itemView.apply {
            "${run.milkInvoiceNo}".also {
               holder.invoice.text ="Invoice : "+ it
            }
            holder.itemView.apply {

                val myFormat = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.US)

            "${sdf.format(run.timeInMillis)}".also {
               holder.date.text ="Date : " +it
            }
        }
            holder.itemView.apply {


            "${run.milkQty}".also {
               holder.qty.text ="Quantity : " +it +" L"
            }
            holder.itemView.apply {


            "${run.price}".also {
               holder.price.text ="Price : ₹ " +it
            }
                holder.itemView.apply {


            "${run.milkType}".also {
               holder.milkType.text ="Type : " +it
            }
        }
    }}}}}}


