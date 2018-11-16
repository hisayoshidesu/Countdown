package jp.mycountdowntimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mTimeerText;

    MyCountDownTimer mTimer;
    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture,
                                long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntiFinished) {
            long minute = millisUntiFinished / 1000 / 60;
            long second = millisUntiFinished / 1000 % 60;
            mTimeerText.setText(String.format("%id:%2$02d", minute, second));
        }

        @Override
        public void onFinish() {
            mTimeerText.setText("0:00");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTimeerText = (TextView) findViewById(R.id.timerText);
        mTimer = new MyCountDownTimer(3 * 60 * 1000, 1000);
    }

    public void onStartButtonTapped(View view) {
        mTimer.cancel();
    }
}
