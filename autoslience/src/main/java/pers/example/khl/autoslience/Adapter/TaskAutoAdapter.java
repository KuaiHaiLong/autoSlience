package pers.example.khl.autoslience.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pers.example.khl.autoslience.DAO.TaskDao;
import pers.example.khl.autoslience.DTO.Task;
import pers.example.khl.autoslience.R;

/**
 * Created by kuaihailong on 2/5/2016.
 * 继承了BaseAbstractRecycleCursorAdapter，可以监听数据的变化
 */
public class TaskAutoAdapter  extends BaseAbstractRecycleCursorAdapter{

    private int itemLayout;

    public TaskAutoAdapter(Context context, Cursor c,int itemLayout) {
        super(context, c);
        this.itemLayout = itemLayout;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor) {
        Task item = TaskDao.getTask(cursor);
        //setText不能设置int型，会报Resources$NotFoundException String resource ID #0x1错误
        //((MyViewHolder) holder).mTextView.setText(item._id);
        ((MyViewHolder) holder).mTextView.setText(Integer.toString(item._id));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new MyViewHolder(v);
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
