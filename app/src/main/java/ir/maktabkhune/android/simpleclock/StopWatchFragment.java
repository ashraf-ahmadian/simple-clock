package ir.maktabkhune.android.simpleclock;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class StopWatchFragment extends Fragment {
    private TextView timePassed;
    private Button startButton;
    private Button stopButton;

    Handler handler;

    private long startTime;
    private long milliSecondTime;
    private long timeBuff = 0L;
    private long updateTime;



    private int milliSeconds;
    private int secondTime;
    private int minuteTime;
    private int hourTime;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stop_watch_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handler = new Handler();
        findView(view);
        startStopWatch();
        clickOnStop();
        onStop();
        stopButton.setEnabled(false);

    }

    private void findView(View view){
        timePassed = view.findViewById(R.id.stop_watch_time_passed);
        startButton = view.findViewById(R.id.stop_watch_start_btn);
        stopButton = view.findViewById(R.id.stop_watch_stop_btn);
    }

    private void startStopWatch(){
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopButton.setEnabled(true);
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
            }
        });
    }

    private void stopStopWatch(){
        handler.removeCallbacks(runnable);
        stopButton.setEnabled(false);
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            milliSecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff + milliSecondTime;
            milliSeconds = (int) (updateTime %1000);
            secondTime = (int )(updateTime / 1000);
            minuteTime = secondTime / 60;
            hourTime = minuteTime / 60;
            secondTime = secondTime % 60;
            minuteTime = minuteTime % 60;
            timePassed.setText(getString(R.string.stopwatch_time_format, hourTime, minuteTime, secondTime, milliSeconds));
            handler.postDelayed(this, 0);
        }
    };

    private void clickOnStop(){
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopStopWatch();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        stopStopWatch();
    }
}
