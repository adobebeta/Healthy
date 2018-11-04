package com.example.a59070090.healthy.sleep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a59070090.healthy.R;

/**
 * Created by adobebeta on 1/11/2018 AD.
 */

public class SleepFormFragment extends Fragment {

    SQLiteDatabase myDB;
    ContentValues _row;
    Bundle _bundle;
    int _bundleInt;
    EditText _date, _sleep, _wake;
    String _dateSql, _sleepSql, _wakeSql; //store data from db
    String _dateStr, _sleepStr, _wakeStr; //data into layout
    Sleep _itemSleep = new Sleep();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep_form,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //open to use db
        myDB = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);

        //create table if not exist
        myDB.execSQL(
                "CREATE TABLE IF NOT EXISTS user (_id INTEGER PRIMARY KEY AUTOINCREMENT, sleep VARCHAR(5), wake VARCHAR(5), date VARCHAR(11))"
        );
        Log.d("SLEEP_FORM", "CREATE TABLE ALREADY");

        //get Bundle
        _bundle = getArguments();

        _date = getView().findViewById(R.id.sleep_form_date);
        _sleep= getView().findViewById(R.id.sleep_form_timesleep);
        _wake = getView().findViewById(R.id.sleep_form_timewakeup);

        int count = 0;

        //แสดงข้อมูลเก่า เมื่อต้องการจะแก้ไข
        if(_bundle != null){
            _bundleInt = _bundle.getInt("_id"); //get Bundle to Int

            Cursor myCursor = myDB.rawQuery("SELECT * FROM user", null);
            while (myCursor.moveToNext()){
                if(count == _bundleInt){
                    _dateSql = myCursor.getString(3);
                    _sleepSql = myCursor.getString(1);
                    _wakeSql = myCursor.getString(2);
                    _bundleInt = myCursor.getInt(0); //เช็คเป็น _id เพราะจะเอาไปอัพเดตตาราง

                    _date.setText(_dateSql);
                    _sleep.setText(_sleepSql);
                    _wake.setText(_wakeSql);

                    Log.d("SLEEP_FORM", "Count = "+count+" _bundleInt = "+_bundleInt+" _id = "+_bundleInt);
                } else {
                    count += 1;
                }
            }
        }

        initSaveBtn();
        initBackBtn();
    }

    void initBackBtn(){
        Button _backBtn = getView().findViewById(R.id.sleep_form_back_btn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new SleepFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    void initSaveBtn(){
        Button _saveBtn = getView().findViewById(R.id.sleep_form_save_btn);
        _saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                _dateStr = _date.getText().toString();
                _sleepStr = _sleep.getText().toString();
                _wakeStr = _wake.getText().toString();

                if(_bundle != null){ //เช็คว่าข้อมูลไหนมีการอัพเดตมั่ง
                    Log.d("SLEEP_FORM", "update = "+_dateStr+_sleepStr+_wakeStr);

                    _itemSleep.setContent(_sleepStr, _wakeStr, _dateSql);
                    _row = _itemSleep.getContent();

                    myDB.update("user", _row, "_id="+_bundleInt, null);

                    Log.d("SLEEP_FORM", "UPDATE ALREADY");
                    Toast.makeText(getActivity(), "UPDATE COMPLETE", Toast.LENGTH_SHORT).show();
                } else { //Bundle = null แปลว่าจะเพิ่มข้อมูล
                    _itemSleep.setContent(_sleepStr, _wakeStr, _dateStr);

                    _row = _itemSleep.getContent();

                    myDB.insert("user", null, _row);

                    Log.d("SLEEP_FORM", "INSERT ALREADY");
                    Toast.makeText(getActivity(), "SAVE", Toast.LENGTH_SHORT).show();
                }

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new SleepFragment())
                        .addToBackStack(null)
                        .commit();

                Log.d("SLEEP_FORM", "GOTO SLEEP");
            }
        });
    }
}

