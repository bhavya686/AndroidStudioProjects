package com.bhavya.experiment09

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotesDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NOTES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITLE TEXT NOT NULL,
                $COLUMN_CONTENT TEXT NOT NULL
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NOTES")
        onCreate(db)
    }

    fun insertNote(title: String, content: String): Long {
        val values = ContentValues().apply {
            put(COLUMN_TITLE, title)
            put(COLUMN_CONTENT, content)
        }

        return writableDatabase.insert(TABLE_NOTES, null, values)
    }

    fun getAllNotes(): List<Note> {
        val notes = mutableListOf<Note>()

        val cursor = readableDatabase.query(
            TABLE_NOTES,
            arrayOf(COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT),
            null,
            null,
            null,
            null,
            "$COLUMN_ID DESC"
        )

        cursor.use {
            while (it.moveToNext()) {
                notes.add(
                    Note(
                        id = it.getLong(it.getColumnIndexOrThrow(COLUMN_ID)),
                        title = it.getString(
                            it.getColumnIndexOrThrow(COLUMN_TITLE)
                        ),
                        content = it.getString(
                            it.getColumnIndexOrThrow(COLUMN_CONTENT)
                        )
                    )
                )
            }
        }

        return notes
    }

    fun deleteNote(id: Long): Int {
        return writableDatabase.delete(
            TABLE_NOTES,
            "$COLUMN_ID = ?",
            arrayOf(id.toString())
        )
    }

    companion object {
        private const val DATABASE_NAME = "student_notes.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NOTES = "notes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"
    }
}

data class Note(
    val id: Long,
    val title: String,
    val content: String
)