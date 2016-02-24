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

import pers.example.khl.autoslience.DTO.TimeType;
import pers.example.khl.autoslience.R;

public class CreatTaskFragment extends Fragment implements View.OnClickListener ,TimePickerDialog.OnTimeSetListener{

    private TimeType time_type;

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
                time_type = TimeType.Start_time;
                timeChoose();
                break;
            case R.id.end_time_choose:
                time_type = TimeType.End_time;
                timeChoose();
                break;
            default:
                break;
        }

    }


    private  void timeChoose(){

        Calendar c = Calendar.getInstance();

        new TimePickerDialog(this.getActivity(),this
                //设置初始时间
                ,c.get(Calendar.HOUR_OF_DAY)
                ,c.get(Calendar.MINUTE)
                //true表示采用24小时制
                ,true).show();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        switch (time_type){
            case Start_time:
                String res = hourOfDay+"："+minute;
                Log.d("create_time_start",""+res);
                EditText start_time_show = (EditText)this.getActivity().findViewById(R.id.start_time_show);
                start_time_show.setText(res);
                break;
            case End_time:
                String resend = hourOfDay+"："+minute;
                Log.d("create_time_end",""+resend);
                EditText end_time_show = (EditText)this.getActivity().findViewById(R.id.end_time_show);
                end_time_show.setText(resend);
                break;
            default:
                break;
        }
        Log.d("create_time_hourOfDay",""+hourOfDay);

    }
}
