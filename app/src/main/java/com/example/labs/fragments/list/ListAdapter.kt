package com.example.labs.fragments.list

import android.annotation.SuppressLint
import android.content.Context
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
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var notesList = emptyList<Note>()

    inner class MyViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false))
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    private fun getModifiedTimestamp(context: Context, modifiedData: LocalDateTime): String {
        var timeUnit: String = ""
        var modifiedTime: Int = 0

        val calculateModifiedTime = LocalDateTime.now().minusMinutes(modifiedData.minute.toLong())
        val calculatedTimeInMinutes = calculateModifiedTime.minute

        when {
            calculatedTimeInMinutes <= 59 -> {
                timeUnit = context.resources.getString(R.string.time_minutes)
                modifiedTime = calculateModifiedTime.minute
            }
            calculatedTimeInMinutes > 60 -> {
                timeUnit = context.resources.getString(R.string.time_hours)
                modifiedTime = calculateModifiedTime.hour
            }
        }

        return "${context.resources.getString(R.string.time_phrase)} %d %s ${context.resources.getString(R.string.time_ago)}".format(modifiedTime, timeUnit)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = notesList[position]

        val createdAtTxt = holder.view.findViewById<TextView>(R.id.note_created_at)
        val untilDate = holder.view.findViewById<TextView>(R.id.until_date)

        createdAtTxt.text = getModifiedTimestamp(holder.itemView.context, currentItem.updatedAt)
        untilDate.text = currentItem.utilDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        holder.view.findViewById<TextView>(R.id.note_txt).text = currentItem.note

        if(position %2 == 0)
            holder.view.findViewById<ConstraintLayout>(R.id.rowLayout).setBackgroundColor(holder.view.context.getColor(R.color.list))
        else
            holder.view.findViewById<ConstraintLayout>(R.id.rowLayout).setBackgroundColor(holder.view.context.getColor(R.color.list_alt))

        holder.view.findViewById<ConstraintLayout>(R.id.rowLayout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.view.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(note: List<Note>){
        this.notesList = note
        notifyDataSetChanged()
    }
}