package com.example.project_growgh.ui

import android.content.Context
import android.os.Bundle
import android.os.RemoteException
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.project_growgh.ApiFiles.imagesApi.ImagesAPI
import com.example.project_growgh.ApiFiles.imagesApi.ImagesAPIInstance
import com.example.project_growgh.ApiFiles.videosApi.VideosAPIInstance
import com.example.project_growgh.ApiFiles.videosApi.VideosApi
import com.example.project_growgh.ApiFiles.videosApi.jsontokotlinfiles.ShortVideosApi
import com.example.project_growgh.FeedsAdapter
import com.example.project_growgh.FeedsItem
import com.example.project_growgh.NetworkConnection
import com.example.project_growgh.R
import com.example.project_growgh.VideoAdapter
import com.example.project_growgh.VideoItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VideoScreenFragment : Fragment() {
    private lateinit var networkConnection: NetworkConnection

    private lateinit var videoApiInstance: VideosApi

    private lateinit var videoRecyclerView: RecyclerView

    private lateinit var swipeForRefresh: SwipeRefreshLayout

    private lateinit var newContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_video_screen, container, false)

        v.apply {
            swipeForRefresh = findViewById(R.id.swipeForRefresh)

            networkConnection = NetworkConnection(context)

            videoRecyclerView = findViewById(R.id.videoRecyclerView)
            newContext = this.context

            videoApiInstance =
                VideosAPIInstance.getVideoApiInstance().create(VideosApi::class.java)

            networkConnection.observe(viewLifecycleOwner, Observer { isAvailable ->
                when (isAvailable) {
                    true -> {
                        try {
                            getDataFromApiFirstTime()

                            swipeForRefresh.setOnRefreshListener {
                                getDataFromApiOnRefresh()

                                swipeForRefresh.isRefreshing = false
                            }
                        } catch (networkError: RemoteException) {
                            Toast.makeText(
                                context,
                                "Please check your network connectivity!!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    false -> {
                        Toast.makeText(
                            context,
                            "Please check your network connectivity!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        }

        return v
    }


    private fun getDataFromApiFirstTime() {
        CoroutineScope(Dispatchers.IO).launch {
            val videoApiInstanceBodyHolder = videoApiInstance.getVideos(1).body()

            val videoData = mutableListOf<VideoItems>()

            if (videoApiInstanceBodyHolder != null) {
                for (i in 0..9) {
                    videoData.add(
                        VideoItems(
                            videoApiInstanceBodyHolder.data.posts[i].submission.mediaUrl,
                            videoApiInstanceBodyHolder.data.posts[i].submission.title,
                            videoApiInstanceBodyHolder.data.posts[i].submission.description,
                        )
                    )
                }
            }

            withContext(Dispatchers.Main) {
                val adapter = VideoAdapter(newContext, videoData)
                videoRecyclerView.adapter = adapter
                videoRecyclerView.layoutManager = LinearLayoutManager(context)
            }
        }
    }


    private fun getDataFromApiOnRefresh() {
        val pageNumber = (0..9).random()

        CoroutineScope(Dispatchers.IO).launch {
            val videoApiInstanceBodyHolder = videoApiInstance.getVideos(pageNumber).body()

            val videoData = mutableListOf<VideoItems>()

            if (videoApiInstanceBodyHolder != null) {
                for (i in 0..9) {
                    videoData.add(
                        VideoItems(
                            videoApiInstanceBodyHolder.data.posts[i].submission.mediaUrl,
                            videoApiInstanceBodyHolder.data.posts[i].submission.title,
                            videoApiInstanceBodyHolder.data.posts[i].submission.description,
                        )
                    )
                }
            }

            withContext(Dispatchers.Main) {
                val adapter = VideoAdapter(newContext, videoData)
                videoRecyclerView.adapter = adapter
                videoRecyclerView.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}

