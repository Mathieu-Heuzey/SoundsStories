package com.soundsstories.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.soundsstories.Sound
import com.soundsstories.R
import android.media.MediaPlayer
import androidx.cardview.widget.CardView

class PhotoAdapter(var context: Context) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    var dataList = emptyList<Sound>()
    var mMediaPlayer: MediaPlayer? = null

    internal fun setDataList(dataList: List<Sound>) {
        this.dataList = dataList
    }

    // Provide a direct reference to each of the views with data items

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView
        var container: CardView

        init {
            image = itemView.findViewById(R.id.icon)
            title = itemView.findViewById(R.id.sound_title)
            container = itemView.findViewById(R.id.card_view)
        }

    }

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {

        // Inflate the custom layout
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: PhotoAdapter.ViewHolder, position: Int) {

        // Get the data model based on position
        var data = dataList[position]

        // Set item views based on your views and data model
        holder.title.text = data.title
        val id = context.resources.getIdentifier(data.icon, "drawable", context.packageName)
        holder.image.setImageResource(id)
        holder.container.setOnClickListener {
            playSound(data.mp3)
        }
    }

    //  total count of items in the list
    override fun getItemCount() = dataList.size

    fun playSound(mp3: String) {

        val sound = SoundRessources.icons[mp3]
        mMediaPlayer = MediaPlayer.create(context, sound!!)
//            mMediaPlayer!!.isLooping = true
        mMediaPlayer!!.start()
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }
}

object SoundRessources {
    var icons: MutableMap<String, Int> = HashMap()

    init {
        icons["cow"] = R.raw.cow
        icons["sheep"] = R.raw.sheep
        icons["hen"] = R.raw.hen
        icons["horse"] = R.raw.horse
        icons["rooster"] = R.raw.rooster
        icons["pig"] = R.raw.pig
    }

}