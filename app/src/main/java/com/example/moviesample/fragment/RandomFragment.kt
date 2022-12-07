package com.example.moviesample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviesample.R
import com.example.moviesample.databinding.FragmentFirstBinding
import com.example.moviesample.viewmodel.RandomViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class RandomFragment : Fragment() {

    private val viewModel: RandomViewModel by viewModel()
    private var _binding: FragmentFirstBinding? = null
    private val jobs = arrayListOf<Job>()
    private val imageRequestOptions = RequestOptions()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRandom.setOnClickListener {
            viewModel.onClick()
        }

        jobs += viewModel.currentMovie.filterNotNull().onEach { movie ->
            val url = "https://image.tmdb.org/t/p/w500" + movie.poster_path
            Glide.with(this@RandomFragment)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .fitCenter()
                .centerCrop()
                .into(binding.card.imageViewMovie)

            binding.card.textViewTitle.text = movie.title
            binding.card.textviewSecond.text = movie.release_date

        }.launchIn(lifecycleScope)

        jobs += viewModel.networkState.onEach { state ->
            when(state){
                RandomViewModel.NetworkState.Error -> {
                    Toast.makeText(requireContext(), "Check your internet connection", Toast.LENGTH_SHORT).show()
                    binding.btnRandom.isEnabled = true
                }
                RandomViewModel.NetworkState.Loading -> {
                    binding.btnRandom.isEnabled = false
                }
                RandomViewModel.NetworkState.Success -> binding.btnRandom.isEnabled = true
            }
        }.launchIn(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        jobs.forEach {
            it.cancel()
        }
        _binding = null
    }
}