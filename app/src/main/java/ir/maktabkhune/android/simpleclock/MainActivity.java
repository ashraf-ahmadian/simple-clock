package ir.maktabkhune.android.simpleclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button stopWatch;
    private Button countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        clickOnCountDownTimer();
        clickOnStopWatch();
    }
    private void findView(){
        stopWatch = findViewById(R.id.stop_watch_btn);
        countDownTimer = findViewById(R.id.count_down_btn);
    }

    private void clickOnCountDownTimer(){
        countDownTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountDownTimerFragment count = new CountDownTimerFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.count_down_frame, count)
                        .addToBackStack("countDown")
                        .commit();



            }
        });
    }
    private void clickOnStopWatch(){
        stopWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StopWatchFragment watch = new StopWatchFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.stop_watch_frame, watch)
                        .addToBackStack("stopWatch")
                        .commit();
            }
        });
    }
}
