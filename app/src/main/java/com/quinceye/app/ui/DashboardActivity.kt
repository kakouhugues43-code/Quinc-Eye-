package com.quinceye.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.quinceye.app.R
import com.quinceye.app.data.AppDatabase
import kotlinx.coroutines.launch
import java.text.NumberFormat

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val tvStats = findViewById<TextView>(R.id.tv_stats)
        val db = AppDatabase.getInstance(applicationContext)

        lifecycleScope.launch {
            val prodCount = db.productDao().count()
            val clientCount = db.clientDao().count()
            val salesCount = db.saleDao().count()
            val revenue = db.saleDao().totalRevenue() ?: 0.0
            val nf = NumberFormat.getCurrencyInstance()
            runOnUiThread {
                tvStats.text = "Produits: $prodCount\nClients: $clientCount\nVentes: $salesCount\nChiffre d'affaires: ${nf.format(revenue)}"
            }
        }
    }
}
