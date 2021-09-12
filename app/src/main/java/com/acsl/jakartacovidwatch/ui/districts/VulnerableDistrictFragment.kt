package com.acsl.jakartacovidwatch.ui.districts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.acsl.jakartacovidwatch.R
import com.acsl.jakartacovidwatch.ViewModelFactory
import com.acsl.jakartacovidwatch.data.datasource.model.VulnerableDistrictItem
import com.acsl.jakartacovidwatch.databinding.FragmentVulnerableDistrictBinding
import com.acsl.jakartacovidwatch.util.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class VulnerableDistrictFragment : Fragment() {

    private var binding: FragmentVulnerableDistrictBinding? = null
    private lateinit var viewModel : VulnerableDistrictViewModel

    private val callback = OnMapReadyCallback { googleMap ->
        observeChange(googleMap)
    }

    private fun observeChange(googleMap: GoogleMap) {
        observe(viewModel.getVulnerableDistricts()) { vulnerableDistricts ->
            pinLocation(vulnerableDistricts, googleMap)
        }
        observe(viewModel.isSuccess)  { responseStatus ->
            responseStatus.message?.let { requireContext().makeToast(it) }
        }
    }

    private fun pinLocation(
        vulnerableDistricts: List<VulnerableDistrictItem>?,
        googleMap: GoogleMap
    ) {
        if (vulnerableDistricts != null && vulnerableDistricts.isNotEmpty()) {
            for (data in vulnerableDistricts) {
                if (data.lat != null && data.lng != null) {
                    val location = LatLng(data.lat, data.lng)
                    googleMap.addMarker(MarkerOptions().position(location).title(data.subDistrictName))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVulnerableDistrictBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[VulnerableDistrictViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        fun newInstance() = VulnerableDistrictFragment()
    }
}