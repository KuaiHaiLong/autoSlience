package pers.example.khl.autoslience.UI;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.util.Calendar;

import pers.example.khl.autoslience.DAO.TaskDao;
import pers.example.khl.autoslience.DTO.Task;
import pers.example.khl.autoslience.DTO.TimeType;
import pers.example.khl.autoslience.MainActivity;
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
        Button check_out = (Button)view.findViewById(R.id.create_check_out);
        check_out.setOnClickListener(this);
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
            case R.id.create_check_out:
                saveOrUpdateTask();
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
        Log.d("create_time_hourOfDay", "" + hourOfDay);

    }

   public void saveOrUpdateTask(){

       Task task = new Task();
       task.info = ((EditText)this.getActivity().findViewById(R.id.clock_name)).getText().toString();
       task.start_time = ((EditText)this.getActivity().findViewById(R.id.start_time_show)).getText().toString();
       task.end_time = ((EditText)this.getActivity().findViewById(R.id.end_time_show)).getText().toString();

       RadioGroup is_valid_Group = (RadioGroup)this.getActivity().findViewById(R.id.is_valid_Group);
       for (int i = 0; i < is_valid_Group.getChildCount(); i++) {
           RadioButton rd = (RadioButton) is_valid_Group.getChildAt(i);
           if (rd.isChecked()) {
               if("有效".equals(rd.getText().toString())){
                    task.is_valid = 1;
               }else{
                   task.is_valid = 0;
               }
               break;
           }
       }

       RadioGroup is_repeated_Group = (RadioGroup)this.getActivity().findViewById(R.id.is_repeated_Group);
       for (int i = 0; i < is_repeated_Group.getChildCount(); i++) {
           RadioButton rd = (RadioButton) is_repeated_Group.getChildAt(i);
           if (rd.isChecked()) {
               if("是".equals(rd.getText().toString())){
                   task.is_repeated = 1;
               }else{
                   task.is_repeated = 0;
               }
               break;
           }
       }

       StringBuffer days = new StringBuffer();
       CheckBox checkbox_sun = (CheckBox)this.getActivity().findViewById(R.id.checkbox_sun);
       CheckBox checkbox_mon = (CheckBox)this.getActivity().findViewById(R.id.checkbox_mon);
       CheckBox checkbox_tue = (CheckBox)this.getActivity().findViewById(R.id.checkbox_tue);
       CheckBox checkbox_wed = (CheckBox)this.getActivity().findViewById(R.id.checkbox_wed);
       CheckBox checkbox_thu = (CheckBox)this.getActivity().findViewById(R.id.checkbox_thu);
       CheckBox checkbox_fri = (CheckBox)this.getActivity().findViewById(R.id.checkbox_fri);
       CheckBox checkbox_sat = (CheckBox)this.getActivity().findViewById(R.id.checkbox_sat);

       days.append(checkbox_sun.isChecked() ? "1" : "0");
       days.append(checkbox_mon.isChecked()?"1":"0");
       days.append(checkbox_tue.isChecked() ? "1" : "0");
       days.append(checkbox_wed.isChecked()?"1":"0");
       days.append(checkbox_thu.isChecked() ? "1" : "0");
       days.append(checkbox_fri.isChecked()?"1":"0");
       days.append(checkbox_sat.isChecked() ? "1" : "0");

       task.daysOfWeek = days.toString();

       TaskDao taskDao = new TaskDao(this.getActivity());
       taskDao.addTask(task);
       taskDao.closeDB();

       /*无法更新数据表，因为只是退出当前的activity，而新建activit和MainActivity不是同一个activity所以无法取得Adapter(推测)
       RecyclerView mRecyclerView = ((ListFragment)this.getActivity().getFragmentManager().findFragmentByTag("list"))
               .getmRecyclerView();
       mRecyclerView.getAdapter().notifyDataSetChanged();
       this.getActivity().finish();*/

       Intent intent = new Intent(this.getActivity(), MainActivity.class);
       startActivity(intent);

   }
}
