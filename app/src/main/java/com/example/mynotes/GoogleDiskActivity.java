package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GoogleDiskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_disk);

        WebView googleV = findViewById(R.id.googleView);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.googleD);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.googleD:
                        return true;
                    case R.id.notes_list:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.yandexD:
                        startActivity(new Intent(getApplicationContext(), YandexDiskActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        // add your link here
        googleV.loadUrl("https://www.drive.google.com/");
        googleV.setWebViewClient(new Client());
        WebSettings ws = googleV.getSettings();

        // Enabling javascript
        ws.setJavaScriptEnabled(true);
        googleV.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        googleV.clearCache(true);
        googleV.clearHistory();

        // download manager is a service that can be used to handle downloads
        googleV.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String s1, String s2, String s3, long l) {
                DownloadManager.Request req = new DownloadManager.Request(Uri.parse(url));
                req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(req);
                Toast.makeText(GoogleDiskActivity.this, "Загрузка....", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class Client extends WebViewClient {
        // on page started load start loading the url
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }


        // load the url of our drive
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
            // if stop loading
            try {
                webView.stopLoading();
            } catch (Exception e) {
            }

            if (webView.canGoBack()) {
                webView.goBack();
            }

            // if loaded blank then show error
            // to check internet connection using
            // alert dialog
            webView.loadUrl("about:blank");
            AlertDialog alertDialog = new AlertDialog.Builder(GoogleDiskActivity.this).create();
            alertDialog.setTitle("Ошибка");
            alertDialog.setMessage("Проверьте ваше интернет соединения и Попробуйте снова.");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Попробуйте снова", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    startActivity(getIntent());
                }
            });

            alertDialog.show();
            super.onReceivedError(webView, errorCode, description, failingUrl);
        }
    }
}
