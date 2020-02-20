package app.kotlinassignment.ui.home

import android.text.TextUtils
import androidx.recyclerview.widget.DiffUtil
import lib.apidata.data.ItemData

class ItemDiffUtil : DiffUtil.ItemCallback<ItemData>() {
    override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
        return TextUtils.equals(oldItem.title, newItem.title)
                && oldItem.completed == newItem.completed
                && TextUtils.equals(oldItem.userId, newItem.userId)
    }
}