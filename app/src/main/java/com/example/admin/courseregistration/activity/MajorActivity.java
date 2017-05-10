package com.example.admin.courseregistration.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.courseregistration.R;
import com.example.admin.courseregistration.adapter.CartListAdapter;
import com.example.admin.courseregistration.adapter.SubjectListAdapter;
import com.example.admin.courseregistration.model.MajorModel;
import com.example.admin.courseregistration.model.SubjectModel;
import com.example.admin.courseregistration.service.DBHelper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by trycatch on 2017. 3. 10..
 */

public class MajorActivity extends AppCompatActivity {
    private Context mContext;
    private ArrayList<MajorModel> majorArray;
    private ArrayList<SubjectModel> subjectArray;
    private SubjectListAdapter subjectListAdapter;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.cart_button)
    Button cart_button;

    @BindView(R.id.major_spinner)
    Spinner major;

    @BindView(R.id.subject_list)
    ListView subject_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);

        ButterKnife.bind(this);

        mContext = getApplicationContext();

        init();
    }

    public void init(){
        setMajor();

        title.setText("강의목록");
        cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.dialog_cart, null);
                ListView dialog_subject_list = (ListView) view.findViewById(R.id.dialog_subject_list);
                Button dialog_close_button = (Button) view.findViewById(R.id.dialog_close_button);
                CartListAdapter cartListAdapter = new CartListAdapter(getApplicationContext(), false);
                dialog_subject_list.setAdapter(cartListAdapter);

                final AlertDialog builder = new AlertDialog.Builder(MajorActivity.this)
                        .setView(view)
                        .create();

                builder.show();

                dialog_close_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.cancel();
                    }
                });
            }
        });

        subjectListAdapter = new SubjectListAdapter(getApplicationContext(), new ArrayList<SubjectModel>());
        subject_list.setAdapter(subjectListAdapter);
    }

    public void setMajor(){
        new Major().execute();
    }

    public void setSubjectList(String value){
        new Subject().execute(value);
        subject_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
                View view = getLayoutInflater().inflate(R.layout.dialog_add_cart, null);
                Button add_button = (Button) view.findViewById(R.id.add_button);
                Button cancel_button = (Button) view.findViewById(R.id.cancel_button);

                final AlertDialog builder = new AlertDialog.Builder(MajorActivity.this)
                        .setView(view)
                        .create();

                builder.show();

                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBHelper.getInstance(getApplicationContext()).insert(subjectArray.get(position));
                        builder.cancel();
                    }
                });

                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.cancel();
                    }
                });
            }
        });
    }

    class Major extends AsyncTask<Void, Void, Void> {
        ArrayList<String> majorNameArray;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect("http://cyber.hansung.ac.kr/jsp/sugang/h_sugang_inwon_s01_h.jsp").timeout(0).get();
                Elements major = document.select("select option");

                majorArray = new ArrayList<>();
                majorNameArray = new ArrayList<>();
                for(Element element : major){
                    majorArray.add(new MajorModel(element.text(), element.attr("value")));
                    majorNameArray.add(element.text());
                }

                document = null;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void mVoid) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MajorActivity.this, android.R.layout.simple_spinner_dropdown_item, majorNameArray);
            major.setAdapter(adapter);
            major.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    setSubjectList(majorArray.get(position).getValue());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    class Subject extends AsyncTask<String, Void, Void> {
        private boolean FLAG_START = false;
        private String value;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            value = params[0];
            try {
                Document document = Jsoup.connect("http://cyber.hansung.ac.kr/jsp/sugang/h_sugang_inwon_s01_h.jsp").data("junkong", value).timeout(0).get();
                Elements course = document.select("table tr td");

                subjectArray = new ArrayList<>();
                for(int i = 0; i < course.size(); i++){
                    if(FLAG_START){
                        subjectArray.add(
                                new SubjectModel(
                                        course.get(i++).text().trim(),
                                        course.get(i++).text().trim(),
                                        course.get(i++).text().trim(),
                                        course.get(i++).text().trim(),
                                        course.get(i++).text().trim(),
                                        course.get(i++).text().trim(),
                                        course.get(i++).text().trim(),
                                        course.get(i++).text().trim(),
                                        course.get(i).text().trim()
                                )
                        );
                    }
                    else if(course.get(i).text().equals("비고")){
                        FLAG_START = true;
                    }
                }

                document = null;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void mVoid) {
            subjectListAdapter.setSubjectArray(subjectArray);
        }
    }
}

