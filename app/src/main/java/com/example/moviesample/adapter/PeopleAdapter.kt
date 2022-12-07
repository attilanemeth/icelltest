package com.example.moviesample.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesample.R
import com.example.moviesample.api.model.Person
import com.example.moviesample.binding
import com.example.moviesample.databinding.PersonItemBinding

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    private val items: MutableList<Person> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val binding = parent.binding<PersonItemBinding>(R.layout.person_item)
        return PeopleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        with(holder.binding) {
            this.person = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItems(people: List<Person>) {
        items.addAll(people)
       notifyDataSetChanged()
    }

    class PeopleViewHolder(val binding: PersonItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}