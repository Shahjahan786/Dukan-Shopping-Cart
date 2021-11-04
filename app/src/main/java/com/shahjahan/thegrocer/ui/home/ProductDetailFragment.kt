package com.shahjahan.thegrocer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.shahjahan.thegrocer.R
import com.shahjahan.thegrocer.db.entities.Product
import com.shahjahan.thegrocer.utility.bindImage
import com.shahjahan.thegrocer.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailFragment : Fragment(){

    private val viewModel: HomeViewModel by viewModels();
    lateinit var itemName: TextView
    lateinit var itemPrice: TextView
    lateinit var itemImage: ImageView
    lateinit var itemDetail: ImageView
    lateinit var itemDescription:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
