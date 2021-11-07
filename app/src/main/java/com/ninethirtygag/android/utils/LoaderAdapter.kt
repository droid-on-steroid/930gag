package com.ninethirtygag.android.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ninethirtygag.android.R

class LoaderAdapter : RecyclerView.Adapter<LoaderViewHolder>() {

    var showLoader: Boolean = false
        set(value) {
            if (value != field) {
                field = value
                if (field) {
                    notifyItemInserted(0)
                } else {
                    notifyItemRemoved(0)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoaderViewHolder {
        return LoaderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.inflater_loader_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, position: Int) {

    }

    override fun getItemCount() = if (showLoader) 1 else 0

}

class LoaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)