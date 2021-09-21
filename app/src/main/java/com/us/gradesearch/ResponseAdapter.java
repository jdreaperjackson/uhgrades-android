package com.us.gradesearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.us.gradesearch.databinding.ResponseRowBinding;

import org.jetbrains.annotations.NotNull;

public class ResponseAdapter extends ListAdapter<ApiResponse, ResponseAdapter.MyViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ResponseRowBinding binder;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            binder = ResponseRowBinding.bind(itemView);
        }
    }


    ResponseAdapter()  {super(DIFF_CALLBACK);}

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.response_row, parent, false);
        return new MyViewHolder(itemView);
    }

     public void onBindViewHolder(MyViewHolder holder, int position) {
         ApiResponse response = getItem(position);

            holder.binder.tvId.setText( response.getField("id"));
            holder.binder.tvTerm.setText( response. getField("term"));
            holder.binder.tvSubject.setText( response. getField("subject"));
            holder.binder.tvCatNo.setText( response. getField("catalogNbr"));
            holder.binder.tvCourceDes.setText( response. getField("courseDescription"));
            holder.binder.tvInstructorLast.setText( response. getField("instructorLast"));

            holder.binder.tvInstructorFirst.setText( response. getField("instructorFirst"));
            holder.binder.tvACount.setText( response. getField("aCount"));
            holder.binder.tvBCount.setText( response. getField("bCount"));
            holder.binder.tvCCount.setText( response. getField("cCount"));
            holder.binder.tvDCount.setText( response. getField("dCount"));
            holder.binder.tvFCount.setText( response. getField("fCount"));

            holder.binder.tvSatisfactory.setText( response. getField("satisfactory"));
            holder.binder.tvDropCount.setText( response. getField("dropCount"));
            holder.binder.tvPercentA.setText( response. getField("percentA"));




    }





        private static DiffUtil.ItemCallback DIFF_CALLBACK  = new DiffUtil.ItemCallback<ApiResponse>() {
            @Override
            public boolean areItemsTheSame(@NonNull @NotNull ApiResponse oldItem, @NonNull @NotNull ApiResponse newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull @NotNull ApiResponse oldItem, @NonNull @NotNull ApiResponse newItem) {
                return false;
            }
        };




}
