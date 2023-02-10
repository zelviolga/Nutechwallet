package com.codingwithze.nutechwallet.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.codingwithze.nutechwallet.R
import com.codingwithze.nutechwallet.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        navigationMenu()
        gototransfer()
    }

    fun navigationMenu(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerMain, HomeFragment())
            .commit()
        binding.menuNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.menuhome ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.containerMain, HomeFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menuprofile ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.containerMain, ProfileFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    fun gototransfer(){
        binding.btnTransfer.setOnClickListener {
            startActivity(Intent(this, TransferActivity::class.java))
        }
    }


    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setMessage("Quit from this app?")
            .setPositiveButton("Yes"){ dialogInterface: DialogInterface, i: Int ->
                finishAffinity()
            }
            .setNegativeButton("No"){ dialogInterface: DialogInterface, i: Int ->
            }
            .show()
    }



}