package pers.example.khl.autoslience.UI;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import pers.example.khl.autoslience.R;

public class CreatTaskFragment extends Fragment implements View.OnClickListener ,TimePickerDialog.OnTimeSetListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creat_task, container, false);
        Button start_time = (Button)view.findViewById(R.id.start_time_choose);
        start_time.setOnClickListener(this);
        Button end_time = (Button)view.findViewById(R.id.end_time_choose);
        end_time.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {

        Integer ViewId = v.getId();

        switch(ViewId){
            case R.id.start_time_choose:
                timeChoose();
                break;
            case R.id.end_time_choose:
                break;
            default:
                break;
        }

    }

/*    private  String timeChoose(){

        Calendar c = Calendar.getInstance();

        new TimePickerDialog(this.getActivity(),
                //绑定监听器
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        *//*EditText show=(EditText)findViewById(R.id.start_time_choose);
                        show.setText("您选择了："+hourOfDay+"时"+minute+"分");*//*
                    }
                }
                //设置初始时间
                ,c.get(Calendar.HOUR_OF_DAY)
                ,c.get(Calendar.MINUTE)
                //true表示采用24小时制
                ,true).show();
        return null;
    }*/

    private  void timeChoose(){

        Calendar c = Calendar.getInstance();

        new TimePickerDialog(this.getActivity(),
                //绑定监听器
                /*new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // TODO Auto-generated method stub
                        EditText show=(EditText) view.findViewById(R.id.start_time_show);
                        show.setText(hourOfDay+"时"+minute+"分");
                    }
                }*/
                this
                //设置初始时间
                ,c.get(Calendar.HOUR_OF_DAY)
                ,c.get(Calendar.MINUTE)
                //true表示采用24小时制
                ,true).show();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Log.d("create_time_hourOfDay",""+hourOfDay);

    }
}
