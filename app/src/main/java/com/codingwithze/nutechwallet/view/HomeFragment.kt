package com.codingwithze.nutechwallet.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.codingwithze.nutechwallet.databinding.FragmentHomeBinding
import com.codingwithze.nutechwallet.viewmodel.BalanceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding
    lateinit var sharedPref : SharedPreferences
    private var token = ""
    lateinit var balanceViewModel : BalanceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container,false)
        return binding.root

    }

    override fun onStart() {
        super.onStart()
        getBalance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireContext().getSharedPreferences("datauser", Context.MODE_PRIVATE)
        token = sharedPref.getString("token","null").toString()
        balanceViewModel = ViewModelProvider(this).get(BalanceViewModel::class.java)
        gotoTopUp()
        getBalance()
        gotoTransactionHistory()

    }


    fun getBalance(){
        balanceViewModel.getBalanceWallet("Bearer "+token)
        balanceViewModel.balance.observe(viewLifecycleOwner){
            if (it != null){
                binding.txtSaldo.text = "Rp. " + it.data.balance.toString()
            }
        }
    }

    fun gotoTopUp(){
        binding.topUp.setOnClickListener {
            startActivity(Intent(requireContext(), TopUpActivity::class.java))
        }
    }

    fun gotoTransactionHistory(){
        binding.cardTransactionHistory.setOnClickListener {
            startActivity(Intent(context, TransactionHistoryActivity::class.java))

        }
    }




}