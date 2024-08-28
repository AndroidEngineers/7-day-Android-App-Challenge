package com.android.engineer.mealmate.view.features.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.engineer.mealmate.data.local.datastore.MealDataStore
import com.android.engineer.mealmate.data.local.roomdb.User
import com.android.engineer.mealmate.data.remote.model.request.RegisterRequest
import com.android.engineer.mealmate.data.utils.IS_LOGGED_IN
import com.android.engineer.mealmate.repository.local.UserRepository
import com.android.engineer.mealmate.repository.remote.AuthRepository
import com.android.engineer.mealmate.view.features.auth.state.VisitingRegisterUiState
import com.android.engineer.mealmate.view.utils.UtilConst
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val dataStore: MealDataStore,
    private val userRepository: UserRepository
): ViewModel() {

    val isLoggedIn = mutableStateOf(false)
    init {
        viewModelScope.launch {
            isLoggedIn.value = dataStore.getBoolean(IS_LOGGED_IN)
        }
    }
    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _firstName = MutableStateFlow("")
    val firstName = _firstName.asStateFlow()

    private val _lastName = MutableStateFlow("")
    val lastName = _lastName.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _userPassword = MutableStateFlow("")
    val userPassword = _userPassword.asStateFlow()

    val userNameErrorMsg = mutableStateOf("")
    val userNameError = mutableStateOf(false)

    val firstNameErrorMsg = mutableStateOf("")
    val firstNameError = mutableStateOf(false)

    val lastNameErrorMsg = mutableStateOf("")
    val lastNameError = mutableStateOf(false)

    val emailErrorMsg = mutableStateOf("")
    val emailError = mutableStateOf(false)

    val passwordErrorMsg = mutableStateOf("")
    val passwordError = mutableStateOf(false)

    val isScreenLoading = mutableStateOf(false)

    fun onSignUpClicked(onCallBack: (VisitingRegisterUiState) -> Unit) {

        if(_userName.value.isEmpty()) {
            userNameErrorMsg.value = "Enter the username"
            userNameError.value = true
            firstNameError.value = false
        } else if(_firstName.value.isEmpty()) {
            firstNameErrorMsg.value = "Enter the firstname"
            firstNameError.value = true
            userNameError.value = false
        } else if(_lastName.value.isEmpty()) {
            lastNameErrorMsg.value = "Enter the lastname"
            lastNameError.value = true
            userNameError.value = false
            firstNameError.value = false
        } else if(_email.value.isEmpty()) {
            emailErrorMsg.value = "Enter the email"
            emailError.value = true
            userNameError.value = false
            firstNameError.value = false
            lastNameError.value = false
        } else {
            userNameError.value = false
            firstNameError.value = false
            lastNameError.value = false
            emailError.value = false
            isScreenLoading.value = true

            val registerRequest = RegisterRequest(
                username = _userName.value,
                firstName = _firstName.value,
                lastName = _lastName.value,
                email = _email.value
            )
            viewModelScope.launch {
                try {
                    val visitRegister = repository.registerAccount(registerRequest)
                    visitRegister.collectLatest { registerResponse ->
                        if (registerResponse.status == "success") {
//                            dataStore.putString(USERNAME, registerResponse.username)
//                            dataStore.putString(PASSWORD, registerResponse.spoonacularPassword)
//                            dataStore.putString(HASH, registerResponse.hash)
                            val user = User(username = registerResponse.username, firstName = _firstName.value, lastName = _lastName.value, email = _email.value, password = registerResponse.spoonacularPassword, hash = registerResponse.hash)
                            val result = userRepository.insertUser(user)
                            if(result > 0) {
                                isScreenLoading.value = false
                                onCallBack(VisitingRegisterUiState.Success())
                                // sendEmail(_email.value, registerResponse.spoonacularPassword)
                                Log.d("testing", "${registerResponse.spoonacularPassword}")

                            } else {
                                isScreenLoading.value = false
                                onCallBack(VisitingRegisterUiState.Error("Error inserting into DB, Try again!"))
                            }
                        }
                    }
                } catch (e: Exception) {
                    Log.d("onResponse", "There is an error")
                    isScreenLoading.value = false
                    e.printStackTrace()
                }
            }
        }
    }

    fun onLoginClicked(onCallBack: (VisitingRegisterUiState) -> Unit) {
        if(_userName.value.isEmpty()) {
            userNameErrorMsg.value = "Enter the username"
            userNameError.value = true
            passwordError.value = false
        } else if(_userPassword.value.isEmpty()) {
            passwordErrorMsg.value = "Enter the password"
            passwordError.value = true
            userNameError.value = false
        } else {
            userNameError.value = false
            passwordError.value = false
            isScreenLoading.value = true

            viewModelScope.launch {
                try {
                    val user = userRepository.getUser(_userName.value, _userPassword.value)

                    if(user != null) {
                        dataStore.putBoolean(IS_LOGGED_IN, true)
                        isScreenLoading.value = false
                        onCallBack(VisitingRegisterUiState.Success())
                    } else {
                        isScreenLoading.value = false
                        onCallBack(VisitingRegisterUiState.Error("Invalid username or password"))
                    }

                } catch (e: Exception) {
                    Log.d("onLoginClicked", "There is an error")
                    isScreenLoading.value = false
                    e.printStackTrace()
                    onCallBack(VisitingRegisterUiState.Error("An error occurred, please try again"))
                }
            }

        }
    }

    fun onValueChanged(authEnum: UtilConst.AuthEnum, newValue: String) {
        when(authEnum) {
            UtilConst.AuthEnum.USERNAME -> { _userName.value = newValue }
            UtilConst.AuthEnum.FIRSTNAME -> { _firstName.value = newValue }
            UtilConst.AuthEnum.LASTNAME -> { _lastName.value = newValue }
            UtilConst.AuthEnum.EMAIL -> { _email.value = newValue }
            UtilConst.AuthEnum.PASSWORD -> { _userPassword.value = newValue }
        }
    }




}