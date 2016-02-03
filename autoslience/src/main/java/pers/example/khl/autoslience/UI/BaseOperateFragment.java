package pers.example.khl.autoslience.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pers.example.khl.autoslience.R;
import pers.example.khl.autoslience.Service.SlienceService;

public class BaseOperateFragment extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("msg","fragment create view!");

        View view = inflater.inflate(R.layout.fragment_base_operate,
                container, false);
        Button button_on = (Button)view.findViewById(R.id.button_on);
        button_on.setOnClickListener(this);

        return  view;

    }

    @Override
    public void onClick(View v) {

        Integer ViewId = v.getId();
        //View button;
        Intent intent = new Intent(this.getActivity(),SlienceService.class);
        Bundle paramBundle = new Bundle();
        switch(ViewId){
            case R.id.button_on:
                //button = v.findViewById(R.id.button_on);
                //button.setTranslationZ(8);
                Log.d("msg", "on click button on !");
                //Toast.makeText(v.getContext(), "click button!", Toast.LENGTH_SHORT).show();

                paramBundle.putInt("on_off", 0);
                intent.putExtras(paramBundle);
                this.getActivity().startService(intent);

                break;

            case R.id.button_off:

                Log.d("msg", "on click button off !");
                paramBundle.putInt("on_off",1);
                intent.putExtras(paramBundle);
                this.getActivity().startService(intent);

                break;

            default:
                break;
        }

    }
}
