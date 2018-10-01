package com.iamagamedev.sharpdeveloperstestjava.ui.transactionsHistoryActivity;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.iamagamedev.sharpdeveloperstestjava.R;
import com.iamagamedev.sharpdeveloperstestjava.app.Constants;
import com.iamagamedev.sharpdeveloperstestjava.app.ThisApplication;
import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionsListAdapter extends RecyclerView.Adapter<TransactionsListAdapter.TransactionsViewHolder>
        implements Filterable {

    private List<TransactionObject> transactionObjects;
    private List<TransactionObject> transactionObjects2;
    private OnTransactionsListListener listener;
    private TransactionsFilter transactionsFilter;

    interface OnTransactionsListListener {
        void onTransactionsListItemClick(TransactionObject transactionObject);
    }

    public void setListener(OnTransactionsListListener listener) {
        this.listener = listener;
    }

    public TransactionsListAdapter(List<TransactionObject> transactionObjects) {
        this.transactionObjects = transactionObjects;
        this.transactionObjects2 = transactionObjects;
    }

    @NonNull
    @Override
    public TransactionsListAdapter.TransactionsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.transactions_list_item, viewGroup, false);
        return new TransactionsListAdapter.TransactionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsListAdapter.TransactionsViewHolder holder, int position) {
        String id = "Transaction Id: " + transactionObjects2.get(position).getId();
        holder.transactionIdText.setText(id);
        holder.transactionDateText.setText(transactionObjects2.get(position).getDate());
        holder.transactionUsernameText.setText(transactionObjects2.get(position).getUsername());
        String amount = transactionObjects2.get(position).getAmount() + " PW";
        holder.transactionAmountText.setText(amount);
        String balance = transactionObjects2.get(position).getBalance() + " PW";
        holder.transactionBalanceText.setText(balance);
    }

    @Override
    public int getItemCount() {
        return (transactionObjects2 != null) ? transactionObjects2.size() : 0;
    }

    public class TransactionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.transactionIdText)
        TextView transactionIdText;
        @BindView(R.id.transactionDateText)
        TextView transactionDateText;
        @BindView(R.id.transactionUsernameText)
        TextView transactionUsernameText;
        @BindView(R.id.transactionAmountText)
        TextView transactionAmountText;
        @BindView(R.id.transactionBalanceText)
        TextView transactionBalanceText;

        public TransactionsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onTransactionsListItemClick(transactionObjects2.get(getAdapterPosition()));
            }
        }
    }

    @Override
    public Filter getFilter() {
        if (transactionsFilter == null) {
            transactionsFilter = new TransactionsFilter();
        }
        return transactionsFilter;
    }

    private class TransactionsFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<TransactionObject> filterList = new ArrayList<>();
                for (int i = 0; i < transactionObjects.size(); i++) {
                    if ((getCompareString(i)).contains(constraint.toString().toUpperCase())) {
                        filterList.add(transactionObjects.get(i));
                    }
                }
                filterResults.count = filterList.size();
                filterResults.values = filterList;
            } else {
                filterResults.count = transactionObjects.size();
                filterResults.values = transactionObjects;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            transactionObjects2 = (List<TransactionObject>) results.values;
            notifyDataSetChanged();

        }
    }

    private String getCompareString(int i) {
        String filter = PreferenceManager.getDefaultSharedPreferences(ThisApplication.getInstance())
                .getString(Constants.SORTING, null);
        switch (Objects.requireNonNull(filter)) {
            case Constants.SORT_BY_NAME:
                return transactionObjects.get(i).getUsername().toUpperCase().trim();
            case Constants.SORT_BY_DATE:
                return transactionObjects.get(i).getDate().toUpperCase().trim();
            case Constants.SORT_BY_AMOUNT:
                return transactionObjects.get(i).getAmount().toUpperCase().trim();
            default:
                return transactionObjects.get(i).getUsername().toUpperCase().trim();
        }
    }
}
