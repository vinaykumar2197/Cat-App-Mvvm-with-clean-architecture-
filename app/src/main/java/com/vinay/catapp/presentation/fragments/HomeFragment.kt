package com.vinay.catapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinay.catapp.databinding.FragmentHomeBinding
import com.vinay.catapp.presentation.adapter.CatImageAdapter
import com.vinay.catapp.presentation.viewmodel.PLViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(){
    private val viewModel: PLViewModel by viewModels()
    private lateinit var adapter: CatImageAdapter
    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return fragmentHomeBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentHomeBinding.catRecyclerView.layoutManager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(fragmentHomeBinding.catRecyclerView.context, DividerItemDecoration.VERTICAL)
        fragmentHomeBinding.catRecyclerView.addItemDecoration(decoration)

        adapter = CatImageAdapter()
        fragmentHomeBinding.catRecyclerView.adapter = adapter

        // Create a coroutine
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getCatBreedList().collectLatest { movies ->
//                        progressbar.visibility = View.GONE
                    movies?.let {
                        progressbar.visibility = View.GONE
                        adapter.submitData(movies)
                    }
                    }
                }
            }

        adapter.addLoadStateListener { loadState ->
            // show empty list
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading)
                progressbar.visibility = View.VISIBLE
            else {
                progressbar.visibility = View.GONE
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(context, it.error.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }
//
//        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            viewModel.getCatBreedList().collectLatest { movies ->
//                if(movies!=null) {
//                    progressbar.visibility = View.GONE
//                    adapter?.submitData(movies)
//                }
//            }
//        }

//        btn_view_balance.setOnClickListener {
//            val navOption = NavOptions.Builder()
//                .setEnterAnim(R.anim.slide_in_right)
//                .setExitAnim(R.anim.slide_out_left)
//                .setPopEnterAnim(R.anim.slide_in_left)
//                .setPopExitAnim(R.anim.slide_out_right)
//                .build()
//
//            navController.navigate(R.id.viewBalanceFragment,null,navOption)
//        }
//
//        btn_transactions.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeFragmentToViewTransactionsFragment()
//            navController.navigate(action)
//        }
//
//        btn_send_money.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeFragmentToChooseReceiverFragment()
//            navController.navigate(action)
//        }

    }

    override fun onDestroyView() {
        Log.e("onDestroyView", "onDestroyView: " )
        super.onDestroyView()
    }

}