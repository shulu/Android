package com.example.navdemo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.app.Fragment;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    NavHostFragment navHostFragment;
    NavController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        controller = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, controller);
    }

    @Override
    public boolean onNavigateUp() {
        //return super.onNavigateUp();
        return controller.navigateUp();
    }
}