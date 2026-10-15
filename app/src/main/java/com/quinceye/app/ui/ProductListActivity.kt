package com.quinceye.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.recyclerview.widget.RecyclerView
import com.quinceye.app.R
import com.quinceye.app.data.AppDatabase
import com.quinceye.app.ui.adapter.ProductAdapter

class ProductListActivity : AppCompatActivity() {
    private lateinit var viewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val db = AppDatabase.getInstance(applicationContext)
        val factory = ProductListViewModel.Factory(db.productDao())
        viewModel = ViewModelProvider(this, factory).get(ProductListViewModel::class.java)

        val recycler = findViewById<RecyclerView>(R.id.productsRecycler)
        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = ProductAdapter { product ->
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product_id", product.id)
            startActivity(intent)
        }
        recycler.adapter = adapter

        viewModel.products.observe(this) { list ->
            adapter.submitList(list)
        }

        findViewById<FloatingActionButton>(R.id.fab_add).setOnClickListener {
            val intent = Intent(this, ProductDetailActivity::class.java)
            startActivity(intent)
        }
    }
}
