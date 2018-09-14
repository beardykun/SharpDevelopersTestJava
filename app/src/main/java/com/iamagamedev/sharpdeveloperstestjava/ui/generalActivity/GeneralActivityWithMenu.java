package com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.iamagamedev.sharpdeveloperstestjava.R;
import com.iamagamedev.sharpdeveloperstestjava.app.Constants;
import com.iamagamedev.sharpdeveloperstestjava.app.SharedPreferencesClass;
import com.iamagamedev.sharpdeveloperstestjava.app.ThisApplication;
import com.iamagamedev.sharpdeveloperstestjava.ui.loginActivity.LoginActivity;
import com.iamagamedev.sharpdeveloperstestjava.ui.transactionsHistoryActivity.TransactionHistoryActivity;

import butterknife.BindView;

public class GeneralActivityWithMenu extends GeneralActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RelativeLayout progress;
    public CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater layoutInflater = getLayoutInflater();

        final View container = layoutInflater.inflate(R.layout.activity_general_with_menu, (ViewGroup) getWindow().getDecorView(), false);
        //noinspection ConstantConditions
        layoutInflater.inflate(layoutResID, (ViewGroup) container.findViewById(R.id.cont_root), true);
        super.setContentView(container);


        progress = findViewById(R.id.progressLay);
        progress.setVisibility(View.GONE);

        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        toolbar = findViewById(R.id.toolbar);
        setToolbar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        setDrawer(toolbar, drawer, navigationView);
    }

    public void setDrawer(Toolbar toolbar, DrawerLayout drawer, NavigationView navigationView) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.app_navigation_drawer_open, R.string.app_navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
            bar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.history:
                startActivity(TransactionHistoryActivity.class, false);
                break;
            case R.id.nav_logout:
                SharedPreferencesClass.deleteFromPrefs();
                PreferenceManager.getDefaultSharedPreferences(this).edit().clear().apply();
                startActivity(LoginActivity.class);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void showProgressView() {
        progress.setVisibility(View.VISIBLE);
    }

    public void hideProgressView() {
        progress.setVisibility(View.GONE);
    }

    public void showErrorSnack(final String error) {
        showErrorSnack(error, coordinatorLayout);
    }

}
