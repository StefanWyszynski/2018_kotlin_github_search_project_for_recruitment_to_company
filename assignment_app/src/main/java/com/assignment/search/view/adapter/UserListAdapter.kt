package com.assignment.search.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.assignment.R
import java.util.*

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 *
 */
class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    private var repositiories: MutableList<IGithubSearchResultMarker> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_result_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repositiories[position], onItemClickListener)
    }


    fun setRepositiories(users: List<IGithubSearchResultMarker>?) {
        if (users != null) {
            repositiories.clear()
            repositiories.addAll(users)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return repositiories.size
    }

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal val userName: TextView
        internal val repoName: TextView

        init {
            userName = view.findViewById(R.id.userName)
            repoName = view.findViewById(R.id.repositoryName)
        }

        fun bind(searchResultObject: IGithubSearchResultMarker, onItemClickListener: OnItemClickListener) {
            itemView.setOnClickListener({
                onItemClickListener.onListItemClick(searchResultObject)
            })
            userName.text = searchResultObject.getUserName()
            repoName.text = searchResultObject.getRepoName()
        }
    }
}

interface OnItemClickListener {

    fun onListItemClick(item: IGithubSearchResultMarker)
}
