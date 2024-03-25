package com.example.labs.fragments.list

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.labs.R
import com.example.labs.data.entities.Note
import java.time.LocalDateTime
import java.util.UUID

class UserId private constructor(val id: UUID) {
   companion object {
       fun new(): UserId {
           return UserId(UUID.randomUUID());
       }

       fun from(id: UUID) : UserId {
           return UserId(id);
       }
   }
};

open class Entity<TId>(val id: TId) {
    val createdAt: LocalDateTime = LocalDateTime.now();
    val updatedAt: LocalDateTime = LocalDateTime.now();
    val deletedAt: LocalDateTime? = LocalDateTime.now();
}

class UserData(val name: String, val age: Int) : Entity<UserId>(UserId.new()) {};

interface Repository<TEntity, TId>
    where TEntity : Entity<TId> {
    fun get(tId: TId): TEntity;
    fun create(tEntity: TEntity);
}

class UserRepository : Repository<UserData, UserId> {
    private val users = emptyList<UserData>();

    override fun get(tId: UserId): UserData {
        return users.find { it == tId }
    }

    override fun create(tEntity: UserData) {
        TODO("Not yet implemented")
    }
}

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var noteSomething = emptyList<>()
    private var notesList = emptyList<Note>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = notesList[position]
        holder.itemView.findViewById<TextView>(R.id.note_txt).text = currentItem.note

        if(position%2 == 0)
            holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setBackgroundColor(Color.parseColor("#d6d4e0"))
        else
            holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setBackgroundColor(Color.parseColor("#b8a9c9"))

        holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(note: List<Note>){
        this.notesList = note
        this.notifyDataSetChanged()
    }
}