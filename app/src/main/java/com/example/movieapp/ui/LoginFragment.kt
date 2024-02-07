package com.example.movieapp.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentLoginBinding
import com.example.movieapp.presentation.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    private val viewModel by viewModel<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observers()
    }

    private fun observers() {
        viewModel.viewState.observe(viewLifecycleOwner){ state ->
            state.hasError?.let {
                viewModel.cleanRegisterError()
                showError(it)
            }

            state?.isLoginSuccessfull?.let {
                viewModel.cleanLogin()
                findNavController().navigate(R.id.action_loginFragment_to_moviesListFragment,
                    bundleOf(MoviesListFragment.EMAIL_KEY to it.email)
                )
            }

            state.onRegister?.let {
                viewModel.cleanRegisterValue()
                showSuccess()
            }
        }
    }

    private fun setupViews() = with(binding) {
        binding.emailTextInput.doOnTextChanged { text, start, before, count ->
            viewModel.onEmailTyped(text.toString())
        }
        binding.passwordTextInput.doOnTextChanged { text, start, before, count ->
            viewModel.onPasswordTyped(text.toString())
        }

        registerButton.setOnClickListener {
            val auth = (requireActivity() as MainActivity).auth
            viewModel.onRegisterSelected(emailTextInput.text.toString(), passwordTextInput.text.toString(), auth)
        }

        loginButton.setOnClickListener {
            val auth = (requireActivity() as MainActivity).auth
            viewModel.onLogin(emailTextInput.text.toString(), passwordTextInput.text.toString(), auth)
        }
    }

    fun showError(msg: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage(msg)
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    fun showSuccess() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Registrado")
        builder.setMessage("El usuario ha sido registrado correactamente. Puede iniciar sesión")
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}