package com.mirshad.myapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mirshad.myapp.R
import com.mirshad.myapp.data.model.Note
import com.mirshad.myapp.databinding.FragmentEditNoteBinding
import com.mirshad.myapp.viewmodel.NoteViewModel
import java.util.Date


class EditNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding?=null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteDate: Date


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditNoteBinding.inflate(inflater,container,false)
        val view = binding.root

        // Retrieve the note ID passed from HomeFragment
        val noteId = arguments?.let { EditNoteFragmentArgs.fromBundle(it).id }

        // Initialize ViewModel
        noteViewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)

        // Fetch the corresponding note from ViewModel
        noteId?.let { id ->
            noteViewModel.getNoteById(id).observe(viewLifecycleOwner, Observer { note ->
                note?.let {
                    // Populate UI with note details
                    noteDate=note.Date
                    binding.etNoteTitle.setText(note.title)
                    binding.etNoteDescription.setText(note.description)
                }
            })
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_editNoteFragment_to_viewNoteFragment)
        }
        binding.btnUpdate.setOnClickListener {
            // Get updated values from EditText fields
            val updatedTitle = binding.etNoteTitle.text.toString()
            val updatedDescription = binding.etNoteDescription.text.toString()

            // Update the note in the ViewModel
            noteId?.let { id ->
                val updatedNote = Note(id, noteDate, updatedTitle, updatedDescription)
                noteViewModel.update(updatedNote)
            }

            // Navigate back to the previous Fragment
            findNavController().navigate(R.id.action_editNoteFragment_to_homeFragment)
        }

        return view
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}