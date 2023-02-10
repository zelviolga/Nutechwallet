package com.codingwithze.nutechwallet.view

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.codingwithze.nutechwallet.data.TopUp
import com.codingwithze.nutechwallet.databinding.ActivityTransferBinding
import com.codingwithze.nutechwallet.viewmodel.BalanceViewModel
import com.codingwithze.nutechwallet.viewmodel.TopupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransferActivity : AppCompatActivity() {
    lateinit var binding : ActivityTransferBinding
    lateinit var topupViewModel : TopupViewModel
    lateinit var sharedPref : SharedPreferences
    private var token = ""
    private var saldo = 0
    lateinit var balanceViewModel : BalanceViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        topupViewModel = ViewModelProvider(this).get(TopupViewModel::class.java)
        balanceViewModel = ViewModelProvider(this).get(BalanceViewModel::class.java)
        sharedPref = this.getSharedPreferences("datauser", Context.MODE_PRIVATE)
        token = sharedPref.getString("token","null").toString()
        getBalance()
        doTransfer()
        cancelTransfer()
    }


    fun getBalance(){
        balanceViewModel.getBalanceWallet("Bearer "+token)
        balanceViewModel.balance.observe(this){
            if (it != null){
                saldo = it.data.balance
            }
        }
    }

    fun doTransfer(){
        binding.btnSend.setOnClickListener {
            if (binding.amountTransfer.text.isNullOrEmpty()){
                binding.amountTransfer.error ="Amount is required"
            }else{
                val amount = binding.amountTransfer.text.toString().toInt()
                if (amount < 10000){
                    Toast.makeText(this, "Minimum is 10.000", Toast.LENGTH_SHORT).show()
                }else if(saldo < amount){
                    Toast.makeText(this, "You don't have enough balance, your balance : Rp. $saldo", Toast.LENGTH_SHORT).show()
                } else{
                    transfer()
                }
            }
        }
    }

    fun transfer(){
        val amount = binding.amountTransfer.text.toString().toInt()
        topupViewModel.postTransfer("Bearer "+token, TopUp(amount))
        topupViewModel.topUp.observe(this){
            if (it != null){
                AlertDialog.Builder(this)
                    .setMessage("${it.message} senilai : $amount")
                    .setPositiveButton("Ok"){ dialogInterface: DialogInterface, i: Int ->
                        finish()
                    }
                    .show()
            }
        }
    }

    fun cancelTransfer(){
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}