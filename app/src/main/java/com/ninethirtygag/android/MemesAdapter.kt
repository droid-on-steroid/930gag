package com.ninethirtygag.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ninethirtygag.android.data.models.Meme
import com.ninethirtygag.android.databinding.ItemMemeBinding

class MemesAdapter : RecyclerView.Adapter<MemeViewHolder>() {

    private var memes = listOf<Meme>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MemeViewHolder(ItemMemeBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        holder.bindMeme(memes[position])
    }

    fun setMemes(memes: List<Meme>) {
        this.memes = ArrayList(memes)
        notifyDataSetChanged()
    }

    override fun getItemCount() = memes.size

}

class MemeViewHolder(private val itemMemeBinding: ItemMemeBinding) :
    RecyclerView.ViewHolder(itemMemeBinding.root) {

    fun bindMeme(memeRes: Meme) {
        itemMemeBinding.apply {
            txtTitle.text = memeRes.title
            Glide.with(itemMemeBinding.imgMeme)
                .load(memeRes.url)
                .placeholder(R.drawable.ic_baseline_image_120)
                .into(itemMemeBinding.imgMeme)
        }
    }
}
