package practicaltest01var07.eim.systems.cs.pub.ro.practicaltest01var07;

import android.content.Context;
import android.content.Intent;

import java.util.Date;

/**
 * Created by student on 04.04.2017.
 */

public class ProcessingThread extends Thread{
    int sum;
    Context c;

    boolean ok = true;

    ProcessingThread(Context c, int sum) {
        this.c = c;
        this.sum = sum;
    }

    @Override
    public void run() {
        while(ok) {
            sendMessage();
            sleep();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.putExtra("time",  new Date(System.currentTimeMillis()) + "");
        intent.putExtra("sum", sum + "");

        c.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        ok = false;
    }
}
