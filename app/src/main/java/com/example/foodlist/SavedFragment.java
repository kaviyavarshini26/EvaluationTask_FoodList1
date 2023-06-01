package com.example.foodlist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.foodlist.AppDatabase;
import com.example.foodlist.ItemDao;
import com.example.foodlist.ItemEntity;
import com.example.foodlist.MyApp;
import com.example.foodlist.R;
import com.example.foodlist.SavedAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class SavedFragment extends Fragment  {

    RecyclerView recyclerView;
     SavedAdapter adapter;
//    private List<String> savedItems = new ArrayList<>();
    private ItemDao itemDao;
    private AppDatabase database;

    public SavedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.saved_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SavedAdapter(getActivity());

        recyclerView.setAdapter(adapter);
        loadSavedItems();


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database= Room.databaseBuilder(getActivity(),AppDatabase.class,"app_database")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        itemDao = database.itemDao();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved, container, false);
        return view;
    }


    private void loadSavedItems() {
        itemDao.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<ItemEntity>>() {
            @Override
            public void onChanged(List<ItemEntity> items) {
                adapter.setItems(items);
                adapter.notifyDataSetChanged();
            }
        });


    }
}

