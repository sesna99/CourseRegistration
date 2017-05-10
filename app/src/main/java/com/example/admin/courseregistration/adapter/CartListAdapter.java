package com.example.admin.courseregistration.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.courseregistration.R;
import com.example.admin.courseregistration.model.SubjectModel;
import com.example.admin.courseregistration.service.DBHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by trycatch on 2017. 3. 12..
 */

public class CartListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<SubjectModel> subjectArray;
    private boolean isRegistration;

    public CartListAdapter(Context mContext, boolean isRegistration) {
        this.mContext = mContext;
        this.subjectArray = DBHelper.getInstance(mContext).getResult();
        this.isRegistration = isRegistration;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.dialog_subject_list_item, null);
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

        if(isRegistration) {
            holder.function_button.setText("신청");
        }
        else{
            holder.function_button.setText("삭제");
            holder.function_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper.getInstance(mContext).delete(subjectArray.get(position));
                    subjectArray = DBHelper.getInstance(mContext).getResult();
                    notifyDataSetChanged();
                }
            });
        }

        return convertView;
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

        @BindView(R.id.function_button)
        Button function_button;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
