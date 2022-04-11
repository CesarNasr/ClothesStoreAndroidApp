package com.example.clothesstoreapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.clothesstoreapp.databinding.ListItemCatalogueBinding
import com.example.clothesstoreapp.datasource.model.Product

class CatalogueAdapter : RecyclerView.Adapter<CatalogueAdapter.DataViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    private var onItemClickListener: ((Product) -> Unit)? = null

    fun setOnItemClickListener(listener: (Product) -> Unit) {
        onItemClickListener = listener
    }


    private val differ = AsyncListDiffer(this, callback)
    private fun getList() = differ.currentList
    fun setList(list: MutableList<Product>) = differ.submitList(list)

    inner class DataViewHolder(private val binding: ListItemCatalogueBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
            binding.product = item
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ListItemCatalogueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getList()[position])
    }

    override fun getItemId(position: Int): Long {
        return getList()[position].productId.toLong()
    }

    override fun getItemCount(): Int = getList().size
}