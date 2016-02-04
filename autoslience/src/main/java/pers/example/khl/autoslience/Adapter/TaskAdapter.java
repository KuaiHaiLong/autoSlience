package pers.example.khl.autoslience.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pers.example.khl.autoslience.DTO.Task;
import pers.example.khl.autoslience.R;

/**
 * Created by kuaihailong on 2/4/2016.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private List<Task> items;
    private int itemLayout;

    public TaskAdapter(List<Task> items, int itemLayout){
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Task item = items.get(position);
        holder.mTextView.setText(item.daysOfWeek);
        //All the thing you gonna show in the item
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public MyViewHolder(View view) {
            super(view);
            mTextView = (TextView)view.findViewById(R.id.text_view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("MyViewHolder", "onClick--> position = " + v.getId());
                }
            });
        }
    }
}
