package com.khinthirisoe.speedtestapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by khinthirisoe on 5/11/17.
 */

public class BenchMark {

    public static int count = 0;
    static JSONObject obj = new JSONObject();
    static JSONObject root = new JSONObject();
    static JSONArray array = new JSONArray();

    static float download, upload, ping;

    public static void hourly() {

        final long period = 1000;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                float download = SpeedTest.downloadSpeed();
                float upload = SpeedTest.uploadSpeed();
                float ping = SpeedTest.ping();
                long dateMillionSecond = System.currentTimeMillis();

                try {
                    obj.put(PrefUtils.KEY_DOWNLOAD, download);
                    obj.put(PrefUtils.KEY_UPLOAD, upload);
                    obj.put(PrefUtils.KEY_PING, ping);
                    obj.put(PrefUtils.KEY_DATE, dateMillionSecond);
                    array.put(obj);
                    root.put("speeds", array);
                    count++;

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, 0, period);
    }

    public static float downloadAverage(Date date) {
        try {
            JSONObject json = new JSONObject(root.toString());
            JSONArray arr = json.getJSONArray("speeds");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.optJSONObject(i);
                download = Float.valueOf(obj.getString(PrefUtils.KEY_DOWNLOAD));
                long d = obj.getLong(PrefUtils.KEY_DATE);
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date dJson = new Date(d);

                if (dateFormat.format(date).equals(dateFormat.format(dJson))) {
                    return download;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return download;
    }

    public static float uploadAverage(Date date) {
        try {
            JSONObject json = new JSONObject(root.toString());
            JSONArray arr = json.getJSONArray("speeds");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.optJSONObject(i);
                upload = Float.valueOf(obj.getString(PrefUtils.KEY_UPLOAD));
                long d = obj.getLong(PrefUtils.KEY_DATE);
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date dJson = new Date(d);

                if (dateFormat.format(date).equals(dateFormat.format(dJson))) {
                    return upload;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return upload;
    }

    public static float pingAverage(Date date) {
        try {
            JSONObject json = new JSONObject(root.toString());
            JSONArray arr = json.getJSONArray("speeds");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.optJSONObject(i);
                ping = Float.valueOf(obj.getString(PrefUtils.KEY_PING));
                long d = obj.getLong(PrefUtils.KEY_DATE);
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date dJson = new Date(d);

                if (dateFormat.format(date).equals(dateFormat.format(dJson))) {
                    return ping;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ping;
    }

    public static float downloadAverage() {
        try {
            JSONObject json = new JSONObject(root.toString());
            JSONArray arr = json.getJSONArray("speeds");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.optJSONObject(i);
                download += Float.valueOf(obj.getString(PrefUtils.KEY_DOWNLOAD));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return download / array.length();
    }

    public static float uploadAverage() {
        try {
            JSONObject json = new JSONObject(root.toString());
            JSONArray arr = json.getJSONArray("speeds");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.optJSONObject(i);
                upload += Float.valueOf(obj.getString(PrefUtils.KEY_UPLOAD));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return upload / array.length();
    }

    public static float pingAverage() {
        try {
            JSONObject json = new JSONObject(root.toString());
            JSONArray arr = json.getJSONArray("speeds");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.optJSONObject(i);
                ping += Float.valueOf(obj.getString(PrefUtils.KEY_PING));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ping / array.length();
    }
}
