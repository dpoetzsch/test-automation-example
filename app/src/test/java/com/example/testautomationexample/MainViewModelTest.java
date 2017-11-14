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

    @Test
    public void setInputText_setsOutputTextToTranslationOfLastEnteredInput() throws Exception {
        // this translate mock answers in the wrong order, something that might happen in a real
        // world with a real network connection
        MainViewModel subject = new MainViewModel(new MyTranslateService() {
            String textBackup = null;
            GoogleTranslationService.RequestHandler handlerBackup = null;

            @Override
            public void request(String text, GoogleTranslationService.RequestHandler handler) {
                if (handlerBackup == null) {
                    handlerBackup = handler;
                    textBackup = text;
                } else {
                    handler.onResponse("TRANSLATED " + text);
                    handlerBackup.onResponse("TRANSLATED " + textBackup);
                }
            }
        });

        subject.setInputText("Hallo");
        subject.setInputText("Welt");

        assertEquals(subject.getOutputText(), "In English: TRANSLATED Welt");
    }
}