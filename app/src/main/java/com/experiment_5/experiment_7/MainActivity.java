package com.experiment_5.experiment_7;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

        private SensorManager mSensorManager;
        private TextView mTxtValue2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mTxtValue2 = (TextView) findViewById(R.id.txt_value2);

            // 获取传感器管理对象
            mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        }

        @Override
        protected void onResume() {
            super.onResume();
            // 为方向传感器注册监听器
            mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
        }

        @Override
        protected void onStop() {
            super.onStop();
            // 取消监听
            mSensorManager.unregisterListener(this);
        }

        // 当传感器的值改变的时候回调该方法
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            // 获取传感器类型
            int type = event.sensor.getType();
            StringBuilder sb;
            switch (type) {
                case Sensor.TYPE_ORIENTATION:
                    sb = new StringBuilder();
                    sb.append("\n方向传感器返回数据：");
                    sb.append("\n绕Z轴转过的角度：");
                    sb.append(values[0]);
                    sb.append("\n绕X轴转过的角度：");
                    sb.append(values[1]);
                    sb.append("\n绕Y轴转过的角度：");
                    sb.append(values[2]);
                    mTxtValue2.setText(sb.toString());
                    break;
            }
        }

        // 当传感器精度发生改变时回调该方法
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
}