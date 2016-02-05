package pers.example.khl.autoslience.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kuaihailong on 2/4/2016.
 */
public class AutoSlienceDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "autoSlience.db";
    private static final int DATABASE_VERSION = 1;


    public AutoSlienceDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS task" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT " +
                ", is_valid INTEGER " +
                ", is_repeated INTEGER " +
                ", start_time TEXT" +
                ", end_time TEXT" +
                ", daysOfWeek TEXT"+
                ", info TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE task ADD COLUMN other STRING");
    }
}
