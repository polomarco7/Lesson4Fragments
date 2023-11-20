package com.example.lesson3fragments.part2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.example.lesson3fragments.R
import com.example.lesson3fragments.databinding.ListUserBinding

class ContactsAdapter(
    private val onClick: (Users) -> Unit
) : ListAdapter<Users, ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            Glide
                .with(holder.itemView)
                .load(item.imageUrl)
                .signature(ObjectKey(System.currentTimeMillis().toString()))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(image)

            nameTextView.text =
                holder.itemView.context.getString(R.string.name, item?.firstName, item?.lastName)
            phoneTextView.text = item?.phoneNumber
        }

        holder.itemView.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }

    fun updateAll(newList: List<Users>) {
        submitList(newList)
    }

    fun updateContact(users: Users?) {
        val newItems = ArrayList(currentList)

        if (users == null) return
        for (item in newItems) {
            if (item.id == users.id) {
                val position = users.id
                newItems[position!!] = users
                submitList(newItems) {
                    notifyItemChanged(position)
                }
                break
            }
        }
    }
}

class ViewHolder(val binding: ListUserBinding) : RecyclerView.ViewHolder(binding.root)

class DiffUtilCallback : DiffUtil.ItemCallback<Users>() {
    override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean =
        oldItem == newItem
}