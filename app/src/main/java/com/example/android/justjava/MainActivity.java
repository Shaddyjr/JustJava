/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int x = 1;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        createOrderSummary(price);
    }
    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if(x<=100){
            displayQuantity(++x);
        }else{
            Toast.makeText(this,"You cannot have more than 100 coffees",Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if(x>2){
            displayQuantity(--x);
        }else{
            Toast.makeText(this,"You cannot have less than 1 coffee",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method returns a string of the order summary
     * @param price of all coffees ordered
     * @return text summary
     */
    private void createOrderSummary(int price){
        CheckBox whip = (CheckBox) findViewById(R.id.whip);
        CheckBox choc = (CheckBox) findViewById(R.id.choc);
        EditText _name = (EditText) findViewById(R.id.userName);
        String name = _name.getText().toString();

//        using strings.xml as resource
        String text = getString(R.string.sum_name, name);
        text += "\n" + getString(R.string.sum_whip, whip.isChecked());
        text += "\n" + getString(R.string.sum_choc, choc.isChecked());
        text += "\n" + getString(R.string.sum_quant, "" + x);
        text += "\n" + getString(R.string.sum_total, NumberFormat.getCurrencyInstance().format(price));
        text += "\n" + getString(R.string.sum_thanks);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:udacity@udacity.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice() {
        CheckBox whip = (CheckBox) findViewById(R.id.whip);
        CheckBox choc = (CheckBox) findViewById(R.id.choc);
        int output = 5;
        if(whip.isChecked()){
            output += 1;
        }

        if(choc.isChecked()){
            output += 2;
        }
        return x * output;
    }

}

