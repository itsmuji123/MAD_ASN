import android.database.Cursor
import com.example.unk.DBHelper

class Utils {
    companion object{
        //TODO: used for data-passing during navigation
        val ID = "_ID";
        val NAME = "NAME";
        val ROLLNO = "ROLL NO";
        //TODO: used inside DatabaseHelper class
        val DATABASE_NAME = "PUCIT1";
        val DATABASE_VERSION = 1
        val TABLE_NAME = "Students";
        val ID_COL = "_id";
        val NAME_COl = "Name";
        val Num_COL = "No";
        //TODO: used inside HomeActivity&#39;s
        lateinit var myCursorAdapter:MyCursorAdapter
        lateinit var dbHelper: DBHelper
        fun refreshListView(){
            val cursor: Cursor? = dbHelper.readAll()
            myCursorAdapter.changeCursor(cursor)
        }
    }
}