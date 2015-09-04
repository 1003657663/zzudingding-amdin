package com.codeevery.login;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.codeevery.myElement.myDialog;
import com.codeevery.zzudingdingAd.R;

import com.codeevery.NetGetPost.GetInternetState;

/*
*说明：
* LoginActivity类主管：
* 1. 获取输入的密码和学号
* 2. 记录是否记住密码或者自动登录
* 3. 启动查询密码的线程登陆教务系统，登陆成功，线程返回数据
* 4. 接收线程返回的数据，判断并写入数据库
* 5. 同时把设置信息，即自动登陆还是记住密码写入系统
* 6. 在启动此线程的时候就判断是自动登录还是记住密码等信息
* 7. 登陆后跳转到指定页面
* 未完成：
* 	登陆成功弹出的Toast优化
*   数据库，转发信息到服务器
*   记住密码和自动登陆状态的识别和写入
*/
public class BookLoginActivity extends Activity implements myDialog.SureButton{
    private TextView title;
    private ConnectivityManager cm;
    private ImageButton backButton;
    private WebView webView;
    private myDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_regist);

        dialog = new myDialog(BookLoginActivity.this);
        webView = (WebView) findViewById(R.id.book_web_view);
        title = (TextView) findViewById(R.id.title);
        title.setText("图书馆预约系统");

        backButton = (ImageButton) findViewById(R.id.back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        GetInternetState getInternetState = new GetInternetState(cm);
        if(getInternetState.isNetConnected()) {
            //支持javascript
            webView.getSettings().setJavaScriptEnabled(true);
            // 设置可以支持缩放
            webView.getSettings().setSupportZoom(true);
            // 设置出现缩放工具
            webView.getSettings().setBuiltInZoomControls(true);
            //扩大比例的缩放
            webView.getSettings().setUseWideViewPort(true);
            //自适应屏幕
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.setWebViewClient(new WebClient());
            webView.loadUrl("http://202.197.191.152/roompre/Default.aspx");
        }else{
            dialog.showDialogWithSure("您的网络有问题哦,检查一下吧","确定");
        }
    }
    class WebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void sureButtonDo() {
        finish();
    }
}
