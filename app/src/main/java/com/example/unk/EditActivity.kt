package com.example.unk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etNumber: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        etName = findViewById(R.id.editTextName)
        etNumber = findViewById(R.id.editTextNumber)
        btnSave = findViewById(R.id.btnSave)

        // Retrieve data from intent extras
        val id = intent.getIntExtra(Utils.ID_COL, -1)
        val name = intent.getStringExtra(Utils.NAME_COl)
        val number = intent.getStringExtra(Utils.Num_COL)

        // Set the retrieved data to the EditTexts
        etName.setText(name)
        etNumber.setText(number)

        btnSave.setOnClickListener {
            // Update the contact using DBHelper's updateContact function
            if(etName.text.toString()!="" && etNumber.text.toString()!="") {
                val dbHelper = DBHelper(this, null)
                dbHelper.updateContact(
                    id.toLong(),
                    etName.text.toString(),
                    etNumber.text.toString()
                )
                // Optionally, handle UI updates or navigate back
                finish()
            }
            else{
                Toast.makeText(this, "Field empty, Try again!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
