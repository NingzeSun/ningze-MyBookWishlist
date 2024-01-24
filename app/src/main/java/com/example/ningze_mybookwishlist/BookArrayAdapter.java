/* BookArrayAdapter is a custom ArrayAdapter<Book> designed for displaying a list of Book objects in a ListView within an Android application. */
/* The design rationale behind BookArrayAdapter includes:
    Customization: It overrides the getView method to create a custom view for each item in the list, populating it with the book's details such as title, author, genre, and status.
    Performance: It uses the view recycling pattern by checking if the convertView is null before inflating a new view, which enhances the performance of the ListView when scrolling.
    Simplicity: The adapter takes an ArrayList<Book> and a Context as arguments, simplifying the process of connecting data to the ListView.
    Contextual Awareness: It holds a reference to the Context to access resources and the application environment, necessary for inflating views. */

package com.example.ningze_mybookwishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ningze_mybookwishlist.Book;

import java.util.ArrayList;

public class BookArrayAdapter extends  ArrayAdapter<Book>{
    private ArrayList<Book> books;
    private Context context;
    int selectedbookindex;
    public BookArrayAdapter(ArrayList<Book> books, Context context){
        super(context, 0, books);
        this.books = books;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.content, parent, false);
        }

        Book book = books.get(position);
        TextView titleName = view.findViewById(R.id.title_text);
        TextView authorName = view.findViewById(R.id.author_text);
        TextView genreName = view.findViewById(R.id.genre_text);
        //TextView yearName = view.findViewById(R.id.year_text);
        TextView statusName = view.findViewById(R.id.status_text);

        titleName.setText(book.getTitle());
        authorName.setText(book.getAuthor());
        genreName.setText(book.getGenre());
        //yearName.setText(book.getYear());
        statusName.setText(book.getStatus());

        return view;
    }

}
