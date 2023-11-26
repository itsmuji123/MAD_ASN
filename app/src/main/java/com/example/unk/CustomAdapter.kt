
import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CursorAdapter
import android.widget.TextView
import com.example.unk.DBHelper
import com.example.unk.R

class CustomAdapter(context: Context, cursor: Cursor?, flags: Int) :
    CursorAdapter(context, cursor, flags) {

    override fun newView(context: Context, cursor: Cursor, parent: ViewGroup): View {
        // Inflate the custom layout for each row
        return LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)
    }

    override fun bindView(view: View, context: Context, cursor: Cursor) {
        // Get references to the TextViews in the custom layout
        val nameTextView: TextView = view.findViewById(R.id.textName)
        val numberTextView: TextView = view.findViewById(R.id.textNumber)
        val editButton: Button = view.findViewById(R.id.btnEdit)
        val deleteButton: Button = view.findViewById(R.id.btnDelete)

        // Retrieve the values from the cursor using column names
        val name = cursor.getString(cursor.getColumnIndexOrThrow(Utils.NAME_COl))
        val number = cursor.getString(cursor.getColumnIndexOrThrow(Utils.Num_COL))

        // Set the values to the TextViews
        nameTextView.text = "Name: $name"
        numberTextView.text = "Number: $number"

        editButton.setOnClickListener {
            // Handle the Edit button click
            // You can implement the logic to edit the contact here
            // For example, you can open an edit activity with the contact details
        }

        deleteButton.setOnClickListener {
            // Handle the Delete button click
            // You can implement the logic to delete the contact here
            // For example, you can show a confirmation dialog and delete the contact if confirmed
        }
    }

    // Override the getItemId method to return the value of the 'id' column
    override fun getItemId(position: Int): Long {
        return cursor?.getLong(cursor.getColumnIndexOrThrow(Utils.ID_COL)) ?: 0
    }
}
