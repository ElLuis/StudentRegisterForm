package com.centennial.elluis.studentstaff;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.centennial.elluis.studentstaff.database.DataSource;

import org.w3c.dom.Text;

public class PaymentActivity extends AppCompatActivity {

    private int username;
    TextView totalAmount;
    EditText amountToPay;
    TextView balance;
    private DataSource db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        db = new DataSource(this);

        Intent intent = getIntent();
        username = intent.getIntExtra("username",-1);
        totalAmount = findViewById(R.id.totalAmountTV);
        amountToPay = findViewById(R.id.amountToPayET);
        balance=findViewById(R.id.balanceTV);

        //Populate attributes of payment
        setTotalAmount();


    }

    public void payBtn_OnClick(View view) {

        //Change balance
        String newBalance = String.valueOf(getBalance());
        balance.setText("Balance: $"+newBalance);
    }

    public void cancelPayBtn_OnClick(View view) {
        amountToPay.setText("");
        balance.setText("");
    }

    public void finishPayment_OnClick(View view) {

        int amountPaid = Integer.parseInt(amountToPay.getText().toString());
        int _balance = Integer.parseInt(balance.getText().toString());

        //update Payment
        db.open();
        db.updatePaymentBalance(username,amountPaid,_balance);
        db.close();

    }

    public void setTotalAmount()
    {
        db.open();
        Cursor c = db.getPayment(username);
        db.close();
        if (c.moveToFirst())
        {
            int _totalAmount = c.getInt(0);
            totalAmount.setText("$"+_totalAmount); //Not parsed to int
        }
    }

    public int getBalance()
    {
        int amountPaid = Integer.parseInt(amountToPay.getText().toString());

        db.open();
        Cursor c = db.getPayment(username);
        db.close();
        if (c.moveToFirst())
        {
            return c.getInt(0) - amountPaid;
        }
        return c.getInt(0);
    }

}
