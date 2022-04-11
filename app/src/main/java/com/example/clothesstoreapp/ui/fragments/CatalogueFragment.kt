package com.example.clothesstoreapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.clothesstoreapp.R
import com.example.clothesstoreapp.databinding.FragmentCatalogueBinding
import com.example.clothesstoreapp.ui.adapters.CatalogueAdapter
import com.example.clothesstoreapp.ui.viewmodels.CatalogueViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CatalogueFragment : Fragment() {
    private lateinit var binding: FragmentCatalogueBinding
    private val viewModel: CatalogueViewModel by viewModels()

    @Inject
    lateinit var catalogueAdapter: CatalogueAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_catalogue,
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

        viewModel.productList.observe(viewLifecycleOwner) {
            catalogueAdapter.setList(it)
        }

        viewModel.uiState.observe(viewLifecycleOwner) {
            // show error dialog if needed
        }
    }


    private fun initRecyclerView() {
        binding.itemsRecyclerview.apply {
            adapter = catalogueAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }

        catalogueAdapter.setOnItemClickListener {
            val bottomSheet = ProductBottomSheet.newInstance(it)
            bottomSheet.show(
                requireActivity().supportFragmentManager,
                "ModalBottomSheet"
            )

        }


    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CatalogueFragment()
    }
}