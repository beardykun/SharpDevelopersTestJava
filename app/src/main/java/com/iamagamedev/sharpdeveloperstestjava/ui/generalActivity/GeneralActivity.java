package com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iamagamedev.sharpdeveloperstestjava.R;

public class GeneralActivity extends AppCompatActivity {

    private RelativeLayout progress;
    public CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater layoutInflater = getLayoutInflater();

        final View container = layoutInflater.inflate(R.layout.activity_general, (ViewGroup) getWindow().getDecorView(), false);
        layoutInflater.inflate(layoutResID, (ViewGroup) container.findViewById(R.id.cont_root), true);
        super.setContentView(container);

        progress = findViewById(R.id.progressLay);
        progress.setVisibility(View.GONE);

        coordinatorLayout = findViewById(R.id.coordinatorLayout);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void showErrorSnack(final String error) {
        showErrorSnack(error, coordinatorLayout);
    }

    protected void showErrorSnack(final String error, CoordinatorLayout coordinatorLayout) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.app_error_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.white));
        snackbar.setActionTextColor(getResources().getColor(R.color.black));

        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setMaxLines(10);
        snackbar.show();
    }

    public void showProgressView() {
        progress.setVisibility(View.VISIBLE);
    }

    public void hideProgressView() {
        progress.setVisibility(View.GONE);
    }

    protected void startActivity(Class activityClass) {
        startActivity(activityClass, false);
    }

    protected void startActivity(Class activityClass, boolean lockBackAction) {
        Intent intent = new Intent(this, activityClass);
        if (lockBackAction) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        startActivity(intent);
    }
}
