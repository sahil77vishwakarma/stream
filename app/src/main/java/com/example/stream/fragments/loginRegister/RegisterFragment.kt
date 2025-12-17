package com.example.stream.fragments.loginRegister

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.stream.R
import com.example.stream.data.User
import com.example.stream.databinding.FragmentRegisterBinding
import com.example.stream.util.RegisterValidation
import com.example.stream.util.Resource
import com.example.stream.util.validateEmail
import com.example.stream.viewmodels.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val TAG = "RegisterFragment"
@AndroidEntryPoint
class RegisterFragment: Fragment(R.layout.fragment_register) {

    private lateinit var binding : FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDoHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.apply {
            buttonRegisterRegister.setOnClickListener {
                    val user = User(
                        edFirstName.text.toString().trim(),
                        edLastName.text.toString().trim(),
                        edEmailRegister.text.toString().trim()
                    )

                 val password = edPasswordRegister.text.toString()
                viewModel.createAccountWithEmailAndPassword(user, password)
            }

        }



        lifecycleScope.launchWhenStarted {
                viewModel.register.collect {
                    when (it){
                        is Resource.Loading ->{
                            binding.buttonRegisterRegister.startAnimation()
                        }
                        is Resource.Success ->{
                            Log.d("Test", it.data.toString())
                            binding.buttonRegisterRegister.revertAnimation()
                        }
                        is Resource.Error -> {
                            Log.e(TAG, it.message.toString())
                            binding.buttonRegisterRegister.revertAnimation()
                        }
                        else -> Unit
                    }
                }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.validation.collect{validation ->
                if(validation.email is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.edEmailRegister.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                }

                if(validation.password is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.edPasswordRegister.apply{
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }

                if(validation.name is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        if(validation.name.check == 0)
                        {
                            binding.edFirstName.apply{
                                requestFocus()
                                error = validation.name.message
                            }
                        }else if(validation.name.check == 1)
                        {
                            binding.edLastName.apply{
                                requestFocus()
                                error = validation.name.message
                            }
                        }else if(validation.name.check == 2)
                        {
                            binding.edFirstName.apply{
                                requestFocus()
                                error = validation.name.message
                            }
                            binding.edLastName.apply{
                                requestFocus()
                                error = validation.name.message
                            }
                        }
                    }
                }
            }
        }
    }
}