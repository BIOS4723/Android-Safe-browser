# Android-Whitelist-Browser
Webview client with a whitelist of allowed URLs.
And Download Content Using Phone's Internal Downloader.

## Change default URL 
Open the ```app/src/main/java/com/webview/app/MainActivity.java``` file and replace `https://ashivered.github.io/listofurls.html` on line **81** with your website
```json
mWebView.loadUrl("https://ashivered.github.io/listofurls.html");
```


the default URL has been loadded when you open the app.

## Change URLs in the Whitelist
Open the ```app/src/main/java/com/webview/app/MainActivity.java``` file and replace the URLs in line **85** with your URLs.

## Change it to Blacklist Browser (List of disallowed URLs)
Reverse the condition (in lines 87-95):
```json
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            String host = Uri.parse(url).getHost();
            if (whiteHosts.contains(host)) {
                return false;
            } else {
                blockString();
                return true;
            }
```
to

```json
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            String host = Uri.parse(url).getHost();
            if (whiteHosts.contains(host)) {
                blockString();
                return true;
            } else {
                return false;
            }
```
