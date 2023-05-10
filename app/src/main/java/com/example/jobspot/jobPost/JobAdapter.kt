package com.example.jobspot.jobPost

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobspot.R

class JobAdapter(private val jobList: ArrayList<JobModel>) :
    RecyclerView.Adapter<JobAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.job_card, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCom = jobList[position]
        holder.jobTitle.text = currentCom.title
        holder.jobType.text = currentCom.type
        holder.jobExperience.text = currentCom.age
        holder.jobPayment.text = currentCom.payment
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val jobTitle : TextView = itemView.findViewById(R.id.showJTitle)
        val jobType : TextView = itemView.findViewById(R.id.showType)
        val jobExperience : TextView = itemView.findViewById(R.id.showJAge)
        val jobPayment : TextView = itemView.findViewById(R.id.showJPayment)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}