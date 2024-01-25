package com.example.ningze_mybookwishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddBookFragment.AddBookDialogListener, EditDeleteBookFragment.EditDeleteBookDialogListener {

    private ArrayList<Book> bookDataList;
    private ListView bookList;
    private ArrayAdapter<Book> bookAdapter;
    int selectedBookIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookDataList = new ArrayList<>();
        bookList = findViewById(R.id.book_list);
        bookAdapter = new BookArrayAdapter(bookDataList, this);
        bookList.setAdapter(bookAdapter);

        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedBookIndex = i;
                EditDeleteBookFragment editDeleteBookFragment = EditDeleteBookFragment.newInstance(bookDataList.get(selectedBookIndex));
                editDeleteBookFragment.show(getSupportFragmentManager(), "Edit Delete Book");
            }
        });



        final FloatingActionButton addCityButton = findViewById(R.id.add_book_button);
        addCityButton.setOnClickListener((v) -> {
            new AddBookFragment().show(getSupportFragmentManager(), "Add Book");
        });
    }


    @Override
    public void addBook(Book newbook){
        bookAdapter.add(newbook);
    }

    @Override
    public void editBook(Book newBook){
        Book bookToEdit = bookDataList.get(selectedBookIndex);
        if (!TextUtils.isEmpty(newBook.getTitle())) {
            // If cityName is not empty, set the city name
            bookToEdit.setTitleName(newBook.getTitle());
        }
        if (!TextUtils.isEmpty(newBook.getAuthor())) {
            // If cityName is not empty, set the city name
            bookToEdit.setAuthorName(newBook.getAuthor());
        }
        if (!TextUtils.isEmpty(newBook.getGenre())) {
            // If cityName is not empty, set the city name
            bookToEdit.setGenreName(newBook.getGenre());
        }
        if (!TextUtils.isEmpty(newBook.getYear())) {
            // If cityName is not empty, set the city name
            bookToEdit.setYearName(newBook.getYear());
        }
        if (!TextUtils.isEmpty(newBook.getStatus())) {
            // If cityName is not empty, set the city name
            bookToEdit.setStatusName(newBook.getStatus());
        }
    }
    @Override
    public void deleteBook(){
        Book bookToDelete = bookDataList.get(selectedBookIndex);
        bookDataList.remove(bookToDelete);
        bookAdapter.notifyDataSetChanged(); // Notify the adapter
    }

}