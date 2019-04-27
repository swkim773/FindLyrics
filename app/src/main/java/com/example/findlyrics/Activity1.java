package com.example.findlyrics;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class Activity1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                String subject = intent.getStringExtra(Intent.EXTRA_SUBJECT);
                int idx1 = subject.indexOf("'") + 1;
                int idx2 = subject.lastIndexOf("'");
                if (idx1 > 0 && idx1 < idx2) {
                    String title = subject.substring(idx1, idx2);
                    searchLyrics(title);
                }
            }
        }

        new Handler().postDelayed(() -> {
            finish();
        }, 0);
    }

    private void searchLyrics(String title) {
        String query = title + " lyrics";

        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        startActivity(intent);
    }
}
