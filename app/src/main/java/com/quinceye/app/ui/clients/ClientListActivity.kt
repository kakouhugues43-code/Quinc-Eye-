package com.quinceye.app.ui.clients

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.quinceye.app.R
import com.quinceye.app.data.AppDatabase
import com.quinceye.app.ui.adapter.ClientAdapter
import com.quinceye.app.ui.ClientDetailActivity

class ClientListActivity : AppCompatActivity() {
    private lateinit var viewModel: ClientListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_list)

        val db = AppDatabase.getInstance(applicationContext)
        val factory = ClientListViewModel.Factory(db.clientDao())
        viewModel = ViewModelProvider(this, factory).get(ClientListViewModel::class.java)

        val recycler = findViewById<RecyclerView>(R.id.clientsRecycler)
        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = ClientAdapter { client ->
            val intent = Intent(this, ClientDetailActivity::class.java)
            intent.putExtra("client_id", client.id)
            startActivity(intent)
        }
        recycler.adapter = adapter

        viewModel.clients.observe(this) { list ->
            adapter.submitList(list)
        }

        findViewById<FloatingActionButton>(R.id.fab_add_client).setOnClickListener {
            startActivity(Intent(this, ClientDetailActivity::class.java))
        }
    }
}
