package com.example.foodlist;

import android.annotation.SuppressLint;
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

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavedFragment extends Fragment implements Bottom1 {

    RecyclerView recyclerView;
    SavedAdapter adapter;
    //    private List<String> savedItems = new ArrayList<>();
    private ItemDao itemDao;
    private AppDatabase database;
    List<Category> savedList = new ArrayList<>();

    public SavedFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = Room.databaseBuilder(getActivity(), AppDatabase.class, "app_database")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        itemDao = database.itemDao();
        handleRetrofit();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.saved_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adapter = new RemoteAdapter((Context) getActivity(), remoteList, (BottomsheetClickListnr) this);
        adapter = new SavedAdapter(getActivity(), savedList,this);
//        adapter.setOnDeleteClickListener((SavedAdapter.ViewHolder.OnDeleteClickListener) this);

        recyclerView.setAdapter(adapter);
//        loadSavedItems();


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved, container, false);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
       loadSavedItems();
    }



    private void handleRetrofit() {
        Call<CategoryResponse1> call = RetrofitClient.getInstance().getApi().fetchAllRemote1();
        call.enqueue(new Callback<CategoryResponse1>() {
            @Override
            public void onResponse(Call<CategoryResponse1> call, Response<CategoryResponse1> response) {
                if (response.isSuccessful()) {
                    savedList = response.body().getSavedList();
                    adapter.setData(savedList);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText((Context) getActivity(), (CharSequence) response.body(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse1> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
//    public void onDeleteClick(int position) {
//        Category category = savedList.get(position);
//        ItemEntity item = new ItemEntity(category.getStrCategory(), category.getStrCategoryThumb(), category.getStrCategoryDescription());
//        itemDao.deleteItem(item);
//        Toast.makeText(getActivity(), "Item removed successfully", Toast.LENGTH_SHORT).show();
//    }
//}



    @Override
    public void onItemClicked1(Category category) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        View bottomSheetView = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet1, null);
        bottomSheetDialog.setContentView(bottomSheetView);

        TextView descriptionTextView = bottomSheetView.findViewById(R.id.desc1);
        descriptionTextView.setText(category.getStrCategoryDescription());

        Button remButton = bottomSheetView.findViewById(R.id.remove);

        remButton.setOnClickListener(v -> {
//            adapter.onDeleteClick(category);; // pass remoteList.get(position)

            Toast.makeText(getActivity(), "Items removed successfully", Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();


        });
        bottomSheetDialog.show();
    }


//    private void insertItemFromDatabase(Category category) {
//        ItemEntity item = new ItemEntity(category.getStrCategory(), category.getStrCategoryThumb(), category.getStrCategoryDescription());
//        itemDao.insertItem(item);
//        itemDao.deleteItem(item);
//
//    }
//}


}

