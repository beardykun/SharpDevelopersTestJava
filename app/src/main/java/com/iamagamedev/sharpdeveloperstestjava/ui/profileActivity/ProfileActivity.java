package com.iamagamedev.sharpdeveloperstestjava.ui.profileActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.iamagamedev.sharpdeveloperstestjava.R;
import com.iamagamedev.sharpdeveloperstestjava.utils.Utils;
import com.iamagamedev.sharpdeveloperstestjava.app.Constants;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserListObject;
import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.GeneralActivityWithMenu;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends GeneralActivityWithMenu implements IProfileView,
        TextInputEditText.OnEditorActionListener {

    private IProfilePresenter presenter;
    @BindView(R.id.profileUsernameText)
    TextView profileUsernameText;
    @BindView(R.id.profileEmailText)
    TextView profileEmailText;
    @BindView(R.id.profileBalanceText)
    TextView profileBalanceText;
    @BindView(R.id.profileRecipientNameLayout)
    TextInputLayout profileRecipientNameLayout;
    @BindView(R.id.profileRecipientNameText)
    TextInputEditText profileRecipientNameText;
    @BindView(R.id.profileSendAmountLayout)
    TextInputLayout profileSendAmountLayout;
    @BindView(R.id.profileSendAmountEdit)
    TextInputEditText profileSendAmountEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        presenter = new ProfilePresenter();
        profileRecipientNameText.setOnEditorActionListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onAttachView(this);
        presenter.getUserInfo();
        if (getIntent().getExtras() != null) {
            presenter.setTransactionData(getIntent().getExtras());
        }
    }

    @Override
    protected void onStop() {
        presenter.onDetachView();
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
    public void populateViews(String id, String name, String email, String balance) {
        profileUsernameText.setText(name);
        profileEmailText.setText(email);
        String strBalance = balance + " PW";
        profileBalanceText.setText(strBalance);
    }

    @OnClick(R.id.createTransactionBtn)
    public void createTransaction() {
        String balance = profileBalanceText.getText().toString().replace(" PW", "");
        presenter.createTransaction(profileRecipientNameText.getText().toString(), profileSendAmountEdit.getText().toString(),
                balance);
    }

    @Override
    public void callUserListDialog(ArrayList<UserListObject> userListObjects) {
        final FragmentManager fm = getSupportFragmentManager();
        UserListFragment fragment = new UserListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(Constants.SAVE_LIST, userListObjects);
        fragment.setArguments(args);

        fragment.show(fm, "tag");
    }

    @Override
    public void setTransactionData(String name, String amount) {
        profileRecipientNameText.setText(name);
        profileSendAmountEdit.setText(amount);
    }

    @Override
    public void onRecipientValidationFailed() {
        Utils.showValidationError(profileRecipientNameLayout, getString(R.string.recipient_error));
    }

    @Override
    public void onAmountValidationFailed() {
        Utils.showValidationError(profileSendAmountLayout, getString(R.string.enter_amount_error));
    }

    @Override
    public void amountMoreThanBalance() {
        Utils.showValidationError(profileSendAmountLayout, getString(R.string.balance_limit_error));
    }

    @Override
    public void transactionCompleted() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.transaction_completed);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_DONE) {
            presenter.getUsersList(profileRecipientNameText.getText().toString());
            return true;
        }
        return false;
    }

}
