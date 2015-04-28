package huji.ac.il.todoapplistex4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Omer on 27/04/2015.
 */
public class TodoDataSource {
    private TodoAppDBHelper m_hlprTodoDB;
    private SQLiteDatabase m_dbTodoDataAccess;
    public TodoDataSource(Context context)
    {
        m_hlprTodoDB = new TodoAppDBHelper(context);
    }

    public void open() throws SQLException {
        m_dbTodoDataAccess = m_hlprTodoDB.getWritableDatabase();
    }

    public void close() {
        m_hlprTodoDB.close();
    }

    public ArrayList<TodoItem> GetTodoItemsFromDB() {
        ArrayList<TodoItem> arrReturnItems = new ArrayList<>();
        Cursor crsrDataRetriever = m_dbTodoDataAccess.query(false,TodoAppDBHelper.TODO_TABLE_NAME,TodoAppDBHelper.TODO_TABLE_DATA_COLUMNS,null,null,null,null,null,null);
        crsrDataRetriever.moveToFirst();
        while (!crsrDataRetriever.isAfterLast()) {
            TodoItem itmCurrItem = TranslateRecordToTodoItem(crsrDataRetriever);
            arrReturnItems.add(itmCurrItem);
            crsrDataRetriever.moveToNext();
        }
        // make sure to close the cursor
        crsrDataRetriever.close();
        return arrReturnItems;
    }

    private TodoItem TranslateRecordToTodoItem(Cursor crsrDataRetriever) {
       TodoItem itmCurrTodoItem = new TodoItem(crsrDataRetriever.getString(0),new Date(crsrDataRetriever.getLong(1)));
       return itmCurrTodoItem;
    }

    public void InsertTodoItem(TodoItem itmToAdd) {
        ContentValues values = new ContentValues();
        values.put(TodoAppDBHelper.TODO_COLUMN_CONTENT, itmToAdd.m_strTodoContent);
        values.put(TodoAppDBHelper.TODO_COLUMN_EXPIRATION,itmToAdd.m_dtExpirationDate.getTime());
        long insertId = m_dbTodoDataAccess.insert(TodoAppDBHelper.TODO_TABLE_NAME, null,
                values);
    }
}
