package com.example.capsule.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.capsule.adapters.HomeViewPagerAdapter
import com.example.capsule.databinding.FragmentHomeBinding
import com.example.capsule.fragments.categories.VideoFragment
import com.example.capsule.fragments.categories.NotesFragment
import com.example.capsule.fragments.categories.QuizFragment
import com.example.capsule.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment(),
    VideoFragment.OnNextVideoButtonClickListener,
    NotesFragment.OnNextNotesButtonClickListener
{
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Observe the timerLiveData to update the UI
        viewModel.timeRemaining.observe(viewLifecycleOwner) { remainingSeconds: Long ->
            val minutes = remainingSeconds / 60
            val seconds = remainingSeconds % 60
            binding.timer.text = String.format("%02d:%02d min", minutes, seconds)
            if(remainingSeconds.toInt() == 0){
                Toast.makeText(requireContext(), "Your time is up", Toast.LENGTH_SHORT).show()
            }
        }
        // Start the timer when the fragment is created
        viewModel.startTimer()


         val categoriesFragments = listOf(
            VideoFragment().apply {setOnNextButtonClickListener(this@HomeFragment)},
            NotesFragment().apply{setOnNextNotesButtonClickListener(this@HomeFragment)},
            QuizFragment(),
        )

        binding.viewPagerHome.isUserInputEnabled = true
        val viewPager2Adapter = HomeViewPagerAdapter(categoriesFragments,childFragmentManager,lifecycle)
        binding.viewPagerHome.adapter = viewPager2Adapter

        TabLayoutMediator(binding.tabLayout,binding.viewPagerHome){tab,position ->
            when(position){
                0 -> tab.text = "Video"
                1 -> tab.text = "Notes"
                2 -> tab.text = "Quiz"
            }
        }.attach()


    }

    override fun onNextVideoButtonClicked() {
        binding.viewPagerHome.currentItem = 1
    }

    override fun onNextNotesButtonClicked() {
        // Move to the "Quiz" tab
        binding.viewPagerHome.currentItem = 2
    }
}
