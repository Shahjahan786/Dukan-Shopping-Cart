package com.shahjahan.thegrocer.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.shahjahan.thegrocer.R
import com.shahjahan.thegrocer.ui.SpaceItemDecoration
import com.shahjahan.thegrocer.viewmodels.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {

     private val viewModel: AccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_account, container, false)
        val orderList: RecyclerView = view.findViewById(R.id.orderList);
        val spaceItemDecoration = SpaceItemDecoration(15)
        orderList.addItemDecoration(spaceItemDecoration)

        viewModel.orders.observe(viewLifecycleOwner, Observer {
            orderList.adapter = AccountAdapter(it)
        })


        return view
    }
}