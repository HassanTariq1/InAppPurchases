package com.itpvt.inapppurchases;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

public class BasicActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler {


    BillingProcessor bp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
bp= new BillingProcessor(this, null,this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bp.purchase(BasicActivity.this,"android.test.purchased");

            }
        });
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {


        Toast.makeText(getApplicationContext(),"Your got it",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        Toast.makeText(getApplicationContext(),"Your got error",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBillingInitialized() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(!bp.handleActivityResult(requestCode,resultCode,data)){

                super.onActivityResult(requestCode,resultCode,data);

            }
    }

    @Override
    protected void onDestroy() {

        if(bp!=null){

            bp.release();
        }
        super.onDestroy();
    }
}
