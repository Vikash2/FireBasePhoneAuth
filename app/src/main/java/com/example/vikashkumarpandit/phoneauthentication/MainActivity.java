package com.example.vikashkumarpandit.phoneauthentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText editText;

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null)
        {
            Intent intent = new Intent(this,ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinnerCountry);
        editText = findViewById(R.id.phoneText);

        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,countryData.countryNames));

        findViewById(R.id.continueButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String code = countryData.countryAreaCodes[spinner.getSelectedItemPosition()];

                String number =editText.getText().toString().trim();

                if(number.isEmpty() || number.length() < 10)
                {
                    MainActivity.this.editText.setError("Valid Number is required");
                    MainActivity.this.editText.requestFocus();
                    return;
                }

                String phoneNumber = "+" + code + number;
                Intent intent = new Intent(MainActivity.this,VerifyPhoneActivity.class);
                intent.putExtra("PhoneNumber",phoneNumber);
                startActivity(intent);

            }
        });





    }
}
