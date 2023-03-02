package com.nrup.mviapp.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nrup.mviapp.data.api.ApiHelperImpl
import com.nrup.mviapp.data.api.RetrofitBuilder
import com.nrup.mviapp.data.model.User
import com.nrup.mviapp.databinding.ActivityMainBinding
import com.nrup.mviapp.ui.main.adapter.MainAdapter
import com.nrup.mviapp.ui.main.intent.MainIntent
import com.nrup.mviapp.ui.main.viewmodel.MainViewModel
import com.nrup.mviapp.ui.main.viewstate.MainState
import com.nrup.mviapp.util.ViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpUI(binding)
        setUpViewModel()
        observeViewModel()
        setUpClicks()
    }

    private fun setUpUI(binding: ActivityMainBinding) {

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.run {
                addItemDecoration(
                    DividerItemDecoration(
                        recyclerView.context,
                        (recyclerView.layoutManager as LinearLayoutManager).orientation
                    )
                )
            }

            recyclerView.adapter = adapter
        }

    }

    private fun setUpViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService
                )
            )
        )[MainViewModel::class.java]
    }

    private fun setUpClicks() {
        binding.buttonFetchUser.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    MainState.Idle -> {}
                    MainState.Loading -> {
                        with(binding) {
                            buttonFetchUser.visibility = View.GONE
                            progressBar.visibility = View.VISIBLE
                        }

                    }
                    is MainState.Users -> {
                        with(binding) {
                            buttonFetchUser.visibility = View.GONE
                            progressBar.visibility = View.GONE
                        }
                        renderList(it.user)

                    }
                    is MainState.Error -> {
                        with(binding) {
                            buttonFetchUser.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                        }
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun renderList(user: List<User>) {
        binding.recyclerView.visibility = View.VISIBLE
        user.let { listOfUsers ->
            listOfUsers.let { adapter.addData(it) }
        }
        adapter.notifyDataSetChanged()
    }
}