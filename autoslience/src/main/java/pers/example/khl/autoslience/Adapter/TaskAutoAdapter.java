package pers.example.khl.autoslience.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import pers.example.khl.autoslience.DAO.TaskDao;
import pers.example.khl.autoslience.DTO.Task;
import pers.example.khl.autoslience.Interface.OnRecyclerViewItemClickListener;
import pers.example.khl.autoslience.R;

/**
 * Created by kuaihailong on 2/5/2016.
 * 继承了BaseAbstractRecycleCursorAdapter，可以监听数据的变化
 */
public class TaskAutoAdapter  extends BaseAbstractRecycleCursorAdapter{

    private int itemLayout;
    /*//当前点击的位置
    private int position;
    //当前点击item的主键
    private int _id;*/

    private Context context;

    private OnRecyclerViewItemClickListener mOnItemClickLitener;

    public void setOnItemClickLitener(OnRecyclerViewItemClickListener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public TaskAutoAdapter(Context context, Cursor c,int itemLayout) {
        super(context, c);
        this.itemLayout = itemLayout;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, Cursor cursor) {
        final Task item = TaskDao.getTask(cursor);
        final int _id = item._id;
        final int position = holder.getLayoutPosition();
        final Context context = this.context;
        final TaskAutoAdapter adapter = this;

        //setText不能设置int型，会报Resources$NotFoundException String resource ID #0x1错误
        //((MyViewHolder) holder).mTextView.setText(item._id);
        ((MyViewHolder) holder).item_name.setText(item.info);
        ((MyViewHolder) holder).item_time.setText(item.start_time+"~"+item.end_time);

        if (mOnItemClickLitener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",_id);
                    bundle.putInt("position",position);
                    mOnItemClickLitener.onItemClick(holder.itemView,bundle);
                }
            });
        }
        ((MyViewHolder) holder).img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("警告");
                builder.setMessage("确定删除吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyItemRemoved(position);
                        TaskDao taskDao = new TaskDao(context);
                        taskDao.delTaskById(item._id);
                        taskDao.closeDB();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new MyViewHolder(v);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView item_name;

        public TextView item_time;

        public ImageView img_del;
  public MyViewHolder(View view) {
            super(view);
            item_name = (TextView)view.findViewById(R.id.item_info);
            item_time = (TextView)view.findViewById(R.id.item_time);
            img_del = (ImageView)view.findViewById(R.id.img_del);
        }
    }
}
