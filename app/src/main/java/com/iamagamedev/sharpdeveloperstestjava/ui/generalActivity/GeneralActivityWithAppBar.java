package com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.iamagamedev.sharpdeveloperstestjava.R;

public class GeneralActivityWithAppBar extends GeneralActivity {
    private RelativeLayout progress;
    public CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater layoutInflater = getLayoutInflater();

        final View container = layoutInflater.inflate(R.layout.activity_general_with_appbar, (ViewGroup) getWindow().getDecorView(), false);
        layoutInflater.inflate(layoutResID, (ViewGroup) container.findViewById(R.id.cont_root), true);
        super.setContentView(container);

        progress = findViewById(R.id.progressLay);
        progress.setVisibility(View.GONE);

        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
        }
    }

    public void showErrorSnack(final String error) {
        showErrorSnack(error, coordinatorLayout);
    }

    public void showProgressView() {
        progress.setVisibility(View.INVISIBLE);
    }

    public void hideProgressView() {
        progress.setVisibility(View.INVISIBLE);
    }
}
