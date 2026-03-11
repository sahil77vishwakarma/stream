package com.example.stream.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stream.R
import com.example.stream.adapters.HomeViewPagerAdapter
import com.example.stream.databinding.FragmentHomeBinding
import com.example.stream.fragments.categories.AccessoriesFragment
import com.example.stream.fragments.categories.ChairFragment
import com.example.stream.fragments.categories.CupboardFragment
import com.example.stream.fragments.categories.FurnitureFragment
import com.example.stream.fragments.categories.MainCategoryFragment
import com.example.stream.fragments.categories.TableFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(R.layout.fragment_home)  {
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragments = arrayListOf<Fragment>(
            MainCategoryFragment(),
            ChairFragment(),
            CupboardFragment(),
            TableFragment(),
            AccessoriesFragment(),
            FurnitureFragment()
        )

        binding.viewpageHome.isUserInputEnabled = false

        val viewPagerAdapter = HomeViewPagerAdapter(categoriesFragments,childFragmentManager,lifecycle)
        binding.viewpageHome.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout,binding.viewpageHome){ tab, position ->
            when(position){
                0 -> tab.text = "Main"
                1 -> tab.text = "Chair"
                2 -> tab.text = "Cupboard"
                3 -> tab.text = "Table"
                4 -> tab.text = "Accessories"
                5 -> tab.text = "Furniture"
            }
        }.attach()

    }
}
