package com.example.ningze_mybookwishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddBookFragment.AddBookDialogListener {

    private ArrayList<Book> bookDataList;
    private ListView bookList;
    private ArrayAdapter<Book> bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookDataList = new ArrayList<>();
        bookList = findViewById(R.id.book_list);
        bookAdapter = new BookArrayAdapter(bookDataList, this);
        bookList.setAdapter(bookAdapter);



        final FloatingActionButton addCityButton = findViewById(R.id.add_book_button);
        addCityButton.setOnClickListener((v) -> {
            new AddBookFragment().show(getSupportFragmentManager(), "Add Book");
        });
    }


    @Override
    public void addBook(Book newbook){
        bookAdapter.add(newbook);
    }
}