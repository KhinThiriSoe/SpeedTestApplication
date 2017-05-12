package com.khinthirisoe.speedtestapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by khinthirisoe on 5/11/17.
 */

public class BenchMark {

    static JSONObject obj = new JSONObject();
    static JSONObject root = new JSONObject();
    static JSONArray array = new JSONArray();

    static float download, upload, ping;

    public static void hourly() {

        final long period = 1000;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                try {
                    obj.put(PrefUtils.KEY_DOWNLOAD, SpeedTest.downloadSpeed());
                    obj.put(PrefUtils.KEY_UPLOAD, SpeedTest.uploadSpeed());
                    obj.put(PrefUtils.KEY_PING, SpeedTest.ping());
                    obj.put(PrefUtils.KEY_DATE, System.currentTimeMillis());
                    array.put(obj);
                    root.put("speeds", array);

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
                    return download / array.length();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return download / array.length();
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
                    return upload / array.length();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return upload / array.length();
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
                    return ping / array.length();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ping / array.length();
    }

    public static ArrayList<Float> download() {
        ArrayList<Float> downloadArr = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(root.toString());
            JSONArray arr = json.getJSONArray("speeds");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.optJSONObject(i);
                downloadArr.add(Float.valueOf(obj.getString(PrefUtils.KEY_DOWNLOAD)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return downloadArr;
    }

    public static ArrayList<Float> upload() {
        ArrayList<Float> uploadArr = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(root.toString());
            JSONArray arr = json.getJSONArray("speeds");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.optJSONObject(i);
                uploadArr.add(Float.valueOf(obj.getString(PrefUtils.KEY_UPLOAD)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return uploadArr;
    }

    public static ArrayList<Float> ping() {
        ArrayList<Float> pingArr = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(root.toString());
            JSONArray arr = json.getJSONArray("speeds");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.optJSONObject(i);
                pingArr.add(Float.valueOf(obj.getString(PrefUtils.KEY_PING)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pingArr;
    }


}
