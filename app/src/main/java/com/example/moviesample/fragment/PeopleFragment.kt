package com.example.moviesample.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.moviesample.adapter.PeopleAdapter
import com.example.moviesample.databinding.FragmentPeopleBinding
import com.example.moviesample.viewmodel.PeopleViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class PeopleFragment : Fragment() {

    private val viewModel: PeopleViewModel by viewModel()
    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!
    private val jobs = arrayListOf<Job>()
    private val adapter = PeopleAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPeopleBinding.inflate(inflater, container, false).apply {
            this.viewModel = viewModel
            this.lifecycleOwner = lifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        jobs += viewModel.peoples.onEach {
            adapter.addItems(it)
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