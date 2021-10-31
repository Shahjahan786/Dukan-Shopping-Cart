package com.example.shopkart.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shahjahan.thegrocer.R
import com.shahjahan.thegrocer.models.ProductModel
import com.shahjahan.thegrocer.utility.bindImage


class ItemAdapter(private val dataSet: List<ProductModel>, val actionlistener: Actionlistener) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    class ItemViewHolder(view: View, actionlistener: Actionlistener) :
        RecyclerView.ViewHolder(view) {
        val itemName: TextView
        val itemPrice: TextView
        val itemImage: ImageView
        val itemDetail: ImageView
        var actionlistener: Actionlistener

        init {
            // Define click listener for the ViewHolder's View.
            itemName = view.findViewById(R.id.item_name)
            itemPrice = view.findViewById(R.id.item_price)
            itemImage = view.findViewById(R.id.item_image)
            itemDetail = view.findViewById(R.id.item_detail)
            this.actionlistener = actionlistener
        }

        fun bind(item: ProductModel) {
            itemName.text = item.title
            itemPrice.text = "Rs. ${item.price}"
            itemImage.bindImage(item.image)

            itemDetail.setOnClickListener {
                actionlistener.onAddTocCart(item)
            }

            itemImage.setOnClickListener {
                actionlistener.onShowProductDetail(item)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grid_view, parent, false)

        return ItemViewHolder(view, actionlistener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataSet.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    interface Actionlistener {
        fun onAddTocCart(item: ProductModel)
        fun onShowProductDetail(item: ProductModel)
    }
}