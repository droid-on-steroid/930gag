package com.ninethirtygag.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ninethirtygag.android.databinding.FragmentMainBinding
import com.ninethirtygag.android.utils.Resource

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val memesAdapter = MemesAdapter()
        binding.listMemes.adapter = memesAdapter
        mainViewModel.memes.observe(viewLifecycleOwner) { memes ->
            memesAdapter.setMemes(memes.data ?: emptyList())
            binding.txtError.isVisible = memes is Resource.Error && memes.data.isNullOrEmpty()
            binding.progress.isVisible = memes is Resource.Error && memes.data.isNullOrEmpty()
        }

        mainViewModel.getMemes()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle()
            }
    }
}