package com.example.movieapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.use_case.LoginUseCase
import com.example.movieapp.domain.use_case.RegisterUserUseCase
import com.example.movieapp.presentation.util.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginViewModel(
    private val useCase: RegisterUserUseCase,
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginViewState>(LoginViewState()) {

    fun onRegisterSelected(email: String, password: String,auth: FirebaseAuth) {
        updateViewState {
            onLoading()
        }
        viewModelScope.launch {
            useCase(email, password, auth){
                if (it) updateViewState { onRegister(UserRegistered(viewState.value?.email ?: "", viewState.value?.password ?: "")) }
                else updateViewState { setRegisterError("No se pudo realizar el registro. Verifique que la contraseña tenga al menos 6 dígitos y el usuario no exista en la base de datos.") }
            }
        }
    }

    fun onLogin(email: String, password: String,auth: FirebaseAuth) {
        updateViewState {
            onLoading()
        }
        viewModelScope.launch {
            loginUseCase(email, password, auth){
                if (it) updateViewState { onLoginSuccessfull(UserRegistered(email, password))}
                else updateViewState { setRegisterError("Usuario no encontrado, verifique que el usuario exista o registrelo por primera vez.") }
            }
        }
    }

    fun onEmailTyped(email: String) {
        updateViewState {
            updateEmail(email)
        }
    }
    fun onPasswordTyped(password: String) {
        updateViewState {
            updatePassword(password)
        }
    }

    fun cleanLogin() {
        updateViewState { cleanLogin() }
    }

    fun cleanRegisterValue() {
        updateViewState { cleanRegister() }
    }

    fun cleanRegisterError() {
        updateViewState { setRegisterError() }
    }
}

data class LoginViewState(
    val isLoading: Boolean = false,
    val email: String? = null,
    val password: String? = null,
    val onRegister: UserRegistered? = null,
    val isLoginSuccessfull: UserRegistered? = null,
    val hasError: String? = null
) {
    fun onLoading() = copy(isLoading = true)

    fun onRegister(userRegistered: UserRegistered) = copy(
        onRegister = userRegistered, isLoading = false, isLoginSuccessfull = null,
        hasError = null
    )


    fun onLoginSuccessfull(userRegistered: UserRegistered) = copy(
        isLoginSuccessfull = userRegistered,
        isLoading = false,
        hasError = null
        )

    fun cleanLogin() = copy(isLoginSuccessfull = null)

    fun cleanRegister() = copy(onRegister = null)

    fun setLoginError(state: String? = null) = copy(hasError = state)

    fun setRegisterError(state: String? = null) = copy(hasError = state)

    fun updateEmail(email: String) = copy(email = email)

    fun updatePassword(password: String) = copy(password = password)
}

data class UserRegistered(
    val email: String,
    val password: String,
)