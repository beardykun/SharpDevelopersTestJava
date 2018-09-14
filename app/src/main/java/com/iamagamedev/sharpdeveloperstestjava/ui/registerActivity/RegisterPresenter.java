package com.iamagamedev.sharpdeveloperstestjava.ui.registerActivity;

import com.iamagamedev.sharpdeveloperstestjava.R;
import com.iamagamedev.sharpdeveloperstestjava.app.ThisApplication;

public class RegisterPresenter implements IRegisterPresenter, IRegisterInteractor.OnRegisterListener {

    private IRegisterView view;
    private IRegisterInteractor interactor;

     RegisterPresenter(){
        interactor = new RegisterInteractor();
    }
    @Override
    public void onAttachView(IRegisterView view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {
        this.view = null;
    }

    @Override
    public void register(String username, String password, String password2, String email) {
        if (view != null) {
            view.showProgress();
            interactor.registerUser(username, password, password2, email, this);
        }
    }

    @Override
    public void onUsernameValidationFailed() {
        if (view != null){
            view.hideProgress();
            view.showUsernameValidationError();
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
    public void onPassword2ValidationFailed() {
        if (view != null){
            view.hideProgress();
            view.showPassword2ValidationError();
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
    public void onSuccess() {
        if (view != null){
            view.hideProgress();
            view.onRegistrationCompleted();
        }
    }

    @Override
    public void onError(String error, int... code) {
        if (view != null) {
            view.hideProgress();
            if (code[0] == 400) {
                error = ThisApplication.getInstance().getString(R.string.email_exists);
            }
            view.showError(error, code);
        }
    }
}
