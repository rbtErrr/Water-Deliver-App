package com.example.mk7.waterdeliverapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int qantity = 5;
    private int price = 0;
    private CheckBox forOfficeCheck;
    private CheckBox forPersonalUseCheck;
    String sName;
    String total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void increment(View view) {
        if (qantity == 600) {
            Toast.makeText(this, "You cant order more then 600 litres", Toast.LENGTH_SHORT).show();
            return;
        }
        qantity = qantity + 5;
        display(qantity);

    }


    public void decrement(View view) {
        if (qantity == 5) {
            Toast.makeText(this, "You cant order less then 5 litres", Toast.LENGTH_SHORT).show();
            return;
        }
        qantity = qantity - 5;
        display(qantity);
    }

    private void display(int number) {
        TextView qantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        qantityTextView.setText("" + number);
    }


    public void totalOrder(View view) {
        TextView editedName = (TextView) findViewById(R.id.edit_name);
        sName = editedName.getText().toString();

        forOfficeCheck = (CheckBox) findViewById(R.id.for_office);
        boolean isOfficeCecked = forOfficeCheck.isChecked();

        forPersonalUseCheck = (CheckBox) findViewById(R.id.for_personal);
        boolean isPersonalCheck = forPersonalUseCheck.isChecked();

        priceTotal(qantity, isOfficeCecked, isPersonalCheck);
        priceDisplayMessage(sName, qantity, isOfficeCecked, isPersonalCheck, price);



    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Water deliver for " + sName);
        intent.putExtra(Intent.EXTRA_TEXT, total);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void priceTotal(int qantity, boolean isOfficeCecked, boolean isPersonalCheck) {
        if (isOfficeCecked && isPersonalCheck) {
            Toast.makeText(this, "Chose one", Toast.LENGTH_SHORT).show();
            price = 0;
            return;
        } else if (isOfficeCecked) {
            price = qantity * 6;
        } else if (isPersonalCheck) {
            price = qantity * 5;
        }else {
            Toast.makeText(this, "Chose one", Toast.LENGTH_SHORT).show();
            price = 0;
            return;
        }


    }

    public void priceDisplayMessage(String name, int quantity, boolean isOfficeCecked, boolean isPersonalCheck, int price){
        TextView allInformation = (TextView) findViewById(R.id.price_text_view);
        String total = "Your name: " + name;
        total +="\n for Office: " + isOfficeCecked;
        total +="\n for Personal Use: " + isPersonalCheck;
        total +="\n Total price: " + price;
        allInformation.setText(total);



    }


}
