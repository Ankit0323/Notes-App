package com.example.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Notes::class), version = 2, exportSchema = false)
abstract class NotesDatabase :RoomDatabase() {
    abstract fun getNotesDao():NotesDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotesDatabase? = null
val migrate_1_2 : Migration=object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE notes_table ADD COLUMN date TEXT DEFAULT ''")
    }
}
        fun getDatabase(context: Context): NotesDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_database"
                ).addMigrations(migrate_1_2)
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}