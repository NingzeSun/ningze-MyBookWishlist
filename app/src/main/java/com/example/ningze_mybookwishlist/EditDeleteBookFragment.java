/*The EditDeleteBookFragment class is a DialogFragment designed to provide a user interface for editing or deleting a book entry.
The design rationale behind this class includes:
    Modularity and Reusability: By encapsulating the edit and delete functionalities within a DialogFragment, the logic for these actions can be reused wherever needed in the application.
    User Experience: The dialog provides a focused context for the user to make changes to a book or remove it, without leaving the current workflow or navigating to a different screen.
    Interface Implementation: The EditDeleteBookDialogListener interface is defined within this class to ensure that the calling context (usually an Activity or another Fragment) implements the necessary callback methods (editBook and deleteBook). This enforces a contract that the calling context must handle the actions triggered by the dialog.
*/
/* No Outstanding issues */
package com.example.ningze_mybookwishlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class EditDeleteBookFragment extends DialogFragment {
    private EditText titleName;
    private EditText authorName;
    private EditText genreName;
    private EditText yearName;
    private EditText statusName;

    private TextView detailTitle;
    private TextView detailAuthor;
    private TextView detailGenre;
    private TextView detailYear;
    private TextView detailStatus;


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

    /* Citation begins */
    /* URL:https://eclass.srv.ualberta.ca/mod/assign/view.php?id=7590772
       Author: unknown
       Date: 2024-01-25
     */
    public static EditDeleteBookFragment newInstance(Book book) {
        EditDeleteBookFragment fragment = new EditDeleteBookFragment();
        Bundle args = new Bundle();
        args.putSerializable("book", book);
        fragment.setArguments(args);
        return fragment;
    }
    /* Citation ends */

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Book book = (Book) getArguments().getSerializable("book");
        }
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.edit_delete_book_layout, null);

        detailTitle = view.findViewById(R.id.detailTitle);
        detailAuthor = view.findViewById(R.id.detailAuthor);
        detailGenre = view.findViewById(R.id.detailGenre);
        detailYear = view.findViewById(R.id.detailYear);
        detailStatus = view.findViewById(R.id.detailStatus);
        if (getArguments() != null) {
            Book book = (Book) getArguments().getSerializable("book");
            // Set the text for the TextViews with labels
            detailTitle.setText("Title: " + book.getTitle());
            detailAuthor.setText("Author: " + book.getAuthor());
            detailGenre.setText("Genre: " + book.getGenre());
            detailYear.setText("Publication Year: " + book.getYear());
            detailStatus.setText("Status: " + book.getStatus());
        }

        titleName = view.findViewById(R.id.title_editDeleteText);
        authorName = view.findViewById(R.id.author_editDeleteText);
        genreName = view.findViewById(R.id.genre_editDeleteText);
        yearName = view.findViewById(R.id.year_editDeleteText);
        statusName = view.findViewById(R.id.status_editDeleteText);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit/Delete City")
                .setNegativeButton("Cancel", null)
                /* Citation begins */
                /* URL: https://www.phind.com/search
                   Author: Phind, ChatGPT-4 engine, owned by Phind
                   Prompt: Can I set two positive buttons for the builder?
                   Date: 2024-01-24
                 */
                .setNeutralButton("Delete", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        // Handle the deletion here
                        listener.deleteBook();
                    }
                })
                /* Citation ends */
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
