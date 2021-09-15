package com.cocos.develop.favoritescreen.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cocos.develop.favoritescreen.R
import com.cocos.develop.model.data.DataModel
import com.cocos.develop.favoritescreen.utils.loadImageFromResource
import kotlinx.android.synthetic.main.activity_favorite_recyclerview_item.view.*

/**
 * homework com.cocos.develop.dictionarykiss.ui.main.adapter
 *
 * @author Amina
 * 26.08.2021
 */
class FavoriteAdapter(private var onListItemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<FavoriteAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataModel> = arrayListOf()

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_favorite_recyclerview_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.header_textview_recycler_item.text = data.text
                itemView.image_url.loadImageFromResource(data.meanings?.get(0)?.imageUrl)
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}
