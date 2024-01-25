package com.example.ningze_mybookwishlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class EditDeleteBookFragment extends DialogFragment {
    private EditText titleName;
    private EditText authorName;
    private EditText genreName;
    private EditText yearName;
    private EditText statusName;


    public interface EditDeleteBookDialogListener {
        void editBook(Book book);
        void deleteBook();
    }
    private EditDeleteBookDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EditDeleteBookFragment.EditDeleteBookDialogListener){
            listener = (EditDeleteBookFragment.EditDeleteBookDialogListener) context;
        }
        else{
            throw new RuntimeException(context.toString() + "must implement EditDeleteBookDialogListener Listener");
        }
    }

    public static EditDeleteBookFragment newInstance(Book book) {
        EditDeleteBookFragment fragment = new EditDeleteBookFragment();
        Bundle args = new Bundle();
        args.putSerializable("book", book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Book city = (Book) getArguments().getSerializable("book");
            // Use city to populate your edit fields
        }
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.edit_delete_book_layout, null);
        titleName = view.findViewById(R.id.title_editDeleteText);
        authorName = view.findViewById(R.id.author_editDeleteText);
        genreName = view.findViewById(R.id.genre_editDeleteText);
        yearName = view.findViewById(R.id.year_editDeleteText);
        statusName = view.findViewById(R.id.status_editDeleteText);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit City")
                .setNegativeButton("Cancel", null)
                .setNeutralButton("Delete", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        // Handle the deletion here
                        listener.deleteBook();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        String title = titleName.getText().toString();
                        String author = authorName.getText().toString();
                        String genre = genreName.getText().toString();
                        String year = yearName.getText().toString();
                        String status = statusName.getText().toString();
                        listener.editBook(new Book(title, author, genre, year, status));
                    }
                }).create();
    }

}
