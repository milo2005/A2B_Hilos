package creati.movil.cronometroapp.Background;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Dario Chamorro on 20/08/2015.
 */
public class CronometroThread extends Thread {

    public static final int SECOND = 0;

    boolean running,paused;
    int cont;
    Handler handler;

    public CronometroThread(Handler handler)
    {
        this.handler = handler;
    }

    @Override
    public void run() {
        running = true;
        cont = 0;

        while (running)
        {
            try {
                Thread.sleep(1000);
                if (!paused && running) {
                    cont++;

                    Message msg = handler.obtainMessage();
                    msg.what = SECOND;
                    msg.arg1 = cont;

                    handler.sendMessage(msg);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

    public void stopCont()
    {
        running = false;
    }
    public void pauseCont(boolean paused)
    {
        this.paused = paused;
    }

    public boolean isPaused()
    {
        return  paused;
    }
}
