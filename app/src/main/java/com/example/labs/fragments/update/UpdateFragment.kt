package com.example.labs.fragments.update

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.labs.R
import com.example.labs.data.entities.Note
import com.example.labs.data.viewmodels.NoteViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var viewModel: NoteViewModel

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_update,
            container,
            false
        );

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        view.findViewById<TextView>(R.id.update_title).text = args.currentNote.note

        val datePicker = view.findViewById<DatePicker>(R.id.datePicker)
        val today = LocalDate.now()
        datePicker.minDate = today.toEpochDay() * 24 * 60 * 60 * 1000

        val untilDate = args.currentNote.utilDate
        datePicker.updateDate(untilDate.year, untilDate.monthValue, untilDate.dayOfMonth)

        val updateBtn = view.findViewById<Button>(R.id.update_btn_done)
        updateBtn.setOnClickListener {
            updateNote()
        }

        val removeBtn = view.findViewById<Button>(R.id.update_btn_rm)
        removeBtn.setOnClickListener {
            removeNote()
        }

        val cancelButton = view.findViewById<Button>(R.id.update_btn_cancel)
        cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        return view;
    }

    private fun updateNote() {
        val datePicker = view?.findViewById<DatePicker>(R.id.datePicker)
        val noteText = view?.findViewById<EditText>(R.id.update_et_note)
            ?.text.toString()

        if (datePicker == null) {
            return;
        }

        if (noteText.isEmpty()) {
            Toast.makeText(
                context,
                context?.resources?.getString(R.string.is_not_possible_to_update),
                Toast.LENGTH_LONG
            ).show()

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

        val untilModifiedDate = LocalDateTime.of(
            LocalDate.of(datePicker.year, datePicker.month + 1, datePicker.dayOfMonth),
            LocalTime.MIN
        )

        val note = Note(args.currentNote.id, noteText, untilModifiedDate)
        viewModel.updateNote(note);

        Toast.makeText(
            context,
            context?.resources?.getString(R.string.updated),
            Toast.LENGTH_LONG
        ).show()

        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    private fun removeNote() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton(context?.resources?.getString(R.string.yes)) { _, _ ->
            viewModel.deleteNote(args.currentNote)
            Toast.makeText(
                requireContext(),
                context?.resources?.getString(R.string.rm_with_sucess),
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        builder.setNegativeButton(context?.resources?.getString(R.string.no)) { _, _ -> }
        builder.setTitle(context?.resources?.getString(R.string.remove))
        builder.setMessage(context?.resources?.getString(R.string.sure_to_remove_note))
        builder.create().show()
    }
}