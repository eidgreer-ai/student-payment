package com.eidgreer.studentpayment.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eidgreer.studentpayment.utils.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(password: String) {
        viewModelScope.launch {
            try {
                val savedPassword = preferencesManager.getPassword()
                if (savedPassword == null) {
                    _loginState.value = LoginState.Error("لم يتم تعيين كلمة مرور. يرجى التواصل مع المسؤول")
                    return@launch
                }

                if (password == savedPassword) {
                    _loginState.value = LoginState.Success
                } else {
                    _loginState.value = LoginState.Error("كلمة المرور غير صحيحة")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "حدث خطأ غير متوقع")
            }
        }
    }

    fun resetState() {
        _loginState.value = LoginState.Idle
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}
