package com.buildappswithalejing.condorlabs_skill_test.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.buildappswithalejing.condorlabs_skill_test.R
import com.buildappswithalejing.condorlabs_skill_test.databinding.FragmentMovieListBinding
import com.buildappswithalejing.condorlabs_skill_test.databinding.ListViewItemBinding
import com.buildappswithalejing.condorlabs_skill_test.viewmodels.MoviesViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    // Shared viewModel for using data between fragments
    private val sharedViewModel: MoviesViewModel by activityViewModels()

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the MovieListFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        //_binding = ListViewItemBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the MoviesViewModel
        binding.viewModel = sharedViewModel

        // Sets the adapter of the moviesList RecyclerView
        binding.moviesList.adapter = MoviesListAdapter(listener)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        */
    }

    private val listener = MoviesListAdapter.OnClickListener { id ->
        //Log.d("MoviesListFragment", id)
        // Sending id from MovieListFragment to MoviesDetailFragment with viewModel
        //sharedViewModel.setIdMovie(id)
        //Log.d("MovieListFragment",sharedViewModel.status.value.toString())

        /**
        var image = binding.statusImage
        // get only a movie from the service with the new data
        sharedViewModel.getMovie(id)
        var status = sharedViewModel.status.value.toString()
        Log.d("MovieListFragment", status)
        while(status != "DONE"){
            Log.d("MovieListFragment", "$status 1")
            image.visibility = View.VISIBLE
            image.setImageResource(R.drawable.loading_animation)
            status = sharedViewModel.status.value.toString()
            Log.d("MovieListFragment", "$status 2")
        }
        image.visibility = View.GONE
        */
        // get only a movie from the service with the new data
        sharedViewModel.getMovie(id)
        // Add action to navigate
        findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}