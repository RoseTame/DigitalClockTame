package com.example.digitalclock;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView tvHourMinute;
    private TextView tvSecond;
    private TextView tvAP;
    private TextView tvWeek;
    private TextView tvDate;
    private Timer timer;
    private TimerTask timerTask;
    private Calendar calendar;
    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatTime;
    private SimpleDateFormat formatSecond;
    private SimpleDateFormat formatWeek;
    private SimpleDateFormat formatAP;

    private String year_month_day;
    private String hour_minute;
    private String second;
    private String week;
    private String ap;
    private ConstraintLayout layout;
    private ActionBar actionBar;

    public void init() {
        // 页面元素
        tvHourMinute = (TextView) findViewById(R.id.hour_minute);
        tvSecond = (TextView) findViewById(R.id.second);
        tvAP = (TextView) findViewById(R.id.ap);
        tvWeek = (TextView) findViewById(R.id.week);
        tvDate = (TextView) findViewById(R.id.date);

        // 主题
        layout = findViewById(R.id.digital_clock);

        // 日期时间格式化
        formatDate = new SimpleDateFormat("yyyy/M/d", Locale.CHINA);
        formatTime = new SimpleDateFormat("HH:mm", Locale.CHINA);
        formatSecond = new SimpleDateFormat("ss", Locale.CHINA);
        formatWeek = new SimpleDateFormat("EEE", Locale.CHINA);
        formatAP = new SimpleDateFormat("a", Locale.ENGLISH);

        // ActionBar
        actionBar = getSupportActionBar(); // 获取 ActionBar
        setActionBar();

        // 定时器
        timer = new Timer();
    }

    public void start() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        update();   // 更新时间数据
                        setContent();  //更新到App页面中
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000); // 每秒更新一次
    }

    public void update() {
        calendar = Calendar.getInstance();
        hour_minute = formatTime.format(calendar.getTime());
        year_month_day = formatDate.format(calendar.getTime());
        second = formatSecond.format(calendar.getTime());
        week = formatWeek.format(calendar.getTime());
        ap = formatAP.format(calendar.getTime());
    }

    public void setContent() {
        tvHourMinute.setText(hour_minute);
        tvSecond.setText(second);
        tvDate.setText(year_month_day);
        tvWeek.setText(week);
        tvAP.setText(ap);
    }

    public void setActionBar() {
        actionBar.setTitle("  Digital Clock Tame"); // 设置标题
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.clock_logo); // 设置 LOGO
        actionBar.setDisplayUseLogoEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.light:
                setLightTheme();
                break;
            case R.id.night:
                setNightTheme();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setLightTheme() {
        layout.setBackgroundColor(Color.WHITE);
        tvHourMinute.setTextColor(Color.BLACK);
        tvSecond.setTextColor(Color.BLACK);
        tvDate.setTextColor(Color.BLACK);
        tvWeek.setTextColor(Color.BLACK);
        tvAP.setTextColor(Color.BLACK);
    }

    public void setNightTheme() {
        layout.setBackgroundColor(Color.BLACK);
        tvHourMinute.setTextColor(Color.WHITE);
        tvSecond.setTextColor(Color.WHITE);
        tvDate.setTextColor(Color.WHITE);
        tvWeek.setTextColor(Color.WHITE);
        tvAP.setTextColor(Color.WHITE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        start();
    }
}