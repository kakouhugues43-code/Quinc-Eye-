package com.quinceye.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.quinceye.app.R
import com.quinceye.app.data.AppDatabase
import com.quinceye.app.data.entities.Sale
import com.quinceye.app.data.entities.SaleItem
import com.quinceye.app.data.entities.Product
import kotlinx.coroutines.launch

class SaleActivity : AppCompatActivity() {
    private val items = mutableListOf<SaleItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sale)

        val clientSpinner = findViewById<Spinner>(R.id.spinner_client)
        val prodIdEt = findViewById<EditText>(R.id.et_product_id)
        val qtyEt = findViewById<EditText>(R.id.et_qty)
        val addBtn = findViewById<Button>(R.id.btn_add_item)
        val itemsTv = findViewById<TextView>(R.id.tv_items)
        val saveBtn = findViewById<Button>(R.id.btn_save_sale)

        val db = AppDatabase.getInstance(applicationContext)

        // load clients into spinner
        lifecycleScope.launch {
            val clients = db.clientDao().getAll().asLiveData().value ?: emptyList()
            val names = ArrayList<String>()
            names.add("Anonymous")
            names.addAll(clients.map { it.name })
            val adapter = ArrayAdapter(this@SaleActivity, android.R.layout.simple_spinner_item, names)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            clientSpinner.adapter = adapter
        }

        addBtn.setOnClickListener {
            val prodId = prodIdEt.text.toString().toLongOrNull()
            val qty = qtyEt.text.toString().toIntOrNull() ?: 0
            if (prodId == null || qty <= 0) {
                Toast.makeText(this, "ID produit et quantité valides requis", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val p = db.productDao().getById(prodId)
                if (p == null) {
                    runOnUiThread { Toast.makeText(this@SaleActivity, "Produit introuvable", Toast.LENGTH_SHORT).show() }
                    return@launch
                }
                if (p.quantity < qty) {
                    runOnUiThread { Toast.makeText(this@SaleActivity, "Stock insuffisant", Toast.LENGTH_SHORT).show() }
                    return@launch
                }
                val item = SaleItem(productId = p.id, qty = qty, price = p.price)
                items.add(item)
                runOnUiThread { itemsTv.text = items.joinToString("\n") { "Prod:${it.productId} x${it.qty} @ ${it.price}" } }
            }
        }

        saveBtn.setOnClickListener {
            if (items.isEmpty()) {
                Toast.makeText(this, "Ajoute au moins un article", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                val selectedClientName = clientSpinner.selectedItem as String
                val clientId: Long? = null // extension: map name->id if clients loaded
                val total = items.sumOf { it.qty * it.price }

                // run in transaction
                db.runInTransaction {
                    val saleId = runCatching { db.saleDao().insert(Sale(clientId = clientId, total = total)) }.getOrNull() ?: 0L
                    items.forEach { it.saleId = saleId }
                    items.forEach { item ->
                        runCatching { db.saleItemDao().insert(item) }
                        // update product quantity
                        val p = runCatching { db.productDao().getById(item.productId) }.getOrNull()
                        p?.let {
                            val newQty = it.quantity - item.qty
                            runCatching { db.productDao().updateQuantity(it.id, newQty) }
                        }
                    }
                }
                runOnUiThread {
                    Toast.makeText(this@SaleActivity, "Vente enregistrée (total: $total)", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }
}
