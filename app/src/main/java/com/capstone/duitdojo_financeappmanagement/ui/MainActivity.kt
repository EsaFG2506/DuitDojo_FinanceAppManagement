package com.capstone.duitdojo_financeappmanagement.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.capstone.duitdojo_financeappmanagement.R
import com.capstone.duitdojo_financeappmanagement.data.room.TransactionsDatabase
import com.capstone.duitdojo_financeappmanagement.databinding.ActivityMainBinding
import com.capstone.duitdojo_financeappmanagement.repository.TransactionsRepository
import com.capstone.duitdojo_financeappmanagement.ui.slider.SliderActivity
import com.capstone.duitdojo_financeappmanagement.ui.viewmodel.MainViewModel
import com.capstone.duitdojo_financeappmanagement.ui.viewmodel.TransactionsViewModel
import com.capstone.duitdojo_financeappmanagement.ui.viewmodel.TransactionsViewModelProvFactory
import com.capstone.duitdojo_financeappmanagement.ui.viewmodel.UserViewModelFactory
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var viewModel: TransactionsViewModel
    private val mainViewModel by viewModels<MainViewModel> {
        UserViewModelFactory.getInstance(this)
    }
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = TransactionsRepository(TransactionsDatabase.getInstance(this))
        val viewModelProviderFactory = TransactionsViewModelProvFactory(repository, application)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory)[TransactionsViewModel::class.java]

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        showLoading(true)

        mainViewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, SliderActivity::class.java))
                finish()
            } else {
                showLoading(false)
            }
        }


        drawerLayout = binding.drawerLayout
        navView = binding.drawerNavView
        navView.setupWithNavController(navController)
        setSupportActionBar(binding.toolbar)

        appBarConfiguration = AppBarConfiguration(
            navController.graph, drawerLayout
        )

        // this to make navigate up when on different fragment
        binding.toolbar.setupWithNavController(navController, drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)

        lifecycleScope.launch {
            if (viewModel.readUIPreference("night_mode") == true) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}