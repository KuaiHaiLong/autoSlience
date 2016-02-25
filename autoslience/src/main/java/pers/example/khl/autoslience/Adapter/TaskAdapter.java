package pers.example.khl.autoslience.Adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pers.example.khl.autoslience.DTO.Task;
import pers.example.khl.autoslience.Interface.OnRecyclerViewItemClickListener;
import pers.example.khl.autoslience.R;

/**
 * Created by kuaihailong on 2/4/2016.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private List<Task> items;
    private int itemLayout;
    //当前点击item的主键
    private int _id;

    public TaskAdapter(List<Task> items, int itemLayout){
        this.items = items;
        this.itemLayout = itemLayout;
    }

    private OnRecyclerViewItemClickListener mOnItemClickLitener;

    public void setOnItemClickLitener(OnRecyclerViewItemClickListener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    //创建新View，被LayoutManager所调用
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new MyViewHolder(v);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Task item = items.get(position);
        holder.mTextView.setText(item.daysOfWeek);
        _id = item._id;

        if (mOnItemClickLitener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", _id);
                    mOnItemClickLitener.onItemClick(holder.itemView, bundle);
                }
            });
        }
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return items.size();
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public MyViewHolder(View view) {
            super(view);
            mTextView = (TextView)view.findViewById(R.id.item_info);
        }
    }
}
