package com.shahjahan.thegrocer.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.shahjahan.thegrocer.R
import com.shahjahan.thegrocer.ui.SpaceItemDecoration
import com.shahjahan.thegrocer.viewmodels.AccountViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class AccountFragment : Fragment() {

    lateinit var viewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel { parametersOf() }
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