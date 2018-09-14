package com.iamagamedev.sharpdeveloperstestjava.ui.profileActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iamagamedev.sharpdeveloperstestjava.R;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.UserListObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder>{

    private List<UserListObject> mUserListObjects;
    private OnUserListItemListener listener;

    interface OnUserListItemListener{
        void onUserListItemClick(int position);
    }

    public void setListener(OnUserListItemListener listener) {
        this.listener = listener;
    }

    public UserListAdapter(List<UserListObject>userListObjects){
        this.mUserListObjects = userListObjects;
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item, viewGroup, false);
        return new UserListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, int position) {
        String id = "Id: " + mUserListObjects.get(position).getId();
        holder.userListIdText.setText(id);
        String name = "Name: " + mUserListObjects.get(position).getName();
        holder.userListNameText.setText(name);
    }

    @Override
    public int getItemCount() {
        return mUserListObjects.size();
    }

    public class UserListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.userListNameText)
        TextView userListNameText;
        @BindView(R.id.userListIdText)
        TextView userListIdText;


         UserListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null){
                listener.onUserListItemClick(getAdapterPosition());
            }
        }
    }
}
