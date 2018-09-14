package com.iamagamedev.sharpdeveloperstestjava.ui.generalActivity;

/**
 * Created by Diana on 09.10.2016.
 */
public interface IGeneralView {

    void showProgress();

    void hideProgress();

    void showError(String error, int... code);

    void showError(int error, int... code);
}
