package com.example.shimmerfirsttest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shimmerfirsttest.databinding.UserListCardBinding
import com.example.shimmerfirsttest.model.User

class UserListAdapter(
    private val context: Context,
    private var userList: List<User>
): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: UserListCardBinding): RecyclerView.ViewHolder(binding.root)

    fun swapData(newList: List<User>){
        this.userList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserListCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(userList[position]){
                Glide.with(context).load(icon).into(binding.imageViewPic)
                binding.textViewName.text = name
                binding.textViewStatus.text = status
            }
        }
    }
}

