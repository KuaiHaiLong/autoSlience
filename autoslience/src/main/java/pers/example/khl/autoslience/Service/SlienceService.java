package pers.example.khl.autoslience.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kuaihailong on 2/3/2016.
 */
public class SlienceService extends Service {

    /**
     * onBind 是 Service 的虚方法，因此我们不得不实现它。
     * 返回 null，表示客服端不能建立到此服务的连接。
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    //首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
    //如果服务已在运行，则不会调用此方法。
    public void onCreate() {
        super.onCreate();
    }

    //启动服务由另一个组件通过调用 startService() 启动，这会导致调用服务的 onStartCommand() 方法。
    //onStart
    @Override
    public int onStartCommand(Intent intent,int flags,int startId) {
        Log.d("LocalService", "Received start id " + startId + ": " + intent);
        Bundle bundle = intent.getExtras();

        //AudioManager 静音管理
        AudioManager audio = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
        //静音开关
        int on_off = bundle.getInt("on_off");
        switch (on_off){
            case 0:
                audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(this, "已静音！", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(this, "关闭静音！", Toast.LENGTH_SHORT).show();
                audio.setStreamVolume(AudioManager.RINGER_MODE_NORMAL, 10, 0);
                break;
            default:
                Toast.makeText(this, "非可执行服务！", Toast.LENGTH_SHORT).show();
                break;
        }

        //通过调用 stopSelf() 或 stopService() 来停止服务
        stopSelf();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
