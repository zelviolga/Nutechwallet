package com.codingwithze.nutechwallet.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.auth0.android.jwt.JWT
import com.codingwithze.nutechwallet.data.Login
import com.codingwithze.nutechwallet.databinding.ActivityLoginBinding
import com.codingwithze.nutechwallet.viewmodel.LoginViewModel
import com.codingwithze.nutechwallet.viewmodel.ProtoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    lateinit var loginVM : LoginViewModel
    lateinit var protoVM : ProtoViewModel
    lateinit var sharedPref : SharedPreferences

    var token : String?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        loginVM = ViewModelProvider(this).get(LoginViewModel::class.java)
        protoVM = ViewModelProvider(this).get(ProtoViewModel::class.java)
        sharedPref = this.getSharedPreferences("datauser", Context.MODE_PRIVATE)

        gotoSignUp()
        initListener()
    }



    fun initListener(){
        binding.btnLogin.setOnClickListener {
            when{
                binding.etEmailLogin.text.isNullOrEmpty() -> binding.etEmailLogin.error = " Email is required"
                binding.etPasswordLogin.text.isNullOrEmpty() -> binding.etPasswordLogin.error = "Password is required"
                else -> {
                    doLogin()
                }
            }
        }
    }

    fun doLogin(){
        loginVM.authLogin(Login(binding.etEmailLogin.text.toString(),binding.etPasswordLogin.text.toString()))
        loginVM.userLogin.observe(this){
            if (it!=null){
                Log.i("tokenn", "token: ${it.data.token}")
                token =it.data.token
                // input to sharedpreferences
                val userData = sharedPref.edit()
                userData.putString("token", it.data.token)
                userData.apply()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

    }

    fun gotoSignUp(){
        binding.txtSignup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}