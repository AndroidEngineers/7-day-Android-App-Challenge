package com.android.engineer.mealmate.view.features.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.engineer.mealmate.data.local.datastore.MealDataStore
import com.android.engineer.mealmate.data.utils.USERNAME
import com.android.engineer.mealmate.repository.local.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStore: MealDataStore,
    private val userRepository: UserRepository
): ViewModel() {

    private var _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private var _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    init {
        viewModelScope.launch {
            _userName.value = dataStore.getString(USERNAME) ?: ""
            if(_userName.value.isNotEmpty()) {
                _email.value = getEmailByUserName(_userName.value)
            }
        }
    }

    private fun getEmailByUserName(userName: String): String {
        var email = ""
        viewModelScope.launch {
            email = userRepository.getEmail(userName)
        }
        return email
    }

    fun onLogoutClicked(onLogoutClicked: () -> Unit) {
        viewModelScope.launch {
            /*dataStore.clearPreference()
            onLogoutClicked()*/
        }
    }

}