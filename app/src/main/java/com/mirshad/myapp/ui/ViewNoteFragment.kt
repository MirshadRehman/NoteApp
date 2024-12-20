package com.mirshad.myapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mirshad.myapp.R

import com.mirshad.myapp.databinding.FragmentViewNoteBinding
import com.mirshad.myapp.viewmodel.NoteViewModel

class ViewNoteFragment : Fragment() {

    private var _binding: FragmentViewNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewNoteBinding.inflate(inflater, container, false)
        val view = binding.root

        // Retrieve the note ID passed from HomeFragment
        val noteId = arguments?.let { ViewNoteFragmentArgs.fromBundle(it).id }

        // Initialize ViewModel
        noteViewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)

        // Fetch the corresponding note from ViewModel
        noteId?.let { id ->
            noteViewModel.getNoteById(id).observe(viewLifecycleOwner, Observer { note ->
                note?.let {
                    // Populate UI with note details
                    binding.tvNoteTitle.text = note.title
                    binding.tvNoteDescription.text = note.description
                }
            })
        }

        binding.btnBack.setOnClickListener {
           findNavController().navigate(R.id.action_viewNoteFragment_to_homeFragment)
        }

        binding.btnEdit.setOnClickListener {
            val action = ViewNoteFragmentDirections.actionViewNoteFragmentToEditNoteFragment(noteId!!)
            findNavController().navigate(action)
        }

        binding.btnDelete.setOnClickListener {
            if (noteId != -1L) {
                // Call delete function in ViewModel
                noteViewModel.deleteNoteById(noteId!!)
            }
            // Navigate back to previous screen
            findNavController().navigateUp()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
