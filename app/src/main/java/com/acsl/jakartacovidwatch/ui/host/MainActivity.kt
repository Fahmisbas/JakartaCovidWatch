package com.acsl.jakartacovidwatch.ui.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.acsl.jakartacovidwatch.R
import com.acsl.jakartacovidwatch.databinding.ActivityMainBinding
import com.acsl.jakartacovidwatch.ui.dashboard.DashboardFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val dashboardFragment = DashboardFragment.newInstance()
        fragmentTransaction.add(R.id.fragment_container, dashboardFragment)
        fragmentTransaction.commit()
    }
}