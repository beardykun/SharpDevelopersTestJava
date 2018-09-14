package com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iamagamedev.sharpdeveloperstestjava.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GeneralActivityWithoutAppbar extends GeneralActivity {

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

        final View container = layoutInflater.inflate(R.layout.activity_general_without_appbar, (ViewGroup) getWindow().getDecorView(), false);
        //noinspection ConstantConditions
        layoutInflater.inflate(layoutResID, (ViewGroup) container.findViewById(R.id.cont_root), true);
        super.setContentView(container);

        progress = findViewById(R.id.progressLay);
        progress.setVisibility(View.GONE);

        coordinatorLayout = findViewById(R.id.coordinatorLayout);

    }

    public void showProgressView() {
        progress.setVisibility(View.VISIBLE);
    }

    public void hideProgressView() {
        progress.setVisibility(View.GONE);
    }

    protected void showErrorSnack(final String error, CoordinatorLayout coordinatorLayout) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.app_error_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
        // Change background color
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.white));
        // Changing action button text color
        snackbar.setActionTextColor(getResources().getColor(R.color.black));

        // Changing message text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setMaxLines(10);
        snackbar.show();
    }
    public void showErrorSnack(final String error) {
        showErrorSnack(error, coordinatorLayout);
    }
}
