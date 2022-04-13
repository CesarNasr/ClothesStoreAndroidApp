package com.example.clothesstoreapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothesstoreapp.R
import com.example.clothesstoreapp.databinding.FragmentWishlistBinding
import com.example.clothesstoreapp.datasource.model.Product
import com.example.clothesstoreapp.ui.adapters.WishListAdapter
import com.example.clothesstoreapp.ui.utils.UiState
import com.example.clothesstoreapp.ui.viewmodels.WishlistViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class WishlistFragment : Fragment() {
    private val viewModel: WishlistViewModel by viewModels()
    private lateinit var binding: FragmentWishlistBinding

    @Inject
    lateinit var wishListAdapter: WishListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_wishlist,
            container,
            false
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel.fetchWishListProducts()

        viewModel.fetchWishListUiState.observe(viewLifecycleOwner) {
            if (it is UiState.Loaded) {
                if (it.data.isNullOrEmpty()) {

                } else {
                    wishListAdapter.setList(it.data as MutableList<Product>)
                }
            } else {
                // show toast
            }
        }

        viewModel.addToBasketUiState.observe(viewLifecycleOwner) {
            if (it is UiState.Loaded) {
                it.itemData?.let { item ->
                    wishListAdapter.removeItem(item)
                    Toast.makeText(requireContext(), resources.getString(R.string.moved_to_basket), Toast.LENGTH_SHORT).show()
                }
            } else {
                // show toast
            }
        }
    }

    private val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            Toast.makeText(requireContext(),  resources.getString(R.string.item_deleted), Toast.LENGTH_SHORT).show()
            //Remove swiped item from DB and notify the RecyclerView on Success
            val position = viewHolder.adapterPosition
            viewModel.removeItemFromWishlist(wishListAdapter.dataList[position]) {
                wishListAdapter.removeItemAt(position)
                viewModel.toggleEmptyView(wishListAdapter.dataList.isNullOrEmpty())

            }
        }
    }


    private fun initRecyclerView() {
        binding.wishList.apply {
            adapter = wishListAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        wishListAdapter.setOnItemClickListener {
            viewModel.addItemToBasket(it)
        }


        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.wishList)
    }


    companion object {
        @JvmStatic
        fun newInstance() = WishlistFragment()
    }
}