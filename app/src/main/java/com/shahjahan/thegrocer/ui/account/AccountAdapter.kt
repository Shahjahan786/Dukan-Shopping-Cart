package com.shahjahan.thegrocer.ui.account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopkart.ui.home.ItemAdapter
import com.shahjahan.thegrocer.R
import com.shahjahan.thegrocer.models.AccountModel
import com.shahjahan.thegrocer.models.CartModel


class AccountAdapter( private val dataset: List<AccountModel>) : RecyclerView.Adapter<AccountAdapter.ItemViewHolder>(){


    class ItemViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {
        private var textViewTitle: TextView
        private var textViewPrice: TextView

        init {
            textViewTitle = view.findViewById(R.id.textViewTitle)
            textViewPrice = view.findViewById(R.id.textViewPrice)
        }

        fun bind(item: AccountModel) {
            textViewTitle.text = item.idText
            textViewPrice.text = item.priceText
        }
    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.account_item, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}