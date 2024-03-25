package com.example.labs.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.labs.R
import com.example.labs.data.entities.Note
import com.example.labs.data.viewmodels.NoteViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class AddFragment : Fragment() {
    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_add,
            container,
            false
        );

        ViewModelProvider(this)[NoteViewModel::class.java].also {
            this.viewModel = it
        }

        val datePicker = view.findViewById<DatePicker>(R.id.datePicker)

        val today = LocalDate.now()
        datePicker.minDate = today.toEpochDay() * 24 * 60 * 60 * 1000

        val addBtn = view.findViewById<Button>(R.id.add_btn_done)
        addBtn.setOnClickListener {
            addNote()
        }

        val cancelBtn = view.findViewById<Button>(R.id.add_btn_cancel)
        cancelBtn.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }

        return view
    }

    private fun addNote() {
        val datePicker = view?.findViewById<DatePicker>(R.id.datePicker);
        val noteText = view?.findViewById<EditText>(R.id.add_et_note_name)
            ?.text.toString()

        if (datePicker == null) {
            return;
        }

        if (noteText.isEmpty()) {
            Toast.makeText(
                view?.context,
                context?.resources?.getString(R.string.should_add_note),
                Toast.LENGTH_LONG
            ).show();

            return;
        }

        if (noteText.length < Note.MINIMUM_NOTE_LENGTH) {
            Toast.makeText(
                context,
                context?.resources?.getString(R.string.note_min_length),
                Toast.LENGTH_LONG
            ).show()

            return;
        }

        val untilDateSelected = LocalDateTime.of(
            LocalDate.of(datePicker.year, datePicker.month + 1, datePicker.dayOfMonth),
            LocalTime.MIN
        )

        val note = Note(0, noteText, untilDateSelected);
        viewModel.addNote(note);
        
        Toast.makeText(
            view?.context,
            context?.resources?.getString(R.string.added),
            Toast.LENGTH_LONG
        ).show();

        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }
}