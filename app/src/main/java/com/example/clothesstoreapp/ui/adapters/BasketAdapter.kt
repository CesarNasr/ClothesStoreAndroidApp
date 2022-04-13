package com.example.clothesstoreapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clothesstoreapp.databinding.ListItemBasketBinding
import com.example.clothesstoreapp.databinding.ListItemWishlistBinding
import com.example.clothesstoreapp.datasource.model.Product

class BasketAdapter : RecyclerView.Adapter<BasketAdapter.DataViewHolder>() {
    var dataList = mutableListOf<Product>()

    private var onItemClickListener: ((Product) -> Unit)? = null

    fun setOnItemClickListener(listener: (Product) -> Unit) {
        onItemClickListener = listener
    }


    private fun getList() = dataList
    fun setList(list: MutableList<Product>) {
        dataList = list
        notifyDataSetChanged()
    }

    fun removeItem(item: Product) {
        dataList.remove(item)
        notifyDataSetChanged()
    }

    inner class DataViewHolder(private val binding: ListItemBasketBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
            binding.product = item
            binding.executePendingBindings()
//            binding.addToBasket.setOnClickListener {
//                onItemClickListener?.invoke(item)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ListItemBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getList()[position])
    }

    override fun getItemId(position: Int): Long {
        return getList()[position].productId.toLong()
    }

    override fun getItemCount(): Int = getList().size
}