import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CursorAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.unk.DBHelper
import com.example.unk.EditActivity
import com.example.unk.R

class MyCursorAdapter(context: Context, cursor: Cursor?) :
    CursorAdapter(context, cursor, 0) {
    override fun getCount(): Int {

        return cursor.count
    }
    // The newView method is used to inflate a new view and return it,
// you don&#39;t bind any data to the view at this point.
    override fun newView(context: Context?, cursor: Cursor?, parent:
    ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.row_item,
            parent, false)
    }
    // The bindView method is used to bind all data to a given view
// such as setting the text on a TextView.
    override fun bindView(view: View, context: Context, cursor: Cursor) {
        val nameTv = view.findViewById<View>(R.id.textName) as TextView
        val rollNoTv = view.findViewById<View>(R.id.textNumber) as TextView
        val editButton: Button = view.findViewById(R.id.btnEdit)
        val deleteButton: Button = view.findViewById(R.id.btnDelete)

        // Extract column indices
        val idIndex = cursor.getColumnIndex(Utils.ID_COL)
        val nameIndex = cursor.getColumnIndex(Utils.NAME_COl)
        val rollNoIndex = cursor.getColumnIndex(Utils.Num_COL)

        if (idIndex >= 0 && nameIndex >= 0 && rollNoIndex >= 0) {
            val id = cursor.getInt(idIndex)
            val name = cursor.getString(nameIndex)
            val rollNo = cursor.getString(rollNoIndex)

            if (name.isEmpty() && rollNo.isEmpty()) {
                Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
            } else {
                nameTv.text = name
                rollNoTv.text = rollNo


                // Set click listener for the entire item
                view.setOnClickListener {
                    // Extract the phone number from the cursor
                    val phoneNumber = cursor.getString(rollNoIndex)
                    // Open the phone dialer with the extracted phone number
                    openPhoneDialer(context, phoneNumber)
                }

                // Set click listener for the delete button
                deleteButton.setOnClickListener {
                    // Call the deleteContact function from your DBHelper
                    val db = DBHelper(context, null)
                    db.deleteContact(id)
                    // Optionally, refresh the ListView or handle UI updates
                    notifyDataSetChanged()
                    Utils.refreshListView()
                    Toast.makeText(context, "Contact deleted", Toast.LENGTH_SHORT).show()
                }

                // Set click listener for the edit button
                editButton.setOnClickListener {

                    // Create an intent to start EditActivity
                    val intent = Intent(context, EditActivity::class.java)
                    // Pass the contact details to EditActivity
                    intent.putExtra(Utils.ID_COL, id)
                    intent.putExtra(Utils.NAME_COl, name)
                    intent.putExtra(Utils.Num_COL, rollNo)
                    // Start EditActivity
                    context.startActivity(intent)
                }
            }
        }
    }


}


private fun openPhoneDialer(context: Context, phoneNumber: String) {
    // Create an Intent to open the phone dialer
    val dialerIntent = Intent(Intent.ACTION_DIAL)
    // Set the phone number to dial
    dialerIntent.data = Uri.parse("tel:$phoneNumber")

    // Check if there's an app to handle the Intent
    if (dialerIntent.resolveActivity(context.packageManager) != null) {
        // Start the dialer activity
        context.startActivity(dialerIntent)
    } else {
        // Handle the case where there's no app to handle the Intent
        Toast.makeText(context, "No app to handle phone dialing.", Toast.LENGTH_SHORT).show()
    }
}