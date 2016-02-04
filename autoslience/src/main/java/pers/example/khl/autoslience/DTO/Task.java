package pers.example.khl.autoslience.DTO;

/**
 * Created by kuaihailong on 2/4/2016.
 */
public class Task {

    public int _id;

    public int is_valid;

    public int is_repeated;

    public String start_time;

    public String end_time;

    public String daysOfWeek;

    public String info;

    public Task(){

    }

    public Task(int is_valid,int is_repeated,String start_time,String end_time,String daysOfWeek,String info){
        //this._id = _id;
        this.is_valid = is_valid;
        this.is_repeated = is_repeated;
        this.start_time = start_time;
        this.info = info;
        this.end_time = end_time;
        this.daysOfWeek = daysOfWeek;
    }

}
