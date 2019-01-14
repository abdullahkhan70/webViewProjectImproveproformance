package com.example.abdullahkhan.webviewproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    ImageView imageView;
    ProgressBar progressBar;
    TextView textView;
    String error;
    Intent intent;
    int counter = 0;
    NetworkInfo info;

    Toolbar toolbar;
    ConnectivityManager connectivityManager;
    SwipeRefreshLayout swipeRefreshLayout;

//    public MainActivity(){}
//    public void getMainActivity(ImageView imageView,ProgressBar progressBar,TextView textView){
//        this.imageView = imageView;
//        this.progressBar = progressBar;
//        this.textView = textView;
//    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        counter++;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Bundle bundle = getIntent().getExtras();

//        if(bundle!=null){
//            int image = bundle.getInt("image");
//            int progress = bundle.getInt("progressbars");
//        }

        webView = (WebView) findViewById(R.id.webView);

        imageView = (ImageView) findViewById(R.id.imageView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);







//        progressBar = (ProgressBar) fnnindViewById(R.id.progress);
//        textView = (TextView) findViewById(R.id.errorMessage);
//        textView.setVisibility(View.INVISIBLE);
        //customViewClient customViewClient = new customViewClient(getApplicationContext());





        final WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDisplayZoomControls(true);

        webSettings.setAppCacheEnabled(true);

        //Improve WebView Proformance...

        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSaveFormData(true);
        webSettings.setSavePassword(true);
        webSettings.setEnableSmoothTransition(true);


        webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webView.loadUrl("http://www.doktorchain.com");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (Uri.parse(url).getHost().equals("google.com")) {
                    return true;
                }

                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //toolbar.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                imageView.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
                //toolbar.setVisibility(View.VISIBLE);
                // hide element by class name!!
//               webView.loadUrl("javascript:(function() { " +
//                        "document.getElementsByClassName('app-display')[0].style.display='none'; })()");
                // hide element by id!!
//                webView.loadUrl("javascript:(function() { " +
//                        "document.getElementById('app-display').style.display='none';})()");


                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                //webView.loadUrl("file:///android_asset/error_message.html");
                super.onReceivedError(view, request, error);
            }
        });



//            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
//                Toast.makeText(getApplicationContext(), "Your Connection is WIFI", Toast.LENGTH_LONG).show();
//            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
//                Toast.makeText(getApplicationContext(), "Your Connection is Mobile", Toast.LENGTH_LONG).show();
//            }


        if (info != null && info.isConnected()) {
            Toast.makeText(getApplicationContext(), "Your Connection is connected", Toast.LENGTH_LONG).show();
        }


    }


}
