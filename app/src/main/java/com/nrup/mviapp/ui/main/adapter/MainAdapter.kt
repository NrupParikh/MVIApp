package com.nrup.mviapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nrup.mviapp.data.model.User
import com.nrup.mviapp.databinding.ItemLayoutBinding

class MainAdapter(
    private val users: ArrayList<User>
) : RecyclerView.Adapter<MainAdapter.MyDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDataViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyDataViewHolder(binding = binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: MyDataViewHolder, position: Int) {
        with(holder) {
            with(users[position]) {

                binding.textViewUserName.text = this.name
                binding.textViewUserEmail.text = this.email
                binding.textViewUserName.text = this.name

                Glide.with(binding.imageViewAvatar.context)
                    .load(this.avatar)
                    .into(binding.imageViewAvatar)
            }
        }
    }

    inner class MyDataViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

}