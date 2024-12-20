package com.mirshad.myapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mirshad.myapp.R
import com.mirshad.myapp.databinding.FragmentNewNoteBinding
import com.mirshad.myapp.data.model.Note
import com.mirshad.myapp.viewmodel.NoteViewModel
import java.util.Date

class NewNoteFragment : Fragment() {

    private lateinit var binding: FragmentNewNoteBinding
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveNote.setOnClickListener {
            saveNote()
            findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)
        }
    }

    private fun saveNote() {
        val title = binding.etNoteTitle.text.toString().trim()
        val description = binding.etNoteDescription.text.toString().trim()

        if (title.isNotEmpty() && description.isNotEmpty()) {
            val newNote = Note(
                Date = Date(),
                title = title,
                description = description
            )

            // Call the ViewModel function to save the note
            noteViewModel.insert(newNote)
            Toast.makeText(context, "Note Saved", Toast.LENGTH_SHORT).show()
            binding.etNoteTitle.text?.clear()
            binding.etNoteDescription.text?.clear()

            // Optionally, you can navigate back to the previous fragment or perform any other action
        } else {

            Toast.makeText(context, "Please enter title and description", Toast.LENGTH_SHORT).show()


        }
    }
}
