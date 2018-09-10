package com.example.iba.ibasecond.library;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class BooksActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mBooksList;

    //Progress Dialog
    private ProgressDialog mProgressDialog;

    private String book_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        book_category = getIntent().getStringExtra(HelperClass.LIBRARY_CATEGORY);

        mToolbar = findViewById(R.id.books_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(book_category);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("تحميل الكتب");
        mProgressDialog.setMessage("من فضلك إنتظر حتى يتم تحميل الكتب.");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        mBooksList = findViewById(R.id.books_list);
        mBooksList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        startListening();

    }

    private void startListening(){
        Query query = FirebaseDatabase.getInstance().getReference().child(HelperClass.LIBRARY).child(book_category);

        FirebaseRecyclerOptions<Book> options =
                new FirebaseRecyclerOptions.Builder<Book>()
                        .setQuery(query, Book.class)
                        .build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Book, BookViewHolder>(options) {
            @Override
            public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.book_single_layout, parent, false);

                return new BookViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(BookViewHolder bookViewHolder, int position, Book book) {
                // Bind the Chat object to the ChatHolder
                // Log.v(CoursesActivity.class.getSimpleName(), "Course Image : "+course.getImage());

                bookViewHolder.setBookImage(book.getImage());

                bookViewHolder.setBookName(book.getName());

                bookViewHolder.setBookDescription(book.getDescription());

                // ...
            }

            @Override
            public void onDataChanged() {
                super.onDataChanged();
                mProgressDialog.dismiss();
            }
        };
        mBooksList.setAdapter(adapter);
        adapter.startListening();

    }
}
