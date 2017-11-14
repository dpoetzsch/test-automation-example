package com.example.testautomationexample;

import com.example.externalservice.GoogleTranslationService;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MainViewModelTest {
    @Test
    public void setInputText_setsOutputTextToTranslation() throws Exception {
        MainViewModel subject = new MainViewModel(new MyTranslateService() {
            @Override
            public void request(String text, GoogleTranslationService.RequestHandler handler) {
                handler.onResponse("TRANSLATED " + text);
            }
        });

        subject.setInputText("Hallo");

        assertEquals(subject.getOutputText(), "In English: TRANSLATED Hallo");
    }
}