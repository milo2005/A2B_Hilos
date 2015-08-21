package creati.movil.cronometroapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import creati.movil.cronometroapp.Background.CronometroThread;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CronometroThread.SECOND)
            {
                int second = msg.arg1;
                txtCronometro.setText(""+second);
            }
        }
    };

    CronometroThread thread;
    TextView txtCronometro;
    Button btnPlay,btnPause,btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCronometro = (TextView) findViewById(R.id.txt_cronometro);
        btnPlay = (Button) findViewById(R.id.btn_play);
        btnPause = (Button) findViewById(R.id.btn_pause);
        btnStop = (Button) findViewById(R.id.btn_stop);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_play:
                if (thread == null) {
                    thread = new CronometroThread(handler);
                    thread.start();
                }
                else if (thread.isPaused())
                    thread.pauseCont(false);
                break;
            case R.id.btn_pause:
                if (thread != null)
                    thread.pauseCont(true);
                break;
            case R.id.btn_stop:
                if (thread != null)
                {
                    thread.stopCont();
                    thread = null;
                    txtCronometro.setText("00");
                }
                break;
        }
    }
}
