package com.iamagamedev.sharpdeveloperstestjava.ui.loginActivity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.WindowManager;

import com.iamagamedev.sharpdeveloperstestjava.R;
import com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity.GeneralActivityWithoutAppbar;
import com.iamagamedev.sharpdeveloperstestjava.ui.profileActivity.ProfileActivity;
import com.iamagamedev.sharpdeveloperstestjava.ui.registerActivity.RegisterActivity;
import com.iamagamedev.sharpdeveloperstestjava.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends GeneralActivityWithoutAppbar implements ILoginView {

    private ILoginPresenter presenter;
    @BindView(R.id.emailLoginLayout)
    TextInputLayout emailLoginLayout;
    @BindView(R.id.passwordLoginLayout)
    TextInputLayout passwordLoginLayout;
    @BindView(R.id.emailLoginText)
    TextInputEditText emailLoginText;
    @BindView(R.id.passwordLoginText)
    TextInputEditText passwordLoginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        presenter = new LoginPresenter();
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
    public void showPasswordValidationError() {
        Utils.showValidationError(passwordLoginLayout, getString(R.string.password_error));
    }

    @Override
    public void showEmailValidationError() {
        Utils.showValidationError(emailLoginLayout, getString(R.string.email_error));
    }

    @Override
    public void goToProfile() {
        startActivity(ProfileActivity.class, true);
    }

    @OnClick(R.id.registerLoginBtn)
    void goToRegister() {
        startActivity(RegisterActivity.class, false);
    }

    @OnClick(R.id.okLoginBtn)
    void loginClick() {
        presenter.loginUser(emailLoginText.getText().toString(), passwordLoginText.getText().toString());
    }
}
