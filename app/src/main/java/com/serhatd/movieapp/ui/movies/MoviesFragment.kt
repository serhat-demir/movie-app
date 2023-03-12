package com.serhatd.movieapp.ui.movies

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.serhatd.movieapp.MainActivity
import com.serhatd.movieapp.R
import com.serhatd.movieapp.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MoviesViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        (requireActivity() as MainActivity).binding.toolbar.visibility = View.VISIBLE
        (requireActivity() as MainActivity).binding.toolbar.title = getString(R.string.title_movies)

        createMenu()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewModel.movies.observe(viewLifecycleOwner) {
            it?.let {
                binding.frgMoviesRecyclerView.adapter = MovieAdapter(it)
            }
        }

        viewModel.getMovies()
    }

    private fun createMenu() {
        requireActivity().addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_guest, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                Navigation.findNavController(binding.frgMoviesRecyclerView).navigate(R.id.moviesToLogin)
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}