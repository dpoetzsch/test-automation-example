package com.example.testautomationexample;

import com.example.externalservice.GoogleTranslationService;

/**
 * Created on 11/14/17.
 *
 * @author David Poetzsch-Heffter
 */

class MyTranslateService {
    public void request(String text, GoogleTranslationService.RequestHandler handler) {
        GoogleTranslationService.request(text, handler);
    }
}
