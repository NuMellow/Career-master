package com.example.career.career;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

class CustomAdaptor extends ArrayAdapter<String>
{
      CustomAdaptor(Context context, String[] questions)
      {
        super(context, R.layout.custom_row ,questions);
      }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater questionInflater = LayoutInflater.from(getContext());
        View customView = questionInflater.inflate(R.layout.custom_row, parent, false);

        String singleQuestionItem = getItem(position);
        TextView customText = (TextView) customView.findViewById(R.id.customText);
        CheckBox checkBox  = (CheckBox) customView.findViewById(R.id.checkBox);

        customText.setText(singleQuestionItem);
        checkBox.setChecked(false);
        return customView;

    }
}
