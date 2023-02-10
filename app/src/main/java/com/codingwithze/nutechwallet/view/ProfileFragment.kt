package com.codingwithze.nutechwallet.view

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.codingwithze.nutechwallet.R
import com.codingwithze.nutechwallet.databinding.FragmentProfileBinding
import com.codingwithze.nutechwallet.viewmodel.ProfileViewModel
import com.codingwithze.nutechwallet.viewmodel.ProtoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    lateinit var binding : FragmentProfileBinding
    lateinit var protoViewModel : ProtoViewModel
    lateinit var profileViewModel : ProfileViewModel
    lateinit var sharedPref : SharedPreferences
    private var token = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        protoViewModel = ViewModelProvider(this).get(ProtoViewModel::class.java)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        sharedPref = requireContext().getSharedPreferences("datauser", Context.MODE_PRIVATE)
        logout()
        token = sharedPref.getString("token","null").toString()
        showProfile()
        doUpdateProfile()
    }

    fun showProfile(){
        profileViewModel.getProfile("Bearer "+ token)
        profileViewModel.profileUser.observe(viewLifecycleOwner){
            if (it != null){
                binding.emailProfile.setText(it.data.email)
                binding.firstNameProfile.setText(it.data.firstName)
                binding.lastNameProfile.setText(it.data.lastName)
            }
        }
    }

    fun doUpdateProfile(){
        binding.btnUpdate.setOnClickListener {
            when{
                binding.firstNameProfile.text.isNullOrEmpty() -> binding.firstNameProfile.error ="you can't send the empty name"
                binding.lastNameProfile.text.isNullOrEmpty() -> binding.lastNameProfile.error ="you can't send the empty data"
                else ->{
                    updateProfile()
                    showProfile()
                }
            }
        }
    }

    fun updateProfile(){
        profileViewModel.postUpdateProfile("Bearer "+token, binding.firstNameProfile.text.toString(), binding.lastNameProfile.text.toString())
        profileViewModel.profileUser.observe(viewLifecycleOwner){
            if (it != null){
                AlertDialog.Builder(requireContext())
                    .setMessage("Update profile succeed")
                    .setPositiveButton("Ok"){ dialogInterface: DialogInterface, i: Int -> }
                    .show()
            }
        }
    }

    fun logout(){
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setIcon(R.drawable.ic_logout)
                .setMessage("Are you sure to logout from this app??")
                .setPositiveButton("Yes"){ dialogInterface: DialogInterface, i: Int ->
                    val logOut = sharedPref.edit()
                    logOut.clear()
                    logOut.apply()
                    requireActivity().finish()
                    startActivity(Intent(context, LoginActivity::class.java))
                }
                .setNegativeButton("No"){ dialogInterface: DialogInterface, i: Int ->
                }
                .show()
        }
    }

}