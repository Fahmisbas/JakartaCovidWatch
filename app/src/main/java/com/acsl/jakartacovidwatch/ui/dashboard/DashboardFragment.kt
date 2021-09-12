package com.acsl.jakartacovidwatch.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.acsl.jakartacovidwatch.R
import com.acsl.jakartacovidwatch.databinding.FragmentDashboardBinding
import com.acsl.jakartacovidwatch.ui.districts.VulnerableDistrictFragment

class DashboardFragment : Fragment() {

    private var binding : FragmentDashboardBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnVulnerableDistrict?.setOnClickListener {
            navigateToFragment(VulnerableDistrictFragment.newInstance())
        }

    }

    private fun navigateToFragment(fragment : Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = DashboardFragment()
    }
}
