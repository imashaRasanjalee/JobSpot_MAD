package com.malkinfo.userinformation.adapter

import android.content.Context
import android.net.Uri
import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.malkinfo.userinformation.R
import com.malkinfo.userinformation.adapter.viewholder.UserViewHolder
import com.malkinfo.userinformation.model.HelperSQL
import com.malkinfo.userinformation.model.UserDatas
import com.malkinfo.userinformation.view.UserListFragmentDirections

class UserListAdapter(
        private val c:Context,listUser:ArrayList<UserDatas>
):RecyclerView.Adapter<UserViewHolder>()
{
    /** ok run it */


    private val listUs:ArrayList<UserDatas>
    private val mDataBase:HelperSQL
    init {
        this.listUs = listUser
        mDataBase = HelperSQL(c)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val infalter = LayoutInflater.from(parent.context)
        val v = infalter.inflate(R.layout.user_item,parent,false)
        return UserViewHolder(v)

    }
    /**ok run */

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       val newList = listUs[position]
        holder.userName.text = "Name: ${newList.name}"
        holder.userMob.text = "Mb. : ${newList.mobN}"
        if (newList.imageUr == "null"){
            holder.userImg.setImageResource(R.drawable.ic_perm)
        }else{
            holder.userImg.setImageURI(Uri.parse(newList.imageUr))
        }
        holder.v.setOnClickListener {
            val ac = UserListFragmentDirections.UserListToDetail().setUserDatas(newList)
            Navigation.findNavController(it).navigate(ac)
        }
        holder.deleteBtn.setOnClickListener {
            mDataBase.deleteInfo(newList.id.toInt())
            listUs.removeAt(position)
            notifyDataSetChanged()
        }
        holder.editView.setOnClickListener {
            val ac = UserListFragmentDirections.UserToAddInfo()
            Navigation.findNavController(it).navigate(ac)
        }

    }


    override fun getItemCount(): Int {
        return  listUs.size
    }
}