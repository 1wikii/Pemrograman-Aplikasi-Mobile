package com.example.tugaspam.ui.skill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tugaspam.databinding.FragmentSkillDetailBinding


class SkillDetailFragment : Fragment() {

    private var _binding: FragmentSkillDetailBinding? = null

    private val binding get() = _binding!!

    companion object {
        val EXTRA_NAME ="extra_name"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSkillDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val name = arguments?.getString(EXTRA_NAME)
            binding.textName.text = name
        }
    }


}