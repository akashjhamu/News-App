package com.example.user.newstoday;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.newstoday.model.dataholder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
    ArrayList<dataholder> arrayList;
    Context mcontext;


    public MyAdapter(ArrayList<dataholder> arrayList1, Context context)
    {
        this.arrayList=arrayList1;
        mcontext=context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        myViewHolder holder=new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {


        holder.publishAt.setText(arrayList.get(position).getPublishAt());
        holder.author.setText(arrayList.get(position).getAuthor());
        holder.desc.setText(arrayList.get(position).getDesc());




      // Picasso.get().load(arrayList.get(position).getImage()).into(imageView);

        Picasso.with(mcontext).load(arrayList.get(position).getImage()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String detail1=arrayList.get(position).getDetail();

                Intent intent=new Intent(mcontext, com.example.user.newstoday.detail.class);

                Bundle bundle=new Bundle();

                bundle.putString("detail1",detail1);

                intent.putExtras(bundle);

                mcontext.startActivity(intent);






            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class  myViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        TextView publishAt,author,desc;

        public myViewHolder(View itemView) {
            super(itemView);

            imageView=(ImageView)itemView.findViewById(R.id.img);
            publishAt=(TextView)itemView.findViewById(R.id.data);
            author=(TextView)itemView.findViewById(R.id.author);
            desc=(TextView)itemView.findViewById(R.id.discription);


        }
    }


}