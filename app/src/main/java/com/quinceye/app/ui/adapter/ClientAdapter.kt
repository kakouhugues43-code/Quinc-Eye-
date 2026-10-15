package com.quinceye.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.quinceye.app.R
import com.quinceye.app.data.entities.Client

class ClientAdapter(private val onClick: (Client) -> Unit) :
    ListAdapter<Client, ClientAdapter.ClientViewHolder>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Client>() {
            override fun areItemsTheSame(oldItem: Client, newItem: Client) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Client, newItem: Client) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_client, parent, false)
        return ClientViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ClientViewHolder(view: View, val onClick: (Client) -> Unit) : RecyclerView.ViewHolder(view) {
        private val nameTv: TextView = view.findViewById(R.id.tv_client_name)
        private val infoTv: TextView = view.findViewById(R.id.tv_client_info)
        private var current: Client? = null

        init {
            view.setOnClickListener {
                current?.let { onClick(it) }
            }
        }

        fun bind(c: Client) {
            current = c
            nameTv.text = c.name
            infoTv.text = (c.phone ?: "") + (if (c.email != null) " | ${c.email}" else "")
        }
    }
}
