package com.codingwithze.nutechwallet.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingwithze.nutechwallet.R
import com.codingwithze.nutechwallet.data.TransactioHistory
import com.codingwithze.nutechwallet.databinding.ItemTransactionBinding

class TransactionHistoryAdapter(var listHistory : List<TransactioHistory>):RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder>() {
    class ViewHolder(var binding :ItemTransactionBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var dataTransaction = listHistory[position]
        holder.binding.transactionId.text = "ID-trans : " + dataTransaction.transactionId
        holder.binding.transactionTime.text = dataTransaction.transactionTime
        holder.binding.transactionType.text = dataTransaction.transactionType
        val transactionType = dataTransaction.transactionType
        if (transactionType == "topup"){
            holder.binding.amount.text = "+ Rp. " + dataTransaction.amount.toString()
        }else{
            holder.binding.amount.text = "- Rp. " + dataTransaction.amount.toString()
            holder.binding.amount.setTextColor(R.color.red)
        }
    }

    override fun getItemCount(): Int {
        return listHistory.size
    }
}