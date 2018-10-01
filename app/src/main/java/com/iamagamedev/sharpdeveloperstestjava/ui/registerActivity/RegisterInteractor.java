package com.iamagamedev.sharpdeveloperstestjava.ui.registerActivity;

import com.iamagamedev.sharpdeveloperstestjava.repository.repClasses.RepositoryLoginRegister;

public class RegisterInteractor implements IRegisterInteractor {

    @Override
    public void registerUser(String username, String password, String password2, String email, OnRegisterListener listener) {
        if (validate(username, password, password2, email, listener))
            RepositoryLoginRegister.registerUser(username, password, email, listener);
    }

    private boolean validate(String username, String password, String password2,
                             String email, IRegisterInteractor.OnRegisterListener listener) {
        if (username.length() < 3) {
            listener.onUsernameValidationFailed();
            return false;
        } else if (password.length() < 6) {
            listener.onPasswordValidationFailed();
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            listener.onEmailValidationFailed();
            return false;
        } else if (password2.length() < 6) {
            listener.onPasswordValidationFailed();
            return false;
        } else if (!password.equals(password2)) {
            listener.onPassword2ValidationFailed();
            return false;
        }
        return true;
    }
}
