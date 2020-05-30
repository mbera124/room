package com.example.shopping;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {

//Annotate the insert() method//

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(Item item);
    @Query("SELECT * from item_table ")
    LiveData<List<Item>> getItemList();

}