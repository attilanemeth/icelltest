package com.example.moviesample.fragment

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.moviesample.R
import com.example.moviesample.compose.MoviesCompose
import com.example.moviesample.viewmodel.ExperimentalViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(R.layout.fragment_second) {
    private val viewModel: ExperimentalViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.composeView).setContent {
            MoviesCompose(viewModel = viewModel)
        }
    }
}