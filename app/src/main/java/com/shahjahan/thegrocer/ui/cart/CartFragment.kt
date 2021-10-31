package com.shahjahan.thegrocer.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.shahjahan.thegrocer.R
import com.shahjahan.thegrocer.models.CartModel
import com.shahjahan.thegrocer.ui.SpaceItemDecorationCart
import com.shahjahan.thegrocer.viewmodels.CartViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class CartFragment : Fragment(), CartAdapter.Actionlistener{

    lateinit var viewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel { parametersOf() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      val view = inflater.inflate(
            R.layout.fragment_cart, container, false
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView4)

        val spaceItemDecoration = SpaceItemDecorationCart(15)
        recyclerView.addItemDecoration(spaceItemDecoration)

        val layout = view.findViewById<LinearLayout>(R.id.linearLayout4)
        val pay = view.findViewById<View>(R.id.pay)
        val totalBillView = view.findViewById<TextView>(R.id.textViewTotalBill)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        pay.setOnClickListener {
            viewModel.removeAll()
        }

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            paymentLoading(progressBar, it)
        })

        viewModel.cartItemsRecView.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = CartAdapter(it, this)

            totalBill(totalBillView, it)

            if (it.isNullOrEmpty()){
                layout.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                pay.visibility = View.GONE
                totalBillView.text= ""
            }
            else{
                layout.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                pay.visibility = View.VISIBLE
            }
        })


        return view
    }

    private fun totalBill(
        price: TextView,
        list: List<CartModel>?
    ){
        var sum = 0.0;
        if (list.isNullOrEmpty()){
            println("heyy null")
        }
        else {
            for (items in list){
                sum += items.price.toDouble()
            }
        }
        val totalBill: String = "Total Bill: Rs$sum"
        price.text = totalBill
    }

    fun paymentLoading(
        progressBar: ProgressBar,
        loading: Boolean
    ) {
        if (loading){
            progressBar.visibility = View.VISIBLE
        }
        else{
            progressBar.visibility = View.GONE
        }
    }

    override fun onDelete(item: CartModel) {
        viewModel.remove(item.id.toInt())
    }
}
