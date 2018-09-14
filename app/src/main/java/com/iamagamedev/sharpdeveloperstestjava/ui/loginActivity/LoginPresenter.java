package com.iamagamedev.sharpdeveloperstestjava.ui.loginActivity;

import com.iamagamedev.sharpdeveloperstestjava.R;
import com.iamagamedev.sharpdeveloperstestjava.app.ThisApplication;

public class LoginPresenter implements ILoginPresenter, ILoginInteractor.OnLoginListener{

    private ILoginView view;
    private ILoginInteractor interactor;

     LoginPresenter(){
        interactor = new LoginInteractor();
    }

    @Override
    public void onAttachView(ILoginView view) {
        this.view = view;
    }

    @Override
    public void loginUser(String email, String password) {
        if (view != null){
            view.showProgress();
            interactor.loginUser(email, password, this);
        }
    }

    @Override
    public void onDetachView() {
        this.view = null;
    }

    @Override
    public void onSuccess(String token) {
        if (view != null){
            view.hideProgress();
            interactor.saveToken(token);
            view.goToProfile();
        }
    }

    @Override
    public void onEmailValidationFailed() {
        if (view != null){
            view.hideProgress();
            view.showEmailValidationError();
        }
    }

    @Override
    public void onPasswordValidationFailed() {
        if (view != null){
            view.hideProgress();
            view.showPasswordValidationError();
        }
    }

    @Override
    public void onError(String error, int... code) {
        if (view != null){
            if (code[0] == 401){
                error = ThisApplication.getInstance().getString(R.string.invalid_email_or_password);
            }
            view.hideProgress();
            view.showError(error, code);
        }
    }
}
