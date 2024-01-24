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
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddBookFragment extends DialogFragment {
    public interface AddBookDialogListener{
        void addBook(Book book);
    }

    private AddBookDialogListener listener;
    private EditText titleName;
    private EditText authorName;
    private EditText genreName;
    private EditText yearName;
    private EditText statusName;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if (context instanceof AddBookDialogListener){
            listener = (AddBookDialogListener) context;
        }
        else{
            throw new RuntimeException(context.toString() + "must implement AddBookDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_book, null);
        titleName = view.findViewById(R.id.title_addText);
        authorName = view.findViewById(R.id.author_addText);
        genreName = view.findViewById(R.id.genre_addText);
        yearName = view.findViewById(R.id.year_addText);
        statusName = view.findViewById(R.id.status_addText);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add a new Book")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        String title = titleName.getText().toString();
                        String author = authorName.getText().toString();
                        String genre = genreName.getText().toString();
                        String year = yearName.getText().toString();
                        String status = statusName.getText().toString();
                        listener.addBook(new Book(title, author, genre, year, status));
                    }
                }).create();
    }


}
