package com.quinceye.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.quinceye.app.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_products).setOnClickListener {
            startActivity(Intent(this, ProductListActivity::class.java))
        }

        findViewById<Button>(R.id.btn_clients).setOnClickListener {
            startActivity(Intent(this, ClientListActivity::class.java))
        }

        findViewById<Button>(R.id.btn_sales).setOnClickListener {
            startActivity(Intent(this, SaleActivity::class.java))
        }

        findViewById<Button>(R.id.btn_dashboard).setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
    }
}
