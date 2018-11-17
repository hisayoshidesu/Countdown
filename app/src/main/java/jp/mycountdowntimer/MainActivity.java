package jp.mycountdowntimer;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mTimerText;
    MyCountDownTimer mTimer;
    SoundPool mSoundPool;
    int mSoundResId;

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture,
                                long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntiFinished) {
            long minute = millisUntiFinished / 1000 / 60;
            long second = millisUntiFinished / 1000 % 60;
            mTimerText.setText(String.format("%id:%2$02d", minute, second));
        }

        @Override
        public void onFinish() {
            mTimerText.setText("0:00");

            mSoundPool.play(mSoundResId, 1.0f, 1.0f, 0,0, 1.0f);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTimerText = (TextView) findViewById(R.id.timerText);
        mTimer = new MyCountDownTimer(3 * 60 * 1000, 1000);
    }

    public void onStartButtonTapped(View view) {
        mTimer.start();
    }
    public  void onStopButtonTapped(View view){
        mTimer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSoundPool = new SoundPool(2, AudioManager.STREAM_ALARM ,0);
        //mSoundResId = mSoundPool.load(this, R.raw.bellsound,1);//R.フォルダ名.ファイル名
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSoundPool.release();
    }
}
