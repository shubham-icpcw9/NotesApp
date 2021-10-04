package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(val listener : INotesAdapter) : RecyclerView.Adapter<NotesAdapter.NoteVH>() {

    private val allNotes = ArrayList<Note>()

    inner class NoteVH(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.noteTv)
        val deleteBtn = itemView.findViewById<ImageView>(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVH {
        val viewHolder = NoteVH(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
        viewHolder.deleteBtn.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteVH, position: Int) {
        val currNote = allNotes[position]
        holder.textView.text = currNote.text
    }

    override fun getItemCount() : Int  {
        return allNotes.size
    }

    fun updateList(newList : List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}

interface INotesAdapter{
    fun onItemClicked(note: Note)
}