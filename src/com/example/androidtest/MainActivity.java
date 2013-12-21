package com.example.androidtest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Base_Activity implements TextWatcher {

    TextView name;
    TextView comment;
    TextView email;

    EditText nameIn;
    EditText commentIn;
    EditText emailIn;
    SQLiteDatabase db;

    private String myName;
    private String myComment;
    private String myEmail;

    DbHelper dbhelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        dbhelper = new DbHelper(getApplicationContext());
        db = dbhelper.getWritableDatabase();

        name = (TextView) findViewById(R.id.nameOut);
        comment = (TextView) findViewById(R.id.commentOut);
        email = (TextView) findViewById(R.id.otherOut);

        nameIn = (EditText) findViewById(R.id.nameIn);
        commentIn = (EditText) findViewById(R.id.commentIn);
        emailIn = (EditText) findViewById(R.id.emailIn);

        nameIn.addTextChangedListener(this);
        commentIn.addTextChangedListener(this);
        emailIn.addTextChangedListener(this);

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterTextChanged(Editable s) {
        try{
            myName = nameIn.getText().toString();
            myComment = commentIn.getText().toString();
            myEmail = emailIn.getText().toString();
        }
        catch (NullPointerException e){
            myName = "Error";
            myComment = "Error";
            myEmail = "Error";
        }

        name.setText(myName);
        comment.setText(myComment);
        email.setText(myEmail);
    }

    public void saveComment(View v){
        try{

            ContentValues cv = new ContentValues();
            cv.put(DbHelper.NAME,myName);
            cv.put(DbHelper.COMMENT,myComment);
            cv.put(DbHelper.EMAIL,myEmail);
            db.insert(DbHelper.TABLE_NAME,null,cv);
        }
        catch (Exception e){
            name.setText(e.getMessage());
        }


    }

    public void getComment(View v){
        try{
        String[] columns = {DbHelper.NAME, DbHelper.COMMENT, DbHelper.EMAIL};
        Cursor cursor = db.query(DbHelper.TABLE_NAME, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            String name1 = cursor.getString(cursor.getColumnIndex(DbHelper.NAME));
            String comment1 = cursor.getString(cursor.getColumnIndex(DbHelper.COMMENT));
            String email1 = cursor.getString(cursor.getColumnIndex(DbHelper.EMAIL));

            Toast.makeText(this,"Name = "+name1 + "\nComment = "+ comment1 +
                            "\nEmail = " + email1, Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        }
        catch(Exception e){
             name.setText(e.getMessage());
            }

    }


    public void onStart(){
    	super.onStart();
    }
    
    public void onResume(){
    	super.onResume();
    }
    
    public void onPause(){
    	super.onPause();
    }
    
    public void onStop(){
    	super.onStop();
    }
    
    public void onDestroy(){
    	super.onDestroy();
    }


    public void  clearComment(View v){

        nameIn.setText("");
        commentIn.setText("");
        emailIn.setText("");

        name.setText("");
        comment.setText("");
        email.setText("");
    }
}
