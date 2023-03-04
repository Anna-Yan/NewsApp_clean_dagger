package com.azaqaryan.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.azaqaryan.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private var binding: ActivityMainBinding? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		appComponent.inject(this)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding?.root)
	}
}