package huji.ac.il.todoapplistex4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Omer on 27/04/2015.
 */
public class TodoAppDBHelper extends SQLiteOpenHelper {
    public static final String TODO_TABLE_NAME = "todoItems";
    private final String TODO_TABLE_CREATION = "CREATE TABLE "+TODO_TABLE_NAME+"(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            TODO_COLUMN_CONTENT + " TEXT NOT NULL," +
            TODO_COLUMN_EXPIRATION + " INTEGER NOT NULL);";
    private static final String DATABASE_NAME = "todoapp.db";
    public static final String TODO_COLUMN_CONTENT = "TODO_CONTENT";
    public static final String TODO_COLUMN_EXPIRATION = "DUE_DATE";
    public static final String[] TODO_TABLE_DATA_COLUMNS = {TODO_COLUMN_CONTENT,TODO_COLUMN_EXPIRATION};
    public static final String TODO_DATA_INSERT_COMMAND = "INSERT INTO "+TODO_TABLE_NAME + "(" + TODO_COLUMN_CONTENT +"," + TODO_COLUMN_EXPIRATION + ") "+
            "VALUES (?,?)";
    private static final int DATABASE_VERSION = 1;
    public TodoAppDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
           db.execSQL(TODO_TABLE_CREATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
