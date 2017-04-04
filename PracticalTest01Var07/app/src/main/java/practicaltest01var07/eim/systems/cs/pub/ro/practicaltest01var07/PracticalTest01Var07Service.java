package practicaltest01var07.eim.systems.cs.pub.ro.practicaltest01var07;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var07Service extends Service {
    public PracticalTest01Var07Service() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("[Message]", "In onCommand in Service");

        int num1 = intent.getIntExtra("sum", 0);

        ProcessingThread p  = new ProcessingThread(this, num1);
        p.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
