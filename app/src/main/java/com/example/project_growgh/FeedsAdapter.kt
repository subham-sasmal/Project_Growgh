package com.example.project_growgh

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FeedsAdapter(
    val context: Context,
    private var feedsItemObject: List<FeedsItem>
) : RecyclerView.Adapter<FeedsAdapter.FeedsViewHolder>() {
    inner class FeedsViewHolder(feedsView: View) : RecyclerView.ViewHolder(feedsView) {
        var profileImage: ImageView = feedsView.findViewById(R.id.profile_dp_holder)
        var profileName: TextView = feedsView.findViewById(R.id.tv_profile_name)
        var feedImage: ImageView = feedsView.findViewById(R.id.feeds_image_holder)
        var commentNumberOne: TextView = feedsView.findViewById(R.id.tv_comment_number_1)
        var commentNumberTwo: TextView = feedsView.findViewById(R.id.comment_count_2)
        var likes: TextView = feedsView.findViewById(R.id.like_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feeds_screen_recycler_values, parent, false)
        return FeedsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return feedsItemObject.size
    }

    override fun onBindViewHolder(holder: FeedsViewHolder, position: Int) {
        holder.apply {
            Glide.with(context).load(feedsItemObject[position].profileImage).into(profileImage)
            profileName.text = feedsItemObject[position].profileName
            Glide.with(context).load(feedsItemObject[position].mainImage).into(feedImage)
            commentNumberOne.text = feedsItemObject[position].commentCount.toString()
            commentNumberTwo.text = feedsItemObject[position].commentCount.toString()
            likes.text = feedsItemObject[position].likesCount.toString()
        }
    }
}