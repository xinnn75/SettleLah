package com.assignment.individual.settlelah.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.individual.settlelah.R;

public class MainActivity extends AppCompatActivity {

    //Calculation
    private EditText editTextTotalAmount, editTextNumberOfPeople;
    private Button buttonCalculate;
    private TextView textViewResult;

    //Database
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        dbHelper = new DBHelper(this);

        editTextTotalAmount = findViewById(R.id.editTextTotalAmount);
        editTextNumberOfPeople = findViewById(R.id.editTextNumberOfPeople);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get values from input fields
                double totalAmount = Double.parseDouble(editTextTotalAmount.getText().toString());
                int numberOfPeople = Integer.parseInt(editTextNumberOfPeople.getText().toString());

                // Calculate individual share
                double individualShare = totalAmount / numberOfPeople;

                // Display result
                String result = "Individual Share: RM" + individualShare;
                textViewResult.setText(result);

                // Save split result in database
                saveSplitResult(totalAmount, numberOfPeople, individualShare);

                // Launch SplitResultsActivity
                launchResultActivity();
            }
        });
    }

    private void saveSplitResult(double totalAmount, int numPeople, double individualShare) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_TOTAL_AMOUNT, totalAmount);
        values.put(DBHelper.COLUMN_NUM_PEOPLE, numPeople);
        values.put(DBHelper.COLUMN_INDIVIDUAL_SHARE, individualShare);

        long newRowId = db.insert(DBHelper.TABLE_NAME, null, values);
        if (newRowId == -1) {
            // Error handling
        }
    }

    private void launchResultActivity() {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        startActivity(intent);
    }

    private void saveAndDisplayResults(double[] individualShares) {
        // Save individual shares to the database
        saveIndividualSharesToDatabase(individualShares);

        // Calculate and display total share amount
        double totalShare = 0;
        for (double share : individualShares) {
            totalShare += share;
        }
        String resultText = "Total Share: $" + totalShare;
        textViewResult.setText(resultText);

        // Configure the share button
        configureShareButton(totalShare);
    }

    private void saveIndividualSharesToDatabase(double[] individualShares) {
        DBHelper dbHelper = new DBHelper(this);

        for (double shareAmount : individualShares) {
            dbHelper.insertShare(shareAmount);
        }

        dbHelper.close();
    }

    private void shareBillResultOnWhatsApp(double totalShare) {
        String shareText = "Total Share: $" + totalShare;

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        shareIntent.setPackage("com.whatsapp"); // Specify WhatsApp package

        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);
        } else {
            // WhatsApp is not installed or the user didn't select it
            Toast.makeText(this, "WhatsApp not installed", Toast.LENGTH_SHORT).show();
        }
    }
    private void configureShareButton(final double totalShare) {
        Button shareButton = findViewById(R.id.buttonShareResult);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareBillResultOnWhatsApp(totalShare);
            }
        });
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
