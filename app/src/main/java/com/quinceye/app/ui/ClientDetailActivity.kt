package com.quinceye.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.quinceye.app.R
import com.quinceye.app.data.AppDatabase
import com.quinceye.app.data.entities.Client
import kotlinx.coroutines.launch

class ClientDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_detail)

        val nameEt = findViewById<EditText>(R.id.et_name)
        val phoneEt = findViewById<EditText>(R.id.et_phone)
        val emailEt = findViewById<EditText>(R.id.et_email)
        val addrEt = findViewById<EditText>(R.id.et_address)
        val saveBtn = findViewById<Button>(R.id.btn_save)

        val db = AppDatabase.getInstance(applicationContext)

        saveBtn.setOnClickListener {
            val name = nameEt.text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Nom requis", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val client = Client(name = name, phone = phoneEt.text.toString().trim(), email = emailEt.text.toString().trim(), address = addrEt.text.toString().trim())
            lifecycleScope.launch {
                db.clientDao().insert(client)
                finish()
            }
        }
    }
}
