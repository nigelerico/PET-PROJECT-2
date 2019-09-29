package com.sourcey.materiallogindemo.adpater;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sourcey.materiallogindemo.R;
import com.sourcey.materiallogindemo.model.Comment;
import com.sourcey.materiallogindemo.model.Order;

import java.util.ArrayList;
import java.util.List;

public class UlasanAdapter extends RecyclerView.Adapter<UlasanAdapter.ViewHolder> {

    private Context mContext;
    private List<Comment> comments;

    public UlasanAdapter(Context mContext, ArrayList<Comment> comments){
        this.mContext = mContext;
        this.comments = comments;
    }

    @Override
    public UlasanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ulasan, parent, false);
        UlasanAdapter.ViewHolder vh = new UlasanAdapter.ViewHolder(mView); // pass the view to View Holder


        return vh;
    }


    @Override
    public void onBindViewHolder(final UlasanAdapter.ViewHolder holder, final int position) {
        final Comment comment = comments.get(position);
        holder.tv_nama.setText(comment.getNama());
        holder.tv_ulasan.setText(comment.getComment());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv_ulasan;
        TextView tv_nama, tv_ulasan;

        public ViewHolder(View itemView) {
            super(itemView);
            cv_ulasan = (CardView) itemView.findViewById(R.id.cv_ulasan);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_ulasan = (TextView) itemView.findViewById(R.id.tv_ulasan);


        }
    }

    public void clear(){
        comments.clear();
        notifyDataSetChanged();
    }

}
