package ir.maktabkhune.android.simpleclock;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CountDownTimerFragment extends Fragment {

    private EditText minuteInput;
    private EditText secondsInput;

    private Button startButton;
    private Button pauseButton;

    private TextView finishTime;

    private int totalMinute;
    private int totalSecond;
    private int totalTime;

    CountDownTimer count;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.count_down_timer_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        pauseButton.setEnabled(false);
        startCountDownTimer();
        clickOnPause();
    }
    private void findView(View view){
        minuteInput = view.findViewById(R.id.minutes_input);
        secondsInput = view.findViewById(R.id.seconds_input);
        startButton = view.findViewById(R.id.count_down_start_resume);
        pauseButton = view.findViewById(R.id.count_down_pause_clear);
        finishTime = view.findViewById(R.id.end_message);
    }

    private int generateTime(){
        totalMinute = Integer.parseInt(minuteInput.getText().toString());
        totalSecond = Integer.parseInt(secondsInput.getText().toString());
        totalTime = (totalMinute * 60) + totalSecond;
        return totalTime;
    }

    private void startCountDownTimer(){
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (minuteInput.length() != 0 && secondsInput.length() !=0){
                    generateTime();
                    count = new CountDownTimer(totalTime*1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            totalSecond = (int) (millisUntilFinished / 1000) %60;
                            totalMinute = (int) (millisUntilFinished / (1000 * 60)) %60;
                            minuteInput.setText(String.valueOf(totalMinute));
                            secondsInput.setText(String.valueOf(totalSecond));
                        }

                        @Override
                        public void onFinish() {
                            finishTime.setText(getString(R.string.end_message));
                            startButton.setEnabled(true);
                            pauseButton.setEnabled(false);
                            minuteInput.setEnabled(true);
                            secondsInput.setEnabled(true);
                            secondsInput.setText("0");
                        }
                    };
                    count.start();
                    startButton.setText(getString(R.string.start));
                    pauseButton.setEnabled(true);
                    startButton.setEnabled(false);
                    minuteInput.setEnabled(false);
                    secondsInput.setEnabled(false);
                }

            }
        });
    }

    private void clickOnPause(){
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseCountDownTimer();
            }
        });
    }

    private void pauseCountDownTimer(){
        count.cancel();
        totalMinute = Integer.valueOf(minuteInput.getText().toString());
        totalSecond = Integer.valueOf(secondsInput.getText().toString());
        minuteInput.setText(String.valueOf(totalMinute));
        secondsInput.setText(String.valueOf(totalSecond));
        pauseButton.setEnabled(false);
        startButton.setText(getString(R.string.resume));
        startButton.setEnabled(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        pauseCountDownTimer();
    }

}
