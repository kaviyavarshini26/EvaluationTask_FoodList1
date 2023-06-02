package com.example.foodlist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
    public interface ItemDao {

        @Insert
        void insertItem(ItemEntity item);
//    @Query("DELETE  FROM items WHERE  'id'= :id")
//    ItemEntity delete(int id);
    @Delete
    void deleteItem(ItemEntity item);


    @Query("SELECT * FROM items")
        LiveData<List<ItemEntity>> getAllItems();



    }
