package com.webview.myapplication;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import java.util.Arrays;
import java.util.List;
public class MainActivity extends Activity { private final int STORAGE_PERMISSION_CODE = 1;
    private WebView mWebView;

    public void blockString() {
        Toast.makeText(this, R.string.blocked_page, Toast.LENGTH_LONG).show();
    }
    public void onBackPressed () {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else if (mWebView.canGoBack()) {
            super.onBackPressed();
        }
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to download files")
                    .setPositiveButton("ok", (dialog, which) -> ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE))
                    .setNegativeButton("cancel", (dialog, which) -> dialog.dismiss())
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    @SuppressLint("SetJavaScriptEnabled")
    protected void onCreate(Bundle savedInstanceState) {
        requestStoragePermission();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = findViewById(R.id.activity_main_webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new HelloWebViewClient());
        mWebView.setDownloadListener((url, userAgent, contentDisposition, mimeType, contentLength) -> {
            Uri source = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(source);
            String cookies = CookieManager.getInstance().getCookie(url);
            request.addRequestHeader("cookie", cookies);
            request.addRequestHeader("User-Agent", userAgent);
            request.setDescription("Downloading File...");
            request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimeType));
            DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            dm.enqueue(request);
            Toast.makeText(this, R.string.downloading, Toast.LENGTH_LONG).show();
        });
        mWebView.loadUrl("https://ashivered.github.io/listofurls.html"); //Replace The Link Here
    }

    private class HelloWebViewClient extends WebViewClient {
        List<String> whiteHosts = Arrays.asList("www.ashivered.github.io", "www.hamichlol.org.il", "www.mitmachim.top", "www.miktzav.com", "www.prog.co.il", "www.forum.netfree.link", "www.tchumim.com", "www.send.magicode.me", "www.m.moovitapp.com", "www.dirshu.co.il", "www.bneidavid.org", "www.besodh.com", "www.media-line.co.il", "www.kolhalashon.com", "www.daf-yomi.com", "www.leumi.co.il", "www.rail.co.il", "www.bankleumi.co.il", "www.bank-yahav.co.il", "www.kosharot.co.il", "www.fibi.co.il", "www.yahav.co.il", "www.kolhashiurim.com", "www.discountbank.co.il", "www.telebank.co.il", "www.bankmassad.co.il", "www.ps.btl.gov.il", "www.btl.gov.il", "www.bankhapoalim.co.il", "www.gstatic.com", "www.pagi.co.il", "www.mishnatyosef.org", "www.hashefa.co.il", "www.steinsaltz-center.org.il", "www.bankhadoar.co.il", "www.mizrahi-tefahot.co.il", "www.netsparkmobile.com", "www.bankotsar.co.il", "www.mercantile.co.il", "www.nosachteiman.co.il", "www.nteiman.co.il", "www.michlala.edu", "www.plp.org.il", "www.tzurtech.auth0.com", "www.noamparty.org.il", "www.noam.org.il", "www.api.w.org", "www.googletagmanager.com", "www.cardcom.solutions", "www.shaveihevron.org", "www.storage.googleapis.com", "www.call2all.co.il", "www.yemot.co.il", "www.translate.google.co.il", "www.translate.google.com", "www.otzar.org", "www.yhr.org.il", "www.accounts.google.co.il", "www.media-line.co.il", "www.maccabi4u.co.il", "www.leumit.co.il", "www.meuhedet.co.il", "www.clalit.co.il", "www.maccabi-dent.com", "www.google-analytics.com", "www.creditguard.co.il", "www.dirshu.co.il", "www.yelonmoreh.co.il", "www.he.wikisource.org", "www.he.m.wikisource.org", "www.yga.co.il", "www.hebrewbooks.org", "www.sefaria.org.il", "www.yeshiva.org.il", "www.call2all.co.il", "www.mizrahi-tefahot.co.il", "www.ims.gov.il", "www.weather.israelinfo.co.il", "xn--5dbqmbuy.com", "www.f2.freeivr.co.il", "www.ykr.org.il", "www.strg.ykr.org.il", "www.zionutdatit.org.il", "www.elector.b-elect.com", "www.meirtv.com", "www.otzar-haretz.co.il", "www.meirtv.com", "www.moreshet-maran.com", "www.net-sah.org", "www.e-services.clalit.co.il", "www.hook.integromat.com", "www.contacts.google.com", "www.haravyosefkalner.com", "www.autozoom3125.pythonanywhere.com", "www.online2.leumit.co.il", "www.tovzebateva.webdev.co.il", "www.miluim.idf.il", "www.rab-exams.co.il", "www.dafyomi.co.il", "www.api.buya.co.il", "www.mp3.meirtv.co.il", "www.darchei-horaah.org", "www.mhd.co.il", "www.payboxapp.com", "www.harav.org", "www.app.shiftorganizer.com", "www.files.daf-yomi.com", "www.max.co.il", "www.cal-online.co.il", "www.kore.co.il", "hm-news.co.il", "www.jdn.co.il", "www.bahazit.co.il", "www.my.gov.il", "www.login.gov.il", "ashivered.github.io", "hamichlol.org.il", "mitmachim.top", "miktzav.com", "prog.co.il", "forum.netfree.link", "tchumim.com", "send.magicode.me", "moovitapp.com", "m.moovitapp.com", "dirshu.co.il", "bneidavid.org", "besodh.com", "media-line.co.il", "kolhalashon.com", "daf-yomi.com", "leumi.co.il", "rail.co.il", "bankleumi.co.il", "bank-yahav.co.il", "kosharot.co.il", "fibi.co.il", "yahav.co.il", "kolhashiurim.com", "discountbank.co.il", "telebank.co.il", "bankmassad.co.il", "ps.btl.gov.il", "btl.gov.il", "bankhapoalim.co.il", "gstatic.com", "pagi.co.il", "mishnatyosef.org", "hashefa.co.il", "steinsaltz-center.org.il", "bankhadoar.co.il", "mizrahi-tefahot.co.il", "netsparkmobile.com", "bankotsar.co.il", "mercantile.co.il", "nosachteiman.co.il", "nteiman.co.il", "michlala.edu", "plp.org.il", "tzurtech.auth0.com", "noamparty.org.il", "noam.org.il", "api.w.org", "googletagmanager.com", "cardcom.solutions", "shaveihevron.org", "storage.googleapis.com", "call2all.co.il", "yemot.co.il", "translate.google.co.il", "translate.google.com", "otzar.org", "yhr.org.il", "accounts.google.co.il", "media-line.co.il", "maccabi4u.co.il", "leumit.co.il", "meuhedet.co.il", "clalit.co.il", "maccabi-dent.com", "google-analytics.com", "creditguard.co.il", "dirshu.co.il", "yelonmoreh.co.il", "he.wikisource.org", "he.m.wikisource.org", "yga.co.il", "hebrewbooks.org", "sefaria.org.il", "yeshiva.org.il", "call2all.co.il", "mizrahi-tefahot.co.il", "ims.gov.il", "weather.israelinfo.co.il", "xn--5dbqmbuy.com", "f2.freeivr.co.il", "ykr.org.il", "strg.ykr.org.il", "zionutdatit.org.il", "elector.b-elect.com", "meirtv.com", "otzar-haretz.co.il", "meirtv.com", "moreshet-maran.com", "net-sah.org", "e-services.clalit.co.il", "hook.integromat.com", "contacts.google.com", "haravyosefkalner.com", "autozoom3125.pythonanywhere.com", "online2.leumit.co.il", "tovzebateva.webdev.co.il", "miluim.idf.il", "rab-exams.co.il", "dafyomi.co.il", "api.buya.co.il", "mp3.meirtv.co.il", "darchei-horaah.org", "mhd.co.il", "payboxapp.com", "harav.org", "app.shiftorganizer.com", "files.daf-yomi.com", "max.co.il", "cal-online.co.il", "kore.co.il", "hm-news.co.il", "jdn.co.il", "bahazit.co.il", "my.gov.il", "login.gov.il");

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            String host = Uri.parse(url).getHost();
            if (whiteHosts.contains(host)) {
                return false;
            } else {
                blockString();
                return true;
            }
        }
    }
}