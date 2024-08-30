package com.merp.jet.my.pdf.reader.app.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("FIREBASE", "signInWithEmailAndPassword PASS: ${task.result}")
                            home()
                        } else {
                            Log.d("FIREBASE", "signInWithEmailAndPassword FAIL: ${task.result}")
                        }
                    }
            } catch (ex: Exception) {
                Log.d("FIREBASE", "signInWithEmailAndPassword: ${ex.message}")
            }

        }
}