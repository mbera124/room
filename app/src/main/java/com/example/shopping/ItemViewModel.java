package com.example.shopping;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {
private ItemRepository myRepository;
private LiveData<List<Item>> myAllItems;

    public ItemViewModel (Application application) {
        super(application);
        myRepository = new ItemRepository(application);
        myAllItems = myRepository.getAllItems();
    }


    LiveData<List<Item>> getAllItems() { return myAllItems; }
    public void insert(Item item) { myRepository.insert(item); }


}
