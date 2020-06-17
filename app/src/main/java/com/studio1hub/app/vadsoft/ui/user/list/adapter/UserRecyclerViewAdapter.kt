
package com.studio1hub.app.vadsoft.ui.user.list.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.studio1hub.app.vadsoft.R
import com.studio1hub.app.vadsoft.model.User
import com.studio1hub.app.vadsoft.ui.user.detail.UserDetailActivity
import kotlinx.android.synthetic.main.user_item.view.*

class UserRecyclerViewAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<UserRecyclerViewAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            itemView.apply {
                tvusername.text = user.name
                tvuserdesignation.text = user.designation
                ivuserprofile.load(user.avatar) {
                    placeholder(R.drawable.ic_photo)
                    transformations(CircleCropTransformation())
                }
                tvusercity.text = user.city
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view: View
        val inflater = LayoutInflater.from(parent.context)
        view = inflater.inflate(R.layout.user_item, parent, false)
        val viewHolder = DataViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val intent = Intent(parent.context,UserDetailActivity::class.java)
            intent.putExtra("user_id", users[viewHolder.adapterPosition].id)
            parent.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUser(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
}