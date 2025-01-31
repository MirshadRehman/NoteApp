package com.mirshad.myapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mirshad.myapp.R
import com.mirshad.myapp.data.model.Note
import java.text.SimpleDateFormat
import java.util.Locale

class NoteAdapter(private val onItemClick: (Note) -> Unit) :
    ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_layout, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
        holder.itemView.setOnClickListener { onItemClick(note) }
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.tv)
        private val dateTextView: TextView = itemView.findViewById(R.id.tv_note_date)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tv_note_description)

        fun bind(note: Note) {
            titleTextView.text = note.title
            val formattedDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(note.Date)
            dateTextView.text = formattedDate
            descriptionTextView.text = note.description
        }
    }

    class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}
