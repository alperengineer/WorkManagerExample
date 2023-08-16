package com.aok.workmanagerexample;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RefreshDatabase extends Worker {

    SharedPreferences sharedPreferences;
    Context myContext;

    public RefreshDatabase(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        this.myContext = context;


    }

    @NonNull
    @Override
    public Result doWork() {

        Data data = getInputData();
        int getData = data.getInt("intKey", 0);
        refreshDatabase(getData);

        return Result.success();
    }

    private void refreshDatabase(int getData){

        sharedPreferences = myContext.getSharedPreferences("com.aok.workmanagerexample", Context.MODE_PRIVATE);
        int savedNumber = sharedPreferences.getInt("myNumber", 0);
        savedNumber += getData;
        System.out.println(savedNumber);
        sharedPreferences.edit().putInt("myNumber", savedNumber).apply();


    }
}
