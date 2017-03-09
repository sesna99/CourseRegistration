package com.example.admin.courseregistration;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        new Login().execute();
    }

    class Login extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                String url ="https://info.hansung.ac.kr/servlet/s_gong.gong_login_ssl";
                Connection.Response res = Jsoup.connect(url).data("id", "1771321").data("passwd", "!kim5656").timeout(0).method(Connection.Method.POST).execute();
                Document document = res.parse();
                Map<String, String> cookie = res.cookies();

                Log.i("cookieSize", cookie.size() + "");
                Log.i("cookie", cookie.toString());

                Document document2 = Jsoup.connect("http://info.hansung.ac.kr/h_dae/dae_main.html").cookies(cookie).timeout(0).get();
                Elements content = document2.select("li");

                Log.i("size", content.size() + "");
                Log.e("html", document.html());

                document = null;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void mVoid) {

        }
    }
}
