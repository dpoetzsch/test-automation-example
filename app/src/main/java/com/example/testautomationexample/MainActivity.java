package com.example.testautomationexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.externalservice.GoogleTranslationService;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputField = (EditText) findViewById(R.id.input_field);
        final TextView outputField = (TextView) findViewById(R.id.output_field);

        inputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence newText, int start, int before, int count) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable newText) {
                outputField.setText(R.string.loading);

                GoogleTranslationService.request(newText.toString(), "de", "en", new GoogleTranslationService.RequestHandler() {
                    @Override
                    public void onResponse(String response) {
                        outputField.setText(getString(R.string.in_english, response));
                    }
                });
            }
        });
    }
}
