package com.example.shopping;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemRepository {
    private ItemDao myItemsDao;
    private LiveData<List<Item>> itemsList;
    LiveData<List<Item>> getAllItems() {
        return itemsList;
    }

    public void insert (Item item) {

// Run on a background thread//

        new newAsyncTask(myItemsDao).execute(item);
    }
    private static class newAsyncTask extends AsyncTask<Item, Void, Void> {

        private ItemDao myAsyncDao;

        newAsyncTask(ItemDao dao) {
            myAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(final Item... params) {
            myAsyncDao.insert(params[0]);
            return null;
        }

    }
    ItemRepository(Application application) {
        ItemRoomDatabase database = ItemRoomDatabase.getDatabase(application);
        myItemsDao = database.itemDao();
        itemsList = myItemsDao.getItemList();
    }
}
