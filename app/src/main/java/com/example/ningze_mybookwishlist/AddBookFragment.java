/* AddBookFragment is a DialogFragment subclass designed to present a dialog interface for adding a new book to a user's book wishlist within an Android application.
The design rationale behind AddBookFragment includes:
    Modularity: By encapsulating the add book dialog in a fragment, the code related to adding a book is kept separate from the rest of the application's code, promoting cleaner and more organized codebase.
    Lifecycle Management: As a DialogFragment, it benefits from the fragment lifecycle, ensuring that the dialog behaves correctly through configuration changes and other lifecycle events.
    Communication with Host: It defines an AddBookDialogListener interface that the host must implement to receive the book details once the user completes the form, establishing a clear contract for communication.
    UI Flexibility: It inflates a custom layout for the dialog, allowing for a tailored user interface that can be adjusted or expanded more easily than a standard dialog.
    Error Handling: It enforces the implementation of the AddBookDialogListener in the host with a runtime check during the onAttach method, preventing class cast exceptions at runtime. */
/* No outstanding issues */
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

    /* Citation begins
       URL: https://eclass.srv.ualberta.ca/pluginfile.php/10617023/mod_resource/content/5/Lab%203%20Instructions%20%5BWinter%202023%5D.pdf
       Author: Junwen Shen
       Date: unknown */
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
    /* Citation ends */


}
