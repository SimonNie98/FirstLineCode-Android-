package com.example.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connector.getDatabase();
                Toast.makeText(MainActivity.this, "Create Database Succeed.", Toast.LENGTH_SHORT).show();
            }
        });

        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Fudan Press");
                book.save();
                Toast.makeText(MainActivity.this, "Add Succeed.", Toast.LENGTH_SHORT).show();
            }
        });

        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?", "The Lost Symbol", "Dan Brown");
                Toast.makeText(MainActivity.this, "Update Succeed.", Toast.LENGTH_SHORT).show();
            }
        });


        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // here DataSupport has been changed into LitePal
                LitePal.deleteAll(Book.class, "price < ?", "15");
                Toast.makeText(MainActivity.this, "Delete Succeed.", Toast.LENGTH_SHORT).show();
            }
        });

        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book : books) {
                    Log.d(TAG, "name is: " + book.getName());
                    Log.d(TAG, "author is: " + book.getAuthor());
                    Log.d(TAG, "pages is: " + book.getPages());
                    Log.d(TAG, "price is: " + book.getPrice());
                    Log.d(TAG, "press is: " + book.getPress());
                }
            }
        });


    }
}
