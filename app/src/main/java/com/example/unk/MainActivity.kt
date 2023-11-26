package com.example.unk

import CustomAdapter
import MyCursorAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.database.Cursor


class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Utils.dbHelper = DBHelper(this, null)

        val cursor = Utils.dbHelper.readAll()
        val listView = findViewById<ListView>(R.id.v)
        val addContact = findViewById<Button>(R.id.addName)
        val en = findViewById<EditText>(R.id.enterName)
        val ec = findViewById<EditText>(R.id.enterAge)
        Utils.myCursorAdapter = MyCursorAdapter(this, cursor)
        listView.adapter = Utils.myCursorAdapter

        addContact.setOnClickListener{
            if(en.text.toString() != "" && ec.text.toString()!= "") {
                Utils.dbHelper.addContact(en.text.toString(), ec.text.toString())
                Utils.refreshListView()
                en.setText("")
                ec.setText("")
            }else{
                Toast.makeText(this, "Field empty, Try again! ", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onResume() {
        super.onResume()
        Utils.refreshListView()
    }

}
