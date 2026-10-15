package com.quinceye.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.quinceye.app.R
import com.quinceye.app.data.Product

class ProductAdapter(private val onClick: (Product) -> Unit) :
    ListAdapter<Product, ProductAdapter.ProductViewHolder>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductViewHolder(view: View, val onClick: (Product) -> Unit) : RecyclerView.ViewHolder(view) {
        private val nameTv: TextView = view.findViewById(R.id.tv_name)
        private val qtyTv: TextView = view.findViewById(R.id.tv_qty)
        private var current: Product? = null

        init {
            view.setOnClickListener {
                current?.let { onClick(it) }
            }
        }

        fun bind(p: Product) {
            current = p
            nameTv.text = p.name
            qtyTv.text = "Stock: ${p.quantity} | Prix: ${p.price}"
        }
    }
}
