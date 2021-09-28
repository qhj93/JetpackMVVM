package com.qhj.jetpackmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.qhj.jetpackmvvm.bean.GankResultBean
import com.qhj.jetpackmvvm.databinding.ItemAdapterBinding

/**
 * Created by 7in on 2021-09-28 9:50
 */
class MyRecyclerViewAdapter(private val data: List<GankResultBean>) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bean = data[position]
        val url = bean.url
        holder.binding.ivGirl.load(url)
    }

    override fun getItemCount(): Int {
        return data.size;
    }
}