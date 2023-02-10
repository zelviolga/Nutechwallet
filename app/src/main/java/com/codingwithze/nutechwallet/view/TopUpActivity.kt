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
import com.codingwithze.nutechwallet.R
import com.codingwithze.nutechwallet.data.TopUp
import com.codingwithze.nutechwallet.databinding.ActivityTopUpBinding
import com.codingwithze.nutechwallet.viewmodel.TopupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopUpActivity : AppCompatActivity() {
    lateinit var binding: ActivityTopUpBinding
    lateinit var topupViewModel : TopupViewModel
    lateinit var sharedPref : SharedPreferences
    private var token = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        topupViewModel = ViewModelProvider(this).get(TopupViewModel::class.java)
        sharedPref = this.getSharedPreferences("datauser", Context.MODE_PRIVATE)
        token = sharedPref.getString("token","null").toString()
        doTopUp()
        cancelTopup()
    }

    fun doTopUp() {
        binding.btnTopup.setOnClickListener {
            when{
                binding.amountTopup.text.isNullOrEmpty() -> binding.amountTopup.error ="Amount is required"
                else ->{
                    if (binding.amountTopup.text.toString().toInt() < 10000){
                        alertMinimum()
                    }else{
                        topUp()
                    }
                }
            }
        }
    }

    fun topUp(){
        topupViewModel.postTopUp("Bearer "+token , TopUp(binding.amountTopup.text.toString().toInt()))
        topupViewModel.topUp.observe(this){
            if (it != null){
                AlertDialog.Builder(this)
                    .setMessage("Top Up balance ${binding.amountTopup.text.toString()} Succeed")
                    .setPositiveButton("Ok"){ dialogInterface: DialogInterface, i: Int ->
                        finish()
                    }
                    .show()
            }
        }
    }

    fun alertMinimum(){
        AlertDialog.Builder(this)
            .setIcon(R.drawable.icon_top_up)
            .setMessage("Minimum top up is Rp 10.000")
            .setPositiveButton("Ok"){ dialogInterface: DialogInterface, i: Int ->
            }
            .show()
    }

    fun cancelTopup(){
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

}