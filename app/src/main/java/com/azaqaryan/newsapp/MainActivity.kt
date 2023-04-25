package com.azaqaryan.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.azaqaryan.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavHostControllerProvider {
	private var binding: ActivityMainBinding? = null
	private lateinit var navController: NavController

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding?.root)
		appComponent.inject(this)

		setup()
	}

	private fun setup() {
		// Set up the navigation controller
		val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
		navController = navHostFragment.navController

		// Set up the home back button with navigation
		setupActionBarWithNavController(navController)
	}
	override fun onSupportNavigateUp(): Boolean {
		return navController.navigateUp() || super.onSupportNavigateUp()
	}

	override fun provideNavController(): NavController {
		return navController
	}
}