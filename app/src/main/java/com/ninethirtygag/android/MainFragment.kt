package com.ninethirtygag.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ninethirtygag.android.databinding.FragmentMainBinding
import com.ninethirtygag.android.utils.LoaderAdapter
import com.ninethirtygag.android.utils.Resource
import com.ninethirtygag.android.utils.pagination.EndlessRecyclerViewScrollListener

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

        val paginationListener = object :
            EndlessRecyclerViewScrollListener(binding.listMemes.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                mainViewModel.loadMoreMemes()
            }
        }

        val memesAdapter = MemesAdapter()
        val loaderAdapter = LoaderAdapter()
        binding.listMemes.adapter = ConcatAdapter(memesAdapter, loaderAdapter)
        binding.listMemes.addOnScrollListener(paginationListener)

        mainViewModel.memes.observe(viewLifecycleOwner) { result ->
            loaderAdapter.showLoader = result is Resource.Loading && memesAdapter.itemCount != 0
            memesAdapter.setMemes(result.data ?: emptyList())
            binding.progress.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
            binding.txtError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
            binding.txtError.text = result.error?.localizedMessage ?: "Something went wrong"
            if (result is Resource.Success || result is Resource.Error) {
                paginationListener.apiComplete()
            }
        }

        mainViewModel.loadMoreMemes()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle()
            }
    }
}