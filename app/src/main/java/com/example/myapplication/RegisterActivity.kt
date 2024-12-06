package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            validateAndRegister()
        }
    }

    private fun validateAndRegister() {
        val username = binding.usernameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        val phone = binding.phoneNumberEditText.text.toString()
        val iin = binding.iinEditText.text.toString()

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty() || iin.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Введите правильный адрес электронной почты", Toast.LENGTH_SHORT).show()
            return
        }

        if (phone.length < 10 || phone.length > 15) {
            Toast.makeText(this, "Введите правильный номер телефона", Toast.LENGTH_SHORT).show()
            return
        }

        if (iin.length != 12) {
            Toast.makeText(this, "Введите правильный ИИН", Toast.LENGTH_SHORT).show()
            return
        }

        if (!password.matches(".*[A-Z].*".toRegex()) || !password.matches(".*\\d.*".toRegex()) || password.length !in 8..16) {
            Toast.makeText(this, "Пароль должен содержать хотя бы одну заглавную букву, цифру и быть длиной от 8 до 16 символов", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Регистрация успешна!", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}