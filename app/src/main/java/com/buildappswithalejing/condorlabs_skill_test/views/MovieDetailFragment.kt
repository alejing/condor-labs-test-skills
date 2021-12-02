package com.buildappswithalejing.condorlabs_skill_test.views

import android.content.Intent
import android.net.Uri
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

    private var urlYoutube = ""

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

        // Observe the LiveData and when it changes, update the
        // state of the UI
        sharedViewModel.movie.observe(viewLifecycleOwner){ movie ->
            binding.movieOverviewText.text = movie.overview
            binding.movieReleaseText.text = movie.releaseDate
            binding.movieBudgetText.text = movie.budget.toString()
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

        //Log.d("MovieDetailFragment", "onCreate " + sharedViewModel.status.value.toString())

        /**
        val urlVideo = sharedViewModel.getVideo()
        if(urlVideo != "nv"){
            binding.movieVideoText.text = getString(R.string.video_available)
            binding.iconVideoLink.visibility = View.VISIBLE
            Log.d("MovieDetailFragment", urlVideo)
            binding.iconVideoLink.setOnClickListener {
                val youTubeVideo: Uri = Uri.parse(urlVideo)
                val intent = Intent(Intent.ACTION_VIEW, youTubeVideo)
                startActivity(intent)
            }
        }else{
            binding.movieVideoText.text = getString(R.string.video_not_available)
            binding.iconVideoLink.visibility = View.GONE
        }
        */
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.iconVideoLink.setOnClickListener {
            val youTubeVideoUri: Uri = Uri.parse(urlYoutube)
            val intent = Intent(Intent.ACTION_VIEW, youTubeVideoUri)
            startActivity(intent)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MovieDetailFragment", "onDestroyView " + sharedViewModel.status.value.toString())
        _binding = null
    }

}