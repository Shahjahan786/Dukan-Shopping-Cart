package com.shahjahan.thegrocer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopkart.ui.home.ItemAdapter
import com.example.shopkart.ui.home.ItemAdapter.Actionlistener
import com.shahjahan.thegrocer.R
import com.shahjahan.thegrocer.db.entities.Product
import com.shahjahan.thegrocer.models.ProductModel
import com.shahjahan.thegrocer.utility.bindImage
import com.shahjahan.thegrocer.viewmodels.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class ProductDetailFragment : Fragment(){

    lateinit var viewModel: HomeViewModel
    lateinit var itemName: TextView
    lateinit var itemPrice: TextView
    lateinit var itemImage: ImageView
    lateinit var itemDetail: ImageView
    lateinit var itemDescription:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel { parametersOf() }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_details, container, false)
        itemName = view.findViewById(R.id.item_name)
        itemPrice = view.findViewById(R.id.item_price)
        itemImage = view.findViewById(R.id.item_image)
        itemDetail = view.findViewById(R.id.item_detail)

        itemDescription = view.findViewById(R.id.item_description)

       val productId =  arguments?.getInt("productId")

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getProductById(productId!!).observe(viewLifecycleOwner, Observer {
                bind(it)
            })
        }




        return view
    }



    private fun bind(item: Product) {
        itemName.text = item.title
        itemPrice.text = "Rs. ${item.price}"
        itemImage.bindImage(item.image)
        itemDescription.text = item.description

        itemDetail.setOnClickListener {
            viewModel.addToCart(item.id.toInt())
        }
    }


}
