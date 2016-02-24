package pers.example.khl.autoslience.DTO;

/**
 * Created by kuaihailong on 2/24/2016.
 */
public enum TimeType {
    Start_time(0),End_time(1);

    private int type;

    private TimeType(int type){
        this.type = type;
    }

    public int getType(){
        return this.type;
    }
}
