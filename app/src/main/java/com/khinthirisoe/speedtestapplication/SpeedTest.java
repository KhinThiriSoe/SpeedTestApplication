package com.khinthirisoe.speedtestapplication;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by khinthirisoe on 5/11/17.
 */

public class SpeedTest {

    public static float downloadSpeed() {
        Random downloadSpeedRandom = new Random();
        float downloadSpeed = downloadSpeedRandom.nextFloat() * (100 - 0) + 0;
        DecimalFormat df = new DecimalFormat("0.00");

        return Float.parseFloat(df.format(downloadSpeed));
    }

    public static float uploadSpeed() {
        Random uploadSpeedRandom = new Random();
        float downloadSpeed = uploadSpeedRandom.nextFloat() * (100 - 0) + 0;
        DecimalFormat df = new DecimalFormat("0.00");

        return Float.parseFloat(df.format(downloadSpeed));
    }

    public static float ping() {
        Random pingRandom = new Random();
        float ping = pingRandom.nextFloat() * (100 - 0) + 0;
        DecimalFormat df = new DecimalFormat("0.00");

        return Float.parseFloat(df.format(ping));
    }
}
