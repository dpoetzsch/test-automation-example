package com.example.externalservice;

import android.os.Handler;

import java.util.Random;

/**
 * Created on 11/14/17.
 *
 * @author David Poetzsch-Heffter
 */
public class GoogleTranslationService {
    private static final Random random = new Random();

    public static void request(final String newText, final RequestHandler handler) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.onResponse(String.format("Uh, I don't remember this from class: '%s'", newText));
            }
        }, random.nextInt(4000));
    }

    public interface RequestHandler {
        void onResponse(String response);
    }
}
