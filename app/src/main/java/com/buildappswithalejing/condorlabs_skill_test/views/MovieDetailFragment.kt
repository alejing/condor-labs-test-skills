package com.buildappswithalejing.condorlabs_skill_test.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import coil.load
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
    // Binding the Fragment with the layout
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    // Helper variable to add a full youtube video
    private var urlYoutube = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Link the layout attributes with the binding funcionality
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the MoviesViewModel
        binding.viewModel = sharedViewModel

        // Observe the LiveData and when it changes, update the
        // state of the UI
        sharedViewModel.movie.observe(viewLifecycleOwner){ movie ->
            // put the image
            val imgUri = getString(R.string.movie_img_url, movie.posterPath).toUri().buildUpon().scheme("https").build()
            binding.movieImage.load(imgUri) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
            // put the overview
            binding.movieOverviewText.text = movie.overview
            // put the release date
            binding.movieReleaseText.text = movie.releaseDate
            // put the budget in currency format
            sharedViewModel.budget.observe(viewLifecycleOwner){ budget ->
                //Log.d("MovieDetailFragment", "onCreateView " + budget)
                binding.movieBudgetText.text = budget.toString()
            }
            // put the video "link" if it exist
            movie.videos.results[0].key.let {key ->
                binding.iconVideoLink.visibility = View.VISIBLE
                binding.movieVideoText.text = getString(R.string.video_available)
                urlYoutube = getString(R.string.movie_video_url, key)
            }
            if (urlYoutube == "") {
                binding.iconVideoLink.visibility = View.INVISIBLE
                binding.movieVideoText.text = getString(R.string.video_not_available)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // handle the video icon action
        binding.iconVideoLink.setOnClickListener {
            val youTubeVideoUri: Uri = Uri.parse(urlYoutube)
            val intent = Intent(Intent.ACTION_VIEW, youTubeVideoUri)
            startActivity(intent)
        }

        // handle the fav icon action
        binding.iconFav.setOnClickListener {
            sharedViewModel.addToFavorites()
            Toast.makeText(context, "Movie added to favorites", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}