package com.buildappswithalejing.condorlabs_skill_test.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.buildappswithalejing.condorlabs_skill_test.R
import com.buildappswithalejing.condorlabs_skill_test.databinding.FragmentMovieDetailBinding
import com.buildappswithalejing.condorlabs_skill_test.viewmodels.MoviesViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailFragment : Fragment() {

    // Shared viewModel for using data between fragments
    private val sharedViewModel: MoviesViewModel by activityViewModels()


    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Capture the idMovie from the item List MovieListFragment
        /**
        sharedViewModel.getIdMovie()?.let { id ->
            Log.d("MovieDetailFragment", id)
            sharedViewModel.getMovie(id)
        }
        */
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the MoviesViewModel
        binding.viewModel = sharedViewModel

        binding.movieOverviewText.text = sharedViewModel.getOverview().toString()
        binding.movieReleaseText.text = sharedViewModel.getReleaseDate().toString()
        binding.movieBudgetText.text = getString(R.string.budget_text, sharedViewModel.getBudget().toString())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}