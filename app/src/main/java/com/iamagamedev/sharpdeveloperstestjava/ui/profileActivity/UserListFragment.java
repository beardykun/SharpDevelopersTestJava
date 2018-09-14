package com.iamagamedev.sharpdeveloperstestjava.ui.profileActivity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iamagamedev.sharpdeveloperstestjava.R;
import com.iamagamedev.sharpdeveloperstestjava.app.Constants;
import com.iamagamedev.sharpdeveloperstestjava.app.ThisApplication;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserListObject;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserListFragment extends DialogFragment implements UserListAdapter.OnUserListItemListener {

    List<UserListObject> userListObjects;
    @BindView(R.id.fragmentRecyclerView)
    RecyclerView recycler;

    public UserListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, view);
        // Inflate the layout for this fragment
        if (getArguments() != null) {
            userListObjects = getArguments().getParcelableArrayList(Constants.SAVE_LIST);
        }
        UserListAdapter adapter = new UserListAdapter(userListObjects);
        adapter.setListener(this);
        recycler.addItemDecoration(new DividerItemDecoration(ThisApplication.getInstance(),
                DividerItemDecoration.VERTICAL));
        recycler.setAdapter(adapter);
        return view;
    }

    @Override
    public void onUserListItemClick(int position) {
        ((ProfileActivity) Objects.requireNonNull(getActivity())).profileRecipientNameText.setText(userListObjects.get(position).getName());
        dismiss();
    }
}
