package com.mirshad.myapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mirshad.myapp.R
import com.mirshad.myapp.adapter.NoteAdapter
import com.mirshad.myapp.viewmodel.NoteViewModel


class HomeFragment : Fragment() {

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize RecyclerView and Adapter
        val recyclerView: RecyclerView = view.findViewById(R.id.rv_notes)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        noteAdapter = NoteAdapter { note ->
            val action = HomeFragmentDirections.actionHomeFragmentToViewNoteFragment(note.id)
            findNavController().navigate(action)
        }
        recyclerView.adapter = noteAdapter

        // Initialize ViewModel
        noteViewModel = NoteViewModel(requireActivity().application)

        // Observe LiveData for notes
        noteViewModel.getAllNotes().observe(viewLifecycleOwner, Observer { notes ->
            noteAdapter.submitList(notes)
        })



        view.findViewById<FloatingActionButton>(R.id.btn_new_note).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)
        }

        return view
    }


}