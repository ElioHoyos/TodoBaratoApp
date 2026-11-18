package com.example.todobaratoapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todobaratoapp.databinding.ActivityMainBinding
import com.example.todobaratoapp.ui.marca.MarcaAdapter
import com.example.todobaratoapp.ui.marca.MarcaUiState
import com.example.todobaratoapp.ui.marca.MarcaViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MarcaViewModel by viewModels()
    private val marcaAdapter = MarcaAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        setupListeners()
        observeUiState()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_refresh) {
            viewModel.cargarMarcas()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView() {
        binding.recyclerMarcas.layoutManager = LinearLayoutManager(this)
        binding.recyclerMarcas.adapter = marcaAdapter
    }

    private fun setupListeners() {
        binding.swipeRefresh.setOnRefreshListener { viewModel.cargarMarcas() }
        binding.buttonReintentar.setOnClickListener { viewModel.cargarMarcas() }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state -> renderState(state) }
            }
        }
    }

    private fun renderState(state: MarcaUiState) {
        binding.swipeRefresh.isRefreshing = false
        binding.progressBar.visibility = View.GONE
        binding.layoutError.visibility = View.GONE
        binding.textEmpty.visibility = View.GONE
        binding.recyclerMarcas.visibility = View.GONE

        when (state) {
            is MarcaUiState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is MarcaUiState.Success -> {
                if (state.marcas.isEmpty()) {
                    binding.textEmpty.visibility = View.VISIBLE
                } else {
                    binding.recyclerMarcas.visibility = View.VISIBLE
                    marcaAdapter.submitList(state.marcas)
                }
            }
            is MarcaUiState.Error -> {
                binding.layoutError.visibility = android.view.View.VISIBLE
                binding.textError.text = state.message
            }
        }
    }
}