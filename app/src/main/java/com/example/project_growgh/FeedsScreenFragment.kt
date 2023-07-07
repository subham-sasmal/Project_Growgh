package com.example.project_growgh

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
import com.example.project_growgh.retrofitfiles.ImagesAPI
import com.example.project_growgh.retrofitfiles.ImagesAPIInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeedsScreenFragment : Fragment() {
    private lateinit var networkConnection: NetworkConnection

    private lateinit var imagesApiInstance: ImagesAPI

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeForRefresh: SwipeRefreshLayout

    private lateinit var newContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_feeds_screen, container, false)

        v.apply {
            recyclerView = findViewById(R.id.recyclerView_feeds_holder)
            swipeForRefresh = findViewById(R.id.swipeForRefresh)
            newContext = this.context

            networkConnection = NetworkConnection(context)

            imagesApiInstance =
                ImagesAPIInstance.getInstance().create(ImagesAPI::class.java)

            networkConnection.observe(viewLifecycleOwner, Observer { isAvailable ->
                when (isAvailable) {
                    true -> {
                        try {
                            getDataFromApiFirstTime()

                            swipeForRefresh.setOnRefreshListener {
                                getDataFromApiAfterRefresh()

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
            val apiInstanceBodyHolder = imagesApiInstance.getImages().body()

            val feedsData = mutableListOf<FeedsItem>()

            if (apiInstanceBodyHolder != null) {
                for (i in 0..9) {
                    feedsData.add(
                        FeedsItem(
                            apiInstanceBodyHolder[(0..9).random()].avatar_url,
                            apiInstanceBodyHolder[(0..9).random()].login,
                            apiInstanceBodyHolder[(0..9).random()].avatar_url,
                            (50..90).random(),
                            (30..60).random()
                        )
                    )
                }
            }

            withContext(Dispatchers.Main) {
                val adapter = FeedsAdapter(newContext, feedsData)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun getDataFromApiAfterRefresh() {
        CoroutineScope(Dispatchers.IO).launch {
            val apiInstanceBodyHolder = imagesApiInstance.getImages().body()

            val feedsData = mutableListOf<FeedsItem>()

            if (apiInstanceBodyHolder != null) {
                for (i in 0..9) {
                    feedsData.add(
                        FeedsItem(
                            apiInstanceBodyHolder[(10..29).random()].avatar_url,
                            apiInstanceBodyHolder[(10..29).random()].login,
                            apiInstanceBodyHolder[(10..29).random()].avatar_url,
                            (50..90).random(),
                            (30..60).random()
                        )
                    )
                }
            }

            withContext(Dispatchers.Main) {
                val adapter = FeedsAdapter(newContext, feedsData)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}