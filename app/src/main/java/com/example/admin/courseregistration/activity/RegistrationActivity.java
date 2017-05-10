package com.example.admin.courseregistration.activity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.admin.courseregistration.R;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by trycatch on 2017. 5. 8..
 */

public class RegistrationActivity extends AppCompatActivity {
    @BindView(R.id.registration_button)
    Button registration_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ButterKnife.bind(this);

        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Registration().execute();
            }
        });
    }

    class Registration extends AsyncTask<Void, Void, Void> {
        private Document document, document2;
        private Connection.Response res;
        private Map<String, String> cookie;
        private String url, message;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                url ="https://info.hansung.ac.kr/servlet/s_gong.gong_login_ssl";
                res = Jsoup.connect(url).data("id", "1771321").data("passwd", "!kim5656").timeout(0).method(Connection.Method.POST).execute();
                document = res.parse();
                cookie = res.cookies();

                document2 = Jsoup.connect("http://info.hansung.ac.kr/jsp/sugang/h_sugang_sincheong_main.jsp").cookies(cookie).timeout(0).get();

                message = document2.head().html().split("\"")[1];

                document = null;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void mVoid) {
            AlertDialog dialog = new AlertDialog.Builder(RegistrationActivity.this)
                    .setMessage(message.replace("\\n", "\n"))
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).create();
            dialog.show();
        }
    }
}
