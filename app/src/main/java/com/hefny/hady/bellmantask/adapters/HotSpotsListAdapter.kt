package com.hefny.hady.bellmantask.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hefny.hady.bellmantask.R
import com.hefny.hady.bellmantask.models.HotSpotsItem
import kotlinx.android.synthetic.main.list_item.view.*

class HotSpotsListAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HotSpotsItem>() {

        override fun areItemsTheSame(oldItem: HotSpotsItem, newItem: HotSpotsItem): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HotSpotsItem,
            newItem: HotSpotsItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<HotSpotsItem>) {
        differ.submitList(list)
    }

    class MyViewHolder
    constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: HotSpotsItem) = with(itemView) {
            itemView.name.text = item.name
            itemView.category_name.text = getCategoryName(item)
            Log.d("AppDebug", "bind: ${item.photos}")
            Glide.with(itemView.context)
                .load(getImage(item))
                .error(R.drawable.no_image_available)
                .into(itemView.image)
        }

        private fun getCategoryName(item: HotSpotsItem): String {
            return if (item.categories!!.isNotEmpty()) {
                item.categories[0]?.name.toString()
            } else ""
        }

        private fun getImage(item: HotSpotsItem): String {
            return if (item.photos!!.isNotEmpty()) {
                item.photos[0].toString()
            } else ""
        }
    }
}