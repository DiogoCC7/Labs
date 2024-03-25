package com.example.labs.fragments.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.labs.MainActivity
import com.example.labs.R
import com.example.labs.data.viewmodels.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {
    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val btnAdd = view.findViewById<FloatingActionButton>(R.id.btn_add_new_note_from_list)
        val btnGoBack = view.findViewById<FloatingActionButton>(R.id.list_fr_go_back)
        val displayNotice = view.findViewById<LinearLayout>(R.id.list_fr_display_none)

        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        viewModel.readAllNotes.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)

            displayNotice.visibility = if (adapter.itemCount == 0) View.VISIBLE else View.GONE
        })

        btnAdd.setOnClickListener(){
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        btnGoBack.setOnClickListener() {
            startActivity(MainActivity.newIntent(requireContext()))
        }

        return view
    }
}