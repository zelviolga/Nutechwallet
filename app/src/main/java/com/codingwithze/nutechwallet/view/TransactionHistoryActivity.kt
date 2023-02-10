package com.codingwithze.nutechwallet.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwithze.nutechwallet.data.TransactioHistory
import com.codingwithze.nutechwallet.databinding.ActivityTransactionHistoryBinding
import com.codingwithze.nutechwallet.view.adapter.TransactionHistoryAdapter
import com.codingwithze.nutechwallet.viewmodel.TransactionHistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionHistoryActivity : AppCompatActivity() {
    lateinit var binding : ActivityTransactionHistoryBinding
    lateinit var transactionHistoryVM : TransactionHistoryViewModel
    lateinit var sharedPref : SharedPreferences
    private var token = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        transactionHistoryVM = ViewModelProvider(this).get(TransactionHistoryViewModel::class.java)
        sharedPref = this.getSharedPreferences("datauser", Context.MODE_PRIVATE)
        token = sharedPref.getString("token","null").toString()
        getTransactionHistory()
    }

    fun getTransactionHistory(){
        transactionHistoryVM.getTransactionHistory("Bearer "+ token)
        transactionHistoryVM.history.observe(this){
            if (it != null){
                showTransactionHistory(it.data)
            }
        }
    }

    fun showTransactionHistory(data : List<TransactioHistory>){
        val adapterHistory = TransactionHistoryAdapter(data)
        binding.rvTransactionHistory.apply {
            val laymanager = LinearLayoutManager( context,LinearLayoutManager.VERTICAL, false)
            adapter = adapterHistory
            layoutManager = laymanager

        }
    }
}