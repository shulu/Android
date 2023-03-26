package com.example.words;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class WordsFragment extends Fragment {

    private WordViewModel wordViewModel;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter1, myAdapter2;
    private LiveData<List<Word>> filterWords;

    private static final String SHP_VIEW_TYPE  = "shp_view_type";
    private static final String WHICH_VIEW_TYPE = "which_view_type";

    public WordsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        myAdapter1 = new MyAdapter(false, wordViewModel);
        myAdapter2 = new MyAdapter(true, wordViewModel);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_words, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = requireActivity().findViewById(R.id.fl_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu);
                SearchView searchView = (SearchView) menu.findItem(R.id.word_search).getActionView();
                searchView.setMaxWidth(500);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public boolean onQueryTextChange(String s) {
                        String search = s.trim();
                        filterWords.removeObservers(requireActivity());
                        filterWords = wordViewModel.filterWords(search);
                        filterWords.observe(requireActivity(), words -> {
                            int temp = myAdapter1.getItemCount();
                            myAdapter1.setAllWords(words);
                            myAdapter2.setAllWords(words);
                            if (temp != words.size()){
                                myAdapter1.notifyDataSetChanged();
                                myAdapter2.notifyDataSetChanged();
                            }
                        });
                        return false;
                    }
                });
            }

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case  R.id.word_clear:
                        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                        builder.setTitle(R.string.menu_clear);
                        builder.setNegativeButton("确定", (dialogInterface, i) -> wordViewModel.deleteAllWord());
                        builder.setNegativeButton("取消", (dialogInterface, i) -> {});
                        builder.create();
                        builder.show();
                        break;
                    case R.id.word_switch:
                        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(SHP_VIEW_TYPE, Context.MODE_PRIVATE);
                        boolean viewType = sharedPreferences.getBoolean(WHICH_VIEW_TYPE, false);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if (viewType){
                            recyclerView.setAdapter(myAdapter2);
                            //recyclerView.swapAdapter(myAdapter2,true);
                            editor.putBoolean(WHICH_VIEW_TYPE, false);
                        }else{
                            recyclerView.setAdapter(myAdapter1);
                            //recyclerView.swapAdapter(myAdapter1,true);
                            editor.putBoolean(WHICH_VIEW_TYPE, true);
                        }
                        editor.apply();
                        break;
                }
                return false;
            }
        });
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(SHP_VIEW_TYPE, Context.MODE_PRIVATE);
        boolean viewType = sharedPreferences.getBoolean(WHICH_VIEW_TYPE, false);
        if (viewType){
            recyclerView.setAdapter(myAdapter2);
        }else{
            recyclerView.setAdapter(myAdapter1);
        }
        filterWords = wordViewModel.getAllWordLive();
        filterWords.observe(requireActivity(), words -> {
            int temp = myAdapter1.getItemCount();
            myAdapter1.setAllWords(words);
            myAdapter2.setAllWords(words);
            if (temp != words.size()){
                myAdapter1.notifyDataSetChanged();
                myAdapter2.notifyDataSetChanged();
            }
        });
        FloatingActionButton floatingActionButton = requireActivity().findViewById(R.id.fab_add);
        floatingActionButton.setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view1);
            navController.navigate(R.id.action_wordsFragment_to_addFragment);
        });

    }
}