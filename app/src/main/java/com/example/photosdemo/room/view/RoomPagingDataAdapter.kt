package com.example.photosdemo.room.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.photosdemo.*
import com.example.photosdemo.common.app.PDApplication
import com.example.photosdemo.room.model.RoomPhotos
import kotlinx.android.synthetic.main.item_room_other.view.*

class RoomPagingDataAdapter<T : RoomPhotos>(
    private val glide: RequestManager
) : PagingDataAdapter<T, RoomViewHolder>(RoomDiffUtil()) {

    private var itemViewType = RoomItemViewType.TOP

    override fun getItemViewType(position: Int): Int {
        itemViewType = if (position == RoomItemViewType.TOP.position) {
            RoomItemViewType.TOP
        } else {
            RoomItemViewType.OTHER
        }
        return itemViewType.layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return RoomViewHolder(view, glide)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(position, itemViewType, item)
    }
}

class RoomDiffUtil<T : RoomPhotos> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.id() == newItem.id()

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem
}

class RoomViewHolder(
    itemView: View,
    private val glide: RequestManager
) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        position: Int,
        itemViewType: RoomItemViewType,
        item: RoomPhotos?
    ) {
        if (itemViewType == RoomItemViewType.TOP) return
        if (position % 2 == 0) {
            val params: ViewGroup.LayoutParams = itemView.layoutParams
            val marginParams: ViewGroup.MarginLayoutParams =
                if (params is ViewGroup.MarginLayoutParams) {
                    params
                } else {
                    ViewGroup.MarginLayoutParams(params)
                }
            marginParams.marginEnd = itemViewType.margin
        }
        itemView.image_view.setImageDrawable(null)
        item?.let {
            glide.load(it.largeImageUrl())
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(itemView.image_view)
        }
        itemView.text_view.text = item?.user()
    }
}

enum class RoomItemViewType(
    @LayoutRes val layout: Int,
    val margin: Int,
    val position: Int,
) {
    TOP(
        R.layout.item_room_top,
        PDApplication.instance.resources.getDimension(R.dimen.room_paging_data_adapter_margin).toInt(),
        0
    ),
    OTHER(
        R.layout.item_room_other,
        PDApplication.instance.resources.getDimension(R.dimen.room_paging_data_adapter_margin).toInt(),
        1
    ),
}

