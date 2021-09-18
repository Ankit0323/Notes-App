package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note : Notes)

    @Delete
    suspend fun delete(note : Notes)

    @Query("Select * from notes_table order by id ASC")
    fun getAll():LiveData<List<Notes>>

   // @Query("UPDATE notes_table SET text=:text WHERE id=:id")
   // suspend fun update(id:Int,text : String):LiveData<Notes>
    //@Query("UPDATE notes_table SET text=:updated WHERE text=:text")
  // suspend fun update(text: String,updated:String)
    @Update
   suspend fun update(note:Notes)
}