package com.example.a59070090.healthy.sleep;

import android.content.ContentValues;

public class Sleep {

    ContentValues _row = new ContentValues();

    String timeSleep;
    String timeWake;
    String date;
    String timeDiff;

    public Sleep(){}

    public Sleep(String sleep, String wake, String date) {
        timeSleep = sleep;
        timeWake = wake;
        this.date = date;

        setTimeDiff(sleep, wake);
    }

    //ContentValues
    public void setContent(String sleep, String wake, String date) {
        this._row.put("sleep", sleep);
        this._row.put("wake", wake);
        this._row.put("date", date);
    }

    public ContentValues getContent() {
        return _row;
    }

    public String getTimeSleep() {
        return timeSleep;
    }

    public void setTimeSleep(String _timeSleep) {
        this.timeSleep = _timeSleep;
    }

    public String getTimeWake() {
        return timeWake;
    }

    public void setTimeWake(String _timeWake) {
        this.timeWake = _timeWake;
    }

    public String getTimeDiff() {
        return timeDiff;
    }

    //Calculate bedtime
    public void setTimeDiff(String sleep, String wake) {
        int _hour = 0;
        int _min = 0;

        String[] _sleep = sleep.split(":");
        int _sleepHour = Integer.parseInt(_sleep[0]);
        int _sleepMin = Integer.parseInt(_sleep[1]);

        String[] _wake = wake.split(":");
        int _wakeHour = Integer.parseInt(_wake[0]);
        int _wakeMin = Integer.parseInt(_wake[1]);

        if(_sleepHour > _wakeHour){
            _hour = 24 - (_sleepHour - _wakeHour);
        }
        if(_sleepHour < _wakeHour) {
            _hour = _wakeHour - _sleepHour;
        }
        if(_sleepMin > _wakeMin){
            _min = 60 - (_sleepMin - _wakeMin);
            _hour -= 1;
        }
        if(_sleepMin < _wakeMin){
            _min = _wakeMin - _sleepMin;
        }

        String _hourStr = String.format("%02d", _hour);
        String _minStr = String.format("%02d", _min);

        this.timeDiff = _hourStr+":"+_minStr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String _date) {
        this.date = _date;
    }

}
