package pers.example.khl.autoslience.UI;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import pers.example.khl.autoslience.Adapter.BaseAbstractRecycleCursorAdapter;
import pers.example.khl.autoslience.Adapter.TaskAdapter;
import pers.example.khl.autoslience.Adapter.TaskAutoAdapter;
import pers.example.khl.autoslience.DAO.TaskDao;
import pers.example.khl.autoslience.DTO.Task;
import pers.example.khl.autoslience.Interface.OnRecyclerViewItemClickListener;
import pers.example.khl.autoslience.R;

public class ListFragment extends Fragment {

    public RecyclerView mRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        FragmentActivity c = getActivity();
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        TaskDao taskDao = new TaskDao(this.getActivity());
        if(taskDao.queryTheCursor().getCount()==0){
            taskDao.initData();
        }

        //使用BaseAbstractRecycleCursorAdapter赋值
        Cursor cursor = taskDao.queryTheCursor();
        TaskAutoAdapter adapter = new TaskAutoAdapter(getActivity(),cursor,R.layout.item_list);

        //使用getAllTask获取数据，直接赋值，不能监听数据变换
        //TaskAdapter adapter = new TaskAdapter(taskDao.getAllTask(),R.layout.item_list);
        adapter.setOnItemClickLitener(new OnRecyclerViewItemClickListener(){
            @Override
            public void onItemClick(View view, Bundle date) {
                Toast.makeText(getActivity(), "id = " + date.getInt("id") + " click",
                        Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    public RecyclerView getmRecyclerView(){
        return mRecyclerView;
    }

}
