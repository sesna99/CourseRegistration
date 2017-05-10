package com.example.admin.courseregistration.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.courseregistration.R;
import com.example.admin.courseregistration.model.SubjectModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by trycatch on 2017. 3. 10..
 */

public class SubjectListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<SubjectModel> subjectArray;

    public SubjectListAdapter(Context mContext, ArrayList<SubjectModel> subjectArray) {
        this.mContext = mContext;
        this.subjectArray = subjectArray;
    }

    @Override
    public int getCount() {
        return subjectArray.size();
    }

    @Override
    public Object getItem(int position) {
        return subjectArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.subject_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.divideClass.setText(subjectArray.get(position).getDivideClass());
        holder.name.setText(subjectArray.get(position).getName());
        holder.professor.setText(subjectArray.get(position).getProfessor());
        holder.grade.setText(subjectArray.get(position).getGrade());
        holder.credit.setText(subjectArray.get(position).getCredit());
        holder.division.setText(subjectArray.get(position).getDivision());

        return convertView;
    }

    public void setSubjectArray(ArrayList<SubjectModel> subjectArray){
        this.subjectArray = subjectArray;
        notifyDataSetChanged();
    }

    class ViewHolder{
        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.divideClass)
        TextView divideClass;

        @BindView(R.id.professor)
        TextView professor;

        @BindView(R.id.grade)
        TextView grade;

        @BindView(R.id.credit)
        TextView credit;

        @BindView(R.id.division)
        TextView division;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
