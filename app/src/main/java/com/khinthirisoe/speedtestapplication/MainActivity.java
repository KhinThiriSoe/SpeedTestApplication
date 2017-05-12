package com.khinthirisoe.speedtestapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.btnTest)
    Button btnTest;
    @BindView(R.id.btnShowSpeed)
    Button btnShowSpeed;
    @BindView(R.id.linear)
    LinearLayout linearLayout;
    @BindView(R.id.tv_show_speed)
    TextView tvShowSpeed;
    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.btn_show)
    Button btnShow;

    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        date = new Date();

        btnShow.setOnClickListener(this);
        btnShowSpeed.setOnClickListener(this);
        btnTest.setOnClickListener(this);
        tvDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int getId = v.getId();
        switch (getId) {
            case R.id.tv_date:
                showDialog(0);
                break;
            case R.id.btnTest:
                BenchMark.hourly();
                linearLayout.setVisibility(View.VISIBLE);
                btnShow.setVisibility(View.VISIBLE);
                break;
            case R.id.btnShowSpeed:
                searchSpeedByDate(date);
                break;
            case R.id.btn_show:
                speed();
            default:
                break;
        }
    }

    private void searchSpeedByDate(Date date) {
        StringBuilder speedByDate = new StringBuilder();
        speedByDate.append("Download average speed is " + BenchMark.downloadAverage(date) + "\n");
        speedByDate.append("Upload speed is " + BenchMark.uploadAverage(date) + "\n");
        speedByDate.append("Ping is " + BenchMark.pingAverage(date));

        tvShowSpeed.setText(speedByDate);
    }

    private void speed() {
        StringBuilder speed = new StringBuilder();
        speed.append("Download speed is " + BenchMark.download() + "\n");
        speed.append("Upload speed is " + BenchMark.upload() + "\n");
        speed.append("Ping is " + BenchMark.ping());

        tvShow.setText(speed);
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        date = c.getTime();
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            int day = selectedDay;
            int month = selectedMonth;
            int year = selectedYear;
            tvDate.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };
}
