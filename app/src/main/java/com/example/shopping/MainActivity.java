package com.example.shopping;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    private ItemViewModel myItemViewModel;
    Toolbar myToolbar;
    private String TAG="items";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = findViewById(R.id.toolbar);


        RecyclerView myRecyclerView = findViewById(R.id.recyclerview);
        final ItemListAdapter myAdapter = new ItemListAdapter(this);
        myRecyclerView.setAdapter(myAdapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Retrieve the ViewModel//
        Log.d(TAG, "kaj"+myItemViewModel);
        //myItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        myItemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
//        Log.d("item", "joe");
        Log.d(TAG, "jj"+myItemViewModel);


//        new ViewModelProvider(this).get(ItemViewModel.class);

//Observe the LiveData and deliver a notification every time the data changes//

        myItemViewModel.getAllItems().observe(this, new Observer<List<Item>>() {

            @Override
            public void onChanged(@Nullable final List<Item> items) {

//Update the UI//

                myAdapter.setItems(items);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Intent intent = new Intent(MainActivity.this, NewItemActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
        }
        return false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Item item = new Item(data.getStringExtra(NewItemActivity.EXTRA));
            myItemViewModel.insert(item);

        }

    }
}
