package com.assignment.individual.settlelah.activity.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.individual.settlelah.R;
import com.assignment.individual.settlelah.activity.Model.Result;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private List<Result> splitResults;

    public ResultAdapter(List<Result> splitResults) {
        this.splitResults = splitResults;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_result_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = splitResults.get(position);
        holder.totalAmountTextView.setText("Total Amount: $" + result.getTotalAmount());
        holder.numPeopleTextView.setText("Number of People: " + result.getNumPeople());
        holder.individualShareTextView.setText("Individual Share: $" + result.getIndividualShare());
    }

    @Override
    public int getItemCount() {
        return splitResults.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView totalAmountTextView;
        public TextView numPeopleTextView;
        public TextView individualShareTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            totalAmountTextView = itemView.findViewById(R.id.totalAmountTextView);
            numPeopleTextView = itemView.findViewById(R.id.numPeopleTextView);
            individualShareTextView = itemView.findViewById(R.id.individualShareTextView);
        }
    }
}
