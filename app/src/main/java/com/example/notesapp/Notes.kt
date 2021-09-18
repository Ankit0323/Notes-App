package com.example.notesapp

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text
import java.util.jar.Attributes

@Entity(tableName = "notes_table")
class Notes( @ColumnInfo(name ="text") var text:String, @ColumnInfo(name ="date") var date: String?) {

    @PrimaryKey(autoGenerate  = true) var id=0

}