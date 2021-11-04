package com.shahjahan.thegrocer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopkart.ui.home.ItemAdapter
import com.example.shopkart.ui.home.ItemAdapter.Actionlistener
import com.shahjahan.thegrocer.R
import com.shahjahan.thegrocer.models.ProductModel
import com.shahjahan.thegrocer.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), Actionlistener{

    private val viewModel: HomeViewModel by viewModels()

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView4);
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        progressBar =  view.findViewById(R.id.progressBar);

        viewModel.products.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = ItemAdapter(it, this);
        })


        viewModel.status.observe(viewLifecycleOwner, Observer {
            when (it) {
                HomeViewModel.ApiStatus.LOADING -> progressBar.visibility = View.VISIBLE
                HomeViewModel.ApiStatus.ERROR -> progressBar.visibility = View.GONE
                HomeViewModel.ApiStatus.DONE -> progressBar.visibility = View.GONE
            }
        })
        return view
    }

    override fun onAddTocCart(item: ProductModel) {
        Toast.makeText(context, "Add to cart: "+item.title, Toast.LENGTH_SHORT).show()
        viewModel.addToCart(Integer.parseInt(item.id))
    }

    override fun onShowProductDetail(item: ProductModel) {
        Toast.makeText(context, "Details: "+item.title, Toast.LENGTH_SHORT).show()
        val bundle = bundleOf("productId" to item.id.toInt())
        findNavController().navigate(R.id.action_navigation_home_to_productDetailFragment, bundle)
    }


}
