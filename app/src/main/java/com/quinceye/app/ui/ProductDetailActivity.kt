package com.quinceye.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.quinceye.app.R
import com.quinceye.app.data.AppDatabase
import com.quinceye.app.data.Product
import kotlinx.coroutines.launch

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val nameEt = findViewById<EditText>(R.id.et_name)
        val skuEt = findViewById<EditText>(R.id.et_sku)
        val priceEt = findViewById<EditText>(R.id.et_price)
        val qtyEt = findViewById<EditText>(R.id.et_qty)
        val saveBtn = findViewById<Button>(R.id.btn_save)

        val db = AppDatabase.getInstance(applicationContext)

        saveBtn.setOnClickListener {
            val name = nameEt.text.toString().trim()
            val sku = skuEt.text.toString().trim().ifEmpty { null }
            val price = priceEt.text.toString().toDoubleOrNull() ?: 0.0
            val qty = qtyEt.text.toString().toIntOrNull() ?: 0

            val p = Product(name = name, sku = sku, price = price, quantity = qty)

            lifecycleScope.launch {
                db.productDao().insert(p)
                finish()
            }
        }
    }
}
