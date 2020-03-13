package com.rokad.demo.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rokad.demo.R;
import com.rokad.demo.fragments.dummy.DummyContent.DummyItem;
import com.rokad.demo.interfaces.OnHomeInteractionListener;

import java.util.List;

public class MyServiceRecyclerViewAdapter extends RecyclerView.Adapter<MyServiceRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final OnHomeInteractionListener mListener;

    public MyServiceRecyclerViewAdapter(List<DummyItem> items, OnHomeInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_services_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setImageResource(mValues.get(position).image);
        holder.mTitle.setText(mValues.get(position).title);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onSelectedServiceInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIdView;
        public final TextView mTitle;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.view_image);
            mTitle = view.findViewById(R.id.view_title);
        }
    }
}
