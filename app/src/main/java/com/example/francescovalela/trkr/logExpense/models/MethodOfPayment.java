package com.example.francescovalela.trkr.logExpense.models;

import android.util.Log;

import com.example.francescovalela.trkr.data.local.MethodOfPaymentDataSource;
import com.example.francescovalela.trkr.data.local.MethodOfPaymentRepository;
import com.example.francescovalela.trkr.data.local.MethodOfPaymentTypeDataSource;
import com.example.francescovalela.trkr.data.local.MethodOfPaymentTypeRepository;
import com.example.francescovalela.trkr.data.local.methodofpayment.MethodOfPaymentLocalDataSource;
import com.example.francescovalela.trkr.data.local.methodofpaymenttype.MethodOfPaymentTypeLocalDataSource;

import java.util.Date;
import java.util.List;

import static android.support.test.InstrumentationRegistry.getContext;

/**
 * Created by flavazelli on 2017-02-12.
 */

public class MethodOfPayment {

    private int id;
    private String nickname;
    private int methodOfPaymentTypeId;
    private long date;

    public MethodOfPayment(int id, String nickname, int methodOfPaymentTypeId, long date) {
        this.id = id;
        this.nickname = nickname;
        this.methodOfPaymentTypeId = methodOfPaymentTypeId;
        this.date = date;
    }

    public MethodOfPayment(String nickname, int methodOfPaymentTypeId) {
        this.nickname = nickname;
        this.methodOfPaymentTypeId = methodOfPaymentTypeId;
        this.date = new Date().getTime(); //defaults to current time in milliseconds
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    //Queries the db to find the type
    public String getMethodOfPaymentType() {

        final String[] typeToReturn = new String[1];

        MethodOfPaymentTypeRepository mMethodOfPaymentTypeRepository = MethodOfPaymentTypeRepository.getInstance(MethodOfPaymentTypeLocalDataSource.getInstance(getContext()));

        mMethodOfPaymentTypeRepository.getMethodOfPaymentType(getId(), new MethodOfPaymentTypeDataSource.GetMethodOfPaymentTypeCallback() {

            @Override
            public void onMethodOfPaymentTypeLoaded(MethodOfPaymentType type) {
                typeToReturn[0] = type.getName();
            }

            @Override
            public void onDataNotAvailable() {
                Log.e("no type available", "no type available");
            }
        });

        return typeToReturn[0];
    }

    //should not be able to set (non feature yet)
    public void setMethodOfPaymentTypeId(int methodOfPaymentTypeId) {
        this.methodOfPaymentTypeId = methodOfPaymentTypeId;
    }

    public Date getDate() {

        Date date = new Date(this.date);
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
