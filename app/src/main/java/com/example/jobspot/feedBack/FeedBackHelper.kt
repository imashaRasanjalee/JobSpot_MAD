package com.example.jobspot.feedBack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobspot.R

class FeedBackHelper(private val feedBacks: ArrayList<FeedBackModel>) :
    RecyclerView.Adapter<FeedBackHelper.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.feedback_card, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCom = feedBacks[position]
        holder.title.text = currentCom.title
        holder.userName.text = currentCom.name
        holder.mobile.text = currentCom.phone
    }

    override fun getItemCount(): Int {
        return feedBacks.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val title : TextView = itemView.findViewById(R.id.showTitle)
        val userName : TextView = itemView.findViewById(R.id.showName)
        val mobile : TextView = itemView.findViewById(R.id.showMobile)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}