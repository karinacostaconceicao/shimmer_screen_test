package com.example.shimmerfirsttest.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shimmerfirsttest.adapter.UserListAdapter
import com.example.shimmerfirsttest.databinding.ActivityMainBinding
import com.example.shimmerfirsttest.model.Resource
import com.example.shimmerfirsttest.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var mainViewModel: MainViewModel
    private var adapter: UserListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initRecyclerview()
        observeViewModel()
        mainViewModel.getUserList()
    }

    private fun observeViewModel(){
        mainViewModel.apply {
            userListObserver.observe(this@MainActivity, Observer { status ->
                when(status) {
                    is Resource.Loading -> {
                        binding.shimmerLayout.startShimmer()
                        binding.shimmerLayout.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        status.data?.let{
                            adapter?.swapData(it)
                            showRecylerview()
                        }
                    }
                    else -> {}
                }
            })
        }
    }

    private fun initRecyclerview(){
        adapter =  UserListAdapter(
            this@MainActivity,
            emptyList()
        )
        binding.recyclerviewUserList.layoutManager = LinearLayoutManager(
            this@MainActivity,LinearLayoutManager.VERTICAL, false
        )
        binding.recyclerviewUserList.adapter = adapter
        binding.recyclerviewUserList.setHasFixedSize(true)
    }

    private fun showRecylerview() {
        binding.shimmerLayout.apply {
            stopShimmer()
            visibility = View.GONE
        }
        binding.recyclerviewUserList.visibility = View.VISIBLE
    }
}