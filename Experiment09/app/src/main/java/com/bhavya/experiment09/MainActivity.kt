package com.bhavya.experiment09

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var studentNameInput: EditText
    private lateinit var darkModeCheckbox: CheckBox
    private lateinit var noteTitleInput: EditText
    private lateinit var noteContentInput: EditText
    private lateinit var emptyNotesText: TextView
    private lateinit var notesContainer: LinearLayout
    private lateinit var databaseHelper: NotesDatabaseHelper

    private val preferences by lazy {
        getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentNameInput = findViewById(R.id.studentNameInput)
        darkModeCheckbox = findViewById(R.id.darkModeCheckbox)
        noteTitleInput = findViewById(R.id.noteTitleInput)
        noteContentInput = findViewById(R.id.noteContentInput)
        emptyNotesText = findViewById(R.id.emptyNotesText)
        notesContainer = findViewById(R.id.notesContainer)

        databaseHelper = NotesDatabaseHelper(this)

        findViewById<Button>(R.id.savePreferencesButton)
            .setOnClickListener {
                savePreferences()
            }

        findViewById<Button>(R.id.saveNoteButton)
            .setOnClickListener {
                saveNote()
            }

        loadPreferences()
        displayNotes()
    }

    private fun savePreferences() {
        val studentName = studentNameInput.text.toString().trim()

        if (studentName.isEmpty()) {
            studentNameInput.error = "Please enter your name"
            studentNameInput.requestFocus()
            return
        }

        preferences.edit()
            .putString(KEY_STUDENT_NAME, studentName)
            .putBoolean(KEY_DARK_MODE, darkModeCheckbox.isChecked)
            .apply()

        Toast.makeText(
            this,
            "Preferences saved successfully",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun loadPreferences() {
        studentNameInput.setText(
            preferences.getString(KEY_STUDENT_NAME, "")
        )

        darkModeCheckbox.isChecked =
            preferences.getBoolean(KEY_DARK_MODE, false)
    }

    private fun saveNote() {
        val title = noteTitleInput.text.toString().trim()
        val content = noteContentInput.text.toString().trim()

        if (title.isEmpty()) {
            noteTitleInput.error = "Please enter a note title"
            noteTitleInput.requestFocus()
            return
        }

        if (content.isEmpty()) {
            noteContentInput.error = "Please enter note content"
            noteContentInput.requestFocus()
            return
        }

        val result = databaseHelper.insertNote(title, content)

        if (result != -1L) {
            noteTitleInput.text.clear()
            noteContentInput.text.clear()
            displayNotes()

            Toast.makeText(
                this,
                "Note saved successfully",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this,
                "Unable to save note",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun displayNotes() {
        notesContainer.removeAllViews()

        val notes = databaseHelper.getAllNotes()
        emptyNotesText.visibility =
            if (notes.isEmpty()) View.VISIBLE else View.GONE

        for (note in notes) {
            val noteCard = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(16, 12, 16, 12)
                setBackgroundColor(Color.WHITE)

                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 0, 0, 12)
                layoutParams = params
            }

            val titleView = TextView(this).apply {
                text = note.title
                textSize = 18f
                setTextColor(Color.rgb(27, 43, 85))
                setTypeface(null, android.graphics.Typeface.BOLD)
            }

            val contentView = TextView(this).apply {
                text = note.content
                textSize = 15f
                setTextColor(Color.rgb(52, 64, 84))
                setPadding(0, 6, 0, 8)
            }

            val deleteButton = Button(this).apply {
                text = "Delete Note"
                isAllCaps = false
                setOnClickListener {
                    deleteNote(note.id)
                }
            }

            noteCard.addView(titleView)
            noteCard.addView(contentView)
            noteCard.addView(deleteButton)
            notesContainer.addView(noteCard)
        }
    }

    private fun deleteNote(noteId: Long) {
        val deletedRows = databaseHelper.deleteNote(noteId)

        if (deletedRows > 0) {
            displayNotes()

            Toast.makeText(
                this,
                "Note deleted",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this,
                "Unable to delete note",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroy() {
        if (::databaseHelper.isInitialized) {
            databaseHelper.close()
        }
        super.onDestroy()
    }

    companion object {
        private const val PREFERENCES_NAME = "student_preferences"
        private const val KEY_STUDENT_NAME = "student_name"
        private const val KEY_DARK_MODE = "dark_mode"
    }
}