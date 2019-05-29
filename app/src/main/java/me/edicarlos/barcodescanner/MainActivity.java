package me.edicarlos.barcodescanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;
import java.util.UUID;

import me.edicarlos.barcodescanner.adapters.OSListAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private OSListAdapter mAdapter;
    private final LinkedList<String> mOSList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Put initial data into the word list.
        for (int i = 0; i < 2; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String currentDateandTime = sdf.format(new Date());

            Random gen = new Random();
            int randomNum = gen.nextInt() & Integer.MAX_VALUE;

            mOSList.addLast("OS: " + randomNum +" - João da Silva - Data: " + currentDateandTime);
        }

        // Create recycler view.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new OSListAdapter(this, mOSList);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add a floating action click handler for creating new entries.
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ProductControl.class);
                startActivityForResult(i, 100);
            }
        });
    }

    private void updateOSList(String OSValue){
        int listSize = mOSList.size();
        // Add a new word to the wordList.
        mOSList.addLast("OS: " + OSValue);
        // Notify the adapter, that the data has changed.
        mRecyclerView.getAdapter().notifyItemInserted(listSize);
        // Scroll to the bottom.
        mRecyclerView.smoothScrollToPosition(listSize);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if(resultCode == Activity.RESULT_OK){
                String OSValue = data.getStringExtra("OSValue");
                updateOSList(OSValue);
            }
        }
    }
}
