package com.example.cbms_naga.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cbms_naga.Model.List_of_Question;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private String[] columns_choices = { "id" ,"questions_id","choices_label" };
    private String cbms_choices = "cbms_choices";

    private String[] columns = { "id" ,"questions_id","questions_label" ,"questions_type"};
    private String cbms_questions_A = "cbms_questions_A";
    private String cbms_questions_B = "cbms_questions_B";
    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all voters from the database.
     *
     * @return a List of questions
     */

    public ArrayList<List_of_Question> get_All_Questions(String cbms_questions) {
        ArrayList<List_of_Question> dataList = new ArrayList<>();
        Cursor cursor = database.query(cbms_questions, null , null,null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            List_of_Question list_of_question = new List_of_Question();
            list_of_question.setId(cursor.getInt(cursor.getColumnIndex("id")));
            list_of_question.setQuestions_id(cursor.getString(cursor.getColumnIndex("questions_id")));
            list_of_question.setQuestions_label(cursor.getString(cursor.getColumnIndex("questions_label")));
            list_of_question.setQuestions_type(cursor.getString(cursor.getColumnIndex("questions_type")));
            dataList.add(list_of_question);
            cursor.moveToNext();
        }

        cursor.close();
        // return all notes
        return dataList;
    }

    public List<String> get_All_Choices(String questions_id) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.query(cbms_choices, null , "questions_id=?", new String[] {questions_id}, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(cursor.getColumnIndex("choices_label")));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

}
