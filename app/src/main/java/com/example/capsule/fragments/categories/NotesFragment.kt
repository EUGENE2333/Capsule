package com.example.capsule.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.capsule.databinding.FragmentNotesBinding

class NotesFragment: Fragment() {
    private lateinit var binding: FragmentNotesBinding
    private var  onNextNotesButtonClickListener:OnNextNotesButtonClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener {
            onNextNotesButtonClickListener?.onNextNotesButtonClicked()
        }

    }

    fun setOnNextNotesButtonClickListener(listener: OnNextNotesButtonClickListener) {
        onNextNotesButtonClickListener = listener

    }

    interface OnNextNotesButtonClickListener {
        fun onNextNotesButtonClicked()
    }



}