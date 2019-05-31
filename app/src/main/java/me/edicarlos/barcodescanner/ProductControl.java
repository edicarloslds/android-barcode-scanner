package me.edicarlos.barcodescanner;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

import me.edicarlos.barcodescanner.adapters.ProductListAdapter;

public class ProductControl extends AppCompatActivity {

    private RecyclerView mRecyclerViewProducts;
    private ProductListAdapter mAdapter;
    private final LinkedList<String> mProductList = new LinkedList<>();

    private TextView mOSValue;
    private String OSNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_control);

        if( getSupportActionBar() != null ) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Random gen = new Random();
        int randomNum = gen.nextInt() & Integer.MAX_VALUE;

        OSNumber = String.valueOf(randomNum);

        mOSValue = findViewById(R.id.textViewOS);
        mOSValue.setText("OS: " + randomNum);

        // Create recycler view.
        mRecyclerViewProducts = findViewById(R.id.recyclerviewProducts);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new ProductListAdapter(this, mProductList);
        // Connect the adapter with the recycler view.
        mRecyclerViewProducts.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fabSave = findViewById(R.id.fabSave);
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveOS();
            }
        });

        FloatingActionButton fabBar = findViewById(R.id.fabBar);
        fabBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readBarCode();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private void SaveOS(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(new Date());

        String Value = OSNumber +" - João da Silva - Data: " + currentDateandTime;

        Intent returnIntent = new Intent();
        returnIntent.putExtra("OSValue",Value);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    private void readBarCode(){
        IntentIntegrator intent = new IntentIntegrator(ProductControl.this);
        intent.setBeepEnabled(true);
        intent.setTimeout(15000);
        intent.initiateScan();
    }

    private void createProductItem(String barcode){
        int listSize = mProductList.size();
        // Add a new word to the wordList.
        mProductList.addLast("Cod: " + barcode +" - Produto " + listSize);
        // Notify the adapter, that the data has changed.
        mRecyclerViewProducts.getAdapter().notifyItemInserted(listSize);
        // Scroll to the bottom.
        mRecyclerViewProducts.smoothScrollToPosition(listSize);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            String barcode = result.getContents();
            if (barcode != null && !"".equals(barcode)){
                createProductItem(barcode);
            }
            else {
                showInputCode();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void showInputCode(){
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.code_input, null);

        final EditText editText = (EditText) dialogView.findViewById(R.id.edt_comment);
        Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
        Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createProductItem(editText.getText().toString());
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }
}
