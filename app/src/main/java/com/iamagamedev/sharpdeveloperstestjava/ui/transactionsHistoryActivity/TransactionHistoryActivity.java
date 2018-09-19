package com.iamagamedev.sharpdeveloperstestjava.ui.transactionsHistoryActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.iamagamedev.sharpdeveloperstestjava.R;
import com.iamagamedev.sharpdeveloperstestjava.app.Constants;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionObject;
import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.GeneralActivity;
import com.iamagamedev.sharpdeveloperstestjava.ui.transactionsHistoryActivity.settings.SettingsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionHistoryActivity extends GeneralActivity
        implements ITransactionHistoryView, TransactionsListAdapter.OnTransactionsListListener,
        SearchView.OnQueryTextListener {

    private boolean preferencesChanged = true;
    private String filter;
    private ITransactionHistoryPresenter presenter;
    private TransactionsListAdapter adapter;
    @BindView(R.id.transactionsRecyclerView)
    RecyclerView transactionsRecyclerView;
    @BindView(R.id.search)
    SearchView searchView;
    @BindView(R.id.toolbar_history)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        ButterKnife.bind(this);
        presenter = new TransactionHistoryPresenter();
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        setSearchViewSettings();
    }

    private void setSearchViewSettings() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }
        searchView.setActivated(true);
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (preferencesChanged) {
            filter = PreferenceManager.getDefaultSharedPreferences(this).getString(Constants.SORTING, Constants.SORT_NOT);
            preferencesChanged = false;
        }
        presenter.onAttachView(this);
        presenter.getTransactionList();
    }

    @Override
    protected void onStop() {
        presenter.onDetachView();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        super.onStop();
    }

    @Override
    public void showProgress() {
        showProgressView();
    }

    @Override
    public void hideProgress() {
        hideProgressView();
    }

    @Override
    public void showError(String error, int... code) {
        showErrorSnack(error);
    }

    @Override
    public void showError(int error, int... code) {
        showErrorSnack(String.valueOf(error));
    }

    @Override
    public void populateAdapter(List<TransactionObject> transactionObjects) {
        adapter = new TransactionsListAdapter(transactionObjects);
        adapter.setListener(this);
        transactionsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void sortList(List<TransactionObject> transactionObjects) {
        presenter.sortList(transactionObjects, filter);
    }

    @Override
    public void onTransactionsListItemClick(final TransactionObject transactionObject) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.select_basis);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.saveObject(transactionObject);
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void backToProfile(Intent intent) {
        startActivity(intent);
    }

    private SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    preferencesChanged = true;
                    if (key.equals(Constants.SORTING)) {
                        filter = sharedPreferences.getString(Constants.SORTING, null);
                    }
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(SettingsActivity.class, false);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.getFilter().filter(s);
        return true;
    }

    @Override
    public void finishActivity() {
        Toast.makeText(this, R.string.empty_history, Toast.LENGTH_LONG).show();
        finish();
    }
}
