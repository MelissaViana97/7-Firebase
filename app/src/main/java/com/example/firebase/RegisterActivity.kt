package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    //creamos la referencia dsel objeto firebaseauth
    private lateinit var auth:FirebaseAuth
    //referencia a componentes de nuestro layout
    private lateinit var buttonRegister:Button
    private lateinit var textViewLogin:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //inicializamos el objeto firebaseauth
        auth=FirebaseAuth.getInstance()
    }
    private fun register(email: String, password: String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{task ->
            if (task.isSuccessful){
                    val intent=Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener{exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }

    }
    private fun goToLogin(){
        val intent=Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //inicializamos el objeto firebaseauth
        auth=FirebaseAuth.getInstance()

        buttonRegister=findViewById<Button>(R.id.btnRegister)
        buttonRegister.setOnClickListener{
            val email=findViewById<EditText>(R.id.txtEmail).text.toString()
            //val email=editTextEmai8lAddress.text.toString()
            val  password=findViewById<EditText>(R.id.txtPass).text.toString()
            this.register(email, password)
        }
        textViewLogin=findViewById<TextView>(R.id.textViewlogin)
        textViewLogin.setOnLongClickListener{
            this.goToLogin()
        }
    }


}