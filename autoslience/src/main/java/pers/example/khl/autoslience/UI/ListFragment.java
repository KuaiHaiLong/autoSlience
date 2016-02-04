package pers.example.khl.autoslience.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pers.example.khl.autoslience.Adapter.TaskAdapter;
import pers.example.khl.autoslience.DAO.TaskDao;
import pers.example.khl.autoslience.DTO.Task;
import pers.example.khl.autoslience.R;

public class ListFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TaskDao taskDao = new TaskDao(this.getActivity());
        if(taskDao.queryTheCursor().getCount()==0){
            taskDao.initData();
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        FragmentActivity c = getActivity();
        RecyclerView mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);

        mRecyclerView.setLayoutManager(layoutManager);
        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new TaskAdapter(taskDao.getAllTask(),R.layout.item_list));

        return view;
    }

}
