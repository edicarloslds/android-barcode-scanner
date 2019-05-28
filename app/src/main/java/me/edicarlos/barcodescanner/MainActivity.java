package me.edicarlos.barcodescanner;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SolicitationListAdapter mAdapter;
    private final LinkedList<String> mSolicitationList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Put initial data into the word list.
        for (int i = 0; i < 20; i++) {
            mSolicitationList.addLast("Solicitation " + i);
        }

        // Create recycler view.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new SolicitationListAdapter(this, mSolicitationList);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add a floating action click handler for creating new entries.
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              int wordListSize = mSolicitationList.size();
//              // Add a new word to the wordList.
//              mSolicitationList.addLast("+ Solicitation " + wordListSize);
//              // Notify the adapter, that the data has changed.
//              mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
//              // Scroll to the bottom.
//              mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });
    }
}
