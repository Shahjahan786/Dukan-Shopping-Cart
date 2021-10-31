package com.shahjahan.thegrocer.ui.cart
import com.shahjahan.thegrocer.models.CartModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopkart.ui.home.ItemAdapter
import com.shahjahan.thegrocer.R
import com.shahjahan.thegrocer.models.ProductModel
import com.shahjahan.thegrocer.ui.account.AccountAdapter


class CartAdapter(private val dataset: List<CartModel>, val actionlistener: Actionlistener) : RecyclerView.Adapter<CartAdapter.ItemViewHolder>(){


    class ItemViewHolder(private var view: View, actionlistener: Actionlistener) : RecyclerView.ViewHolder(view) {
        private var textViewTitle: TextView
        private var textViewPrice: TextView
        private var ivDelete: ImageView
        private var actionlistener: Actionlistener

        init {
            textViewTitle = view.findViewById(R.id.textViewTitle)
            textViewPrice = view.findViewById(R.id.textViewPrice)
            ivDelete = view.findViewById(R.id.ivDelete)
            this.actionlistener = actionlistener
        }

        fun bind(item: CartModel) {
            textViewTitle.text = "Product: ${item.title}"
            textViewPrice.text = "Price: Rs. ${item.price}"
            ivDelete.setOnClickListener {
                actionlistener.onDelete(item)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)

        return ItemViewHolder(view, actionlistener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    interface Actionlistener {
        fun onDelete(item: CartModel)

    }
}