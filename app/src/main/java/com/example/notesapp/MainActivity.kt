package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*


lateinit var viewModel: NotesViewModel
class MainActivity : AppCompatActivity() ,recyclerAdapter.click{
    private lateinit var binding:ActivityMainBinding
    private lateinit var mAdapter: recyclerAdapter
    var textForUpdate=""
    lateinit var myNote:Notes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycler.layoutManager=LinearLayoutManager(this)
        mAdapter= recyclerAdapter(this)
        binding.recycler.adapter=mAdapter
        viewModel=ViewModelProvider(this,ViewModelProvider
            .AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {
                mAdapter.refresh(it)
        })
     //   binding.recycler
    }

    fun create(view: View)  {

        val inputNotes=binding.inputText.text.toString()
        if(inputNotes.isNotEmpty()) {
            val date= addDate()
            viewModel.insertNote(Notes(inputNotes,date))
        }
        binding.inputText.setText("")
    }
    fun update(view: View) {
        val inputNotes = binding.inputText.text.toString()

        if(inputNotes.isNotEmpty()) {
            myNote.text=inputNotes
            viewModel.updateNote(myNote)
        }
        binding.create.visibility=View.VISIBLE
        binding.delete.visibility=View.GONE
        binding.update.visibility=View.GONE
   
        binding.inputText.setText("")
    }
    fun delete(view: View) {
        val inputNotes = binding.inputText.text.toString()
        if(inputNotes.isNotEmpty()) {
            viewModel.deleteNote(myNote)
        }

        binding.create.visibility=View.VISIBLE
        binding.delete.visibility=View.GONE
        binding.update.visibility=View.GONE
        binding.inputText.setText("")
    }



    override fun itemClick(note: Notes) {
        binding.create.visibility=View.GONE
        binding.delete.visibility=View.VISIBLE
        binding.update.visibility=View.VISIBLE

        binding.inputText.setText(note.text)
        textForUpdate=note.text
        myNote = note
    }
    fun addDate() : String {
        val calendar=Calendar.getInstance()
        val dateTime=calendar.time
        val sdf=SimpleDateFormat("dd MMM yyyy    HH:mm", Locale.getDefault())
       return sdf.format(dateTime)

    }
}