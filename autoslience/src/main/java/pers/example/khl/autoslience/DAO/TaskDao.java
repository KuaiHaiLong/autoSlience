package pers.example.khl.autoslience.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pers.example.khl.autoslience.DBHelper.AutoSlienceDBHelper;
import pers.example.khl.autoslience.DTO.Task;

/**
 * Created by kuaihailong on 2/4/2016.
 */
public class TaskDao {

    private AutoSlienceDBHelper helper;
    private SQLiteDatabase db;

    public TaskDao(Context context) {
        this.helper = new AutoSlienceDBHelper(context);
        this.db = helper.getWritableDatabase();
    }

    //添加多条任务
    public void add(List<Task> Tasks) {
        db.beginTransaction();
        try {
            for (Task task : Tasks) {
                this.addTask(task);
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }


    //单个任务添加
    public void addTask(Task task){
        db.execSQL("INSERT INTO task VALUES(null, ?, ?, ?, ?, ?, ?)",
                new Object[]{task.is_valid, task.is_repeated, task.start_time, task.end_time,
                        task.daysOfWeek, task.info});
    }

    //获取所有的任务，返回List<Task>任务列表
    public List<Task> getAllTask(){

        List<Task> tasks = new ArrayList<Task>();
        Cursor taskCursor = this.queryTheCursor();
        while(taskCursor.moveToNext()){
            Task task = new Task();
            task._id = taskCursor.getInt(taskCursor.getColumnIndex("_id"));
            task.is_repeated = taskCursor.getInt(taskCursor.getColumnIndex("is_repeated"));
            task.is_valid = taskCursor.getInt(taskCursor.getColumnIndex("is_valid"));
            task.info = taskCursor.getString(taskCursor.getColumnIndex("info"));
            task.start_time = taskCursor.getString(taskCursor.getColumnIndex("start_time"));
            task.end_time = taskCursor.getString(taskCursor.getColumnIndex("end_time"));
            task.daysOfWeek = taskCursor.getString(taskCursor.getColumnIndex("daysOfWeek"));
            tasks.add(task);
        }
        return  tasks;
    }


    //定时任务查询
/*    public List<Task> queryReturnList() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            Task task = new Task();
            task._id = c.getInt(c.getColumnIndex("_id"));
            task.time = c.getString(c.getColumnIndex("time"));
            task.week = c.getString(c.getColumnIndex("week"));
            task.onOff = c.getInt(c.getColumnIndex("onOff"));
            task.info = c.getString(c.getColumnIndex("info"));
            tasks.add(task);
        }
        c.close();
        return tasks;
    }*/

    public void initData(){
        ArrayList<Task> tasks = new ArrayList<Task>();
        int n = 10;

        for(int i = 0 ; i < n ;i++){
            Task task = new Task(1,1,"1028","1429","0101101","");
            tasks.add(task);
        }

        this.add(tasks);
    }

    //取出所有任务
    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM task",new String[]{});
        return c;
    }

    public void closeDB() {
        db.close();
    }

}
