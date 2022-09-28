package com.technipixl.exo1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.technipixl.exo1.databinding.FragmentCharactersBinding
import com.technipixl.exo1.recyclerViews.HeroRecyclerView

class CharactersFragment : Fragment() {

    private var binding: FragmentCharactersBinding? = null
    private val viewModel: HeroViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)

        viewModel.getHeroList(20)

        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.viewModel = viewModel
        binding?.heroList?.adapter = HeroRecyclerView()

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}