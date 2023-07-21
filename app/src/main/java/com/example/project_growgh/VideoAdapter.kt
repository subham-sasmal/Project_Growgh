package com.example.project_growgh

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(
    val context: Context,
    private var videoItemObject: List<VideoItems>
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    inner class VideoViewHolder(videoView: View) : RecyclerView.ViewHolder(videoView) {
        var videoUrl: VideoView = videoView.findViewById(R.id.videoHolder)
        var title: TextView = videoView.findViewById(R.id.titleHolder)
        var description: TextView = videoView.findViewById(R.id.descriptionHolder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_screen_recycler_values, parent, false)
        return VideoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videoItemObject.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.apply {
            val mediaController = MediaController(context)
            mediaController.setAnchorView(videoUrl)

            val videoURI = Uri.parse(videoItemObject[position].mediaUrl)

            videoUrl.setMediaController(mediaController)
            videoUrl.setVideoURI(videoURI)
            videoUrl.requestFocus()
            videoUrl.start()

            title.text = videoItemObject[position].title
            description.text = videoItemObject[position].description
        }
    }
}