package com.codingwithze.nutechwallet.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.codingwithze.nutechwallet.data.User
import com.codingwithze.nutechwallet.databinding.ActivityRegisterBinding
import com.codingwithze.nutechwallet.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    lateinit var binding : ActivityRegisterBinding
    lateinit var registerVM : RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        goToLogin()
        registerVM = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.btnSignUp.setOnClickListener {
            when{
                binding.etEmail.text.isNullOrEmpty() -> binding.etEmail.error = "Email is required"
                binding.etFirstName.text.isNullOrEmpty() -> binding.etFirstName.error = "First name is required"
                binding.etLastName.text.isNullOrEmpty() -> binding.etLastName.error ="Last name is required"
                binding.etPassword.text.isNullOrEmpty() -> binding.etPassword.error = "Password is required"
                else ->{
                    doRegistration()
                    finish()
                }
            }

        }
    }

    fun doRegistration(){
        val email = binding.etEmail.text.toString()
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val password = binding.etPassword.text.toString()

        registerVM.registerUser(User(email,firstName,lastName,password))
        registerVM.statusRegist.observe(this){
            if (it != null){
                Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,LoginActivity::class.java))
            }
        }
    }

    fun goToLogin(){
        binding.txtLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}