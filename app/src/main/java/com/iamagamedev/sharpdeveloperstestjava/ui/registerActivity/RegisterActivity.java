package com.iamagamedev.sharpdeveloperstestjava.ui.registerActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;

import com.iamagamedev.sharpdeveloperstestjava.R;
import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.GeneralActivityWithAppBar;
import com.iamagamedev.sharpdeveloperstestjava.ui.loginActivity.LoginActivity;
import com.iamagamedev.sharpdeveloperstestjava.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends GeneralActivityWithAppBar implements IRegisterView {

    @BindView(R.id.usernameRegisterLayout)
    TextInputLayout usernameRegisterLayout;
    @BindView(R.id.passwordRegisterLayout)
    TextInputLayout passwordRegisterLayout;
    @BindView(R.id.password2RegisterLayout)
    TextInputLayout password2RegisterLayout;
    @BindView(R.id.emailRegisterLayout)
    TextInputLayout emailRegisterLayout;
    @BindView(R.id.usernameRegisterText)
    TextInputEditText usernameRegisterText;
    @BindView(R.id.passwordRegisterText)
    TextInputEditText passwordRegisterText;
    @BindView(R.id.password2RegisterText)
    TextInputEditText password2RegisterText;
    @BindView(R.id.emailRegisterText)
    TextInputEditText emailRegisterText;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        presenter = new RegisterPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onAttachView(this);
    }

    @Override
    protected void onStop() {
        presenter.onDetachView();
        super.onStop();
    }

    @Override
    public void showUsernameValidationError() {
        Utils.showValidationError(usernameRegisterLayout, getString(R.string.username_error));
    }

    @Override
    public void showPasswordValidationError() {
        Utils.showValidationError(passwordRegisterLayout, getString(R.string.password_error));
    }

    @Override
    public void showPassword2ValidationError() {
        Utils.showValidationError(password2RegisterLayout, getString(R.string.different_password));
    }

    @Override
    public void showEmailValidationError() {
        Utils.showValidationError(emailRegisterLayout, getString(R.string.email_error));
    }

    @Override
    public void onRegistrationCompleted() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.registration_completed);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(LoginActivity.class, true);
                dialogInterface.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
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

    @OnClick(R.id.okRegisterBtn)
    void registerClick() {
        presenter.register(usernameRegisterText.getText().toString(), passwordRegisterText.getText().toString(),
                password2RegisterText.getText().toString(), emailRegisterText.getText().toString());
    }
}
