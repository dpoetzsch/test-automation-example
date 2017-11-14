package com.example.testautomationexample;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.externalservice.GoogleTranslationService;

/**
 * Created on 11/14/17.
 *
 * @author David Poetzsch-Heffter
 */

public class MainViewModel extends BaseObservable {
    private String inputText;
    private String outputText = "Enter text to see the translation!";

    private MyTranslateService translationService;

    public MainViewModel(MyTranslateService service) {
        this.translationService = service;
    }

    public void setInputText(final String newText) {
        this.inputText = newText;
        notifyPropertyChanged(com.example.testautomationexample.BR.inputText);

        setOutputText("Loading...");

        this.translationService.request(newText, new GoogleTranslationService.RequestHandler() {
            @Override
            public void onResponse(String response) {
                if (newText.equals(inputText)) {
                    setOutputText("In English: " + response);
                }
            }
        });
    }

    @Bindable
    public String getInputText() { return inputText; }

    public void setOutputText(String newText) {
        this.outputText = newText;
        notifyPropertyChanged(com.example.testautomationexample.BR.outputText);
    }

    @Bindable
    public String getOutputText() { return outputText; }
}
