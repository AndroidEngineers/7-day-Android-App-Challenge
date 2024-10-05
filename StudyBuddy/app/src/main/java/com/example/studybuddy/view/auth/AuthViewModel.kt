package com.example.studybuddy.view.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studybuddy.domain.model.UserData
import com.example.studybuddy.utils.User_Node
import com.example.studybuddy.utils.handleException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    val _authState = MutableLiveData<AuthState>()
    val authState:LiveData<AuthState> = _authState
    var inProcess = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)


    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if (auth.currentUser!=null)
        {
            _authState.value = AuthState.Authenticated
        }
        else{
            _authState.value=AuthState.Unauthenticated
        }
    }

    fun login(email:String, password:String)
    {
        if (email.isEmpty()||password.isEmpty())
        {
            _authState.value = AuthState.Error("Please fill all the credentials before login")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {task ->
                if (task.isSuccessful)
                {
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Something went wrong")
                }
            }
    }

    fun signUp(email: String, password: String, name: String, premiumMember: Boolean,number: String?,expiry: Long?) {
        if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {

            _authState.value = AuthState.Error("Please fill all the credentials before signing up")
            return
        }

        inProcess.value=true

        firestore.collection(User_Node).whereEqualTo("number" , number).get().addOnSuccessListener {
            if (it.isEmpty){
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            createUser(name,number,premiumMember, expiry)
                        } else {
                            _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                        }
                    }
            }
            else{
                handleException("AuthViewModel","Phone number already exist",null)
                inProcess.value = false
            }
            }

        }



    fun createUser(name:String? = null, number:String? = null,premiumMember: Boolean? = null,expiry:Long? =null){
        var uid = auth.currentUser?.uid
        val userData = UserData(
            userId = uid,
            name = name?: userData.value?.name,
            number = number?: userData.value?.number,
            PremiumMember = premiumMember?:userData.value?.PremiumMember,
            expiry = expiry?: userData.value?.expiry
        )
        uid?.let {
            inProcess.value = true

            firestore.collection(User_Node).document(uid).get().addOnSuccessListener {
                if (it.exists()){
                    handleException("AuthViewModel","User already exist",null)
                    inProcess.value = false
                    getUserData(uid)

                }

                else{
                    firestore.collection(User_Node).document(uid).set(userData)
                    inProcess.value = false
                }
            }.addOnFailureListener {
                handleException("AuthViewModel","User can't retrieved",null)
            }

        }


    }

    private fun getUserData(uid:String) {
        inProcess.value = true
        firestore.collection(User_Node).document(uid).addSnapshotListener{
            value, error->
            if (error!=null)
            {
                handleException("AuthViewModel","Something went wrong",error)
            }
            if (value != null){
                var  user = value.toObject<UserData>()
                userData.value = user
                inProcess.value = false
            }
        }
    }

    fun logout(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    fun checkSubscriptionStatus(onResult: (Boolean) -> Unit) {

    }



}


sealed class AuthState{
    data object Authenticated: AuthState()
    data object Unauthenticated: AuthState()
   data object Loading: AuthState()

    data object RegisterSuccess: AuthState()
    data class Error(val message: String): AuthState()
}