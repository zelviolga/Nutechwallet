package com.codingwithze.nutechwallet.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.auth0.android.jwt.JWT
import com.codingwithze.nutechwallet.databinding.ActivitySplashBinding
import com.codingwithze.nutechwallet.viewmodel.ProtoViewModel

class SplashActivity : AppCompatActivity() {
    lateinit var binding : ActivitySplashBinding
    lateinit var protoViewModel: ProtoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        protoViewModel = ViewModelProvider(this).get(ProtoViewModel::class.java)


        Handler().postDelayed({
            if (this.getSharedPreferences("datauser" , Context.MODE_PRIVATE)!!.contains("token")){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }
        },3000)
    }
}