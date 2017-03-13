package com.example.movil.taller_5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.movil.taller_5.Model.Note;

import java.util.Collections;
import java.util.List;

/**
 * Created by movil on 3/13/17.
 */

public class ViewAdapter extends BaseAdapter {

    private Context context;
    private List<Note> data = Collections.emptyList();

    public ViewAdapter(Context context, List<Note> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Note note = data.get(position);

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.row, null);
        }

        TextView tvRow = (TextView) view.findViewById(R.id.tvRow);
        TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
        Button btn = (Button) view.findViewById(R.id.btnRow);
        CardView cardRow = (CardView) view.findViewById(R.id.cardRow);

        tvRow.setText(note.getTitle());
        tvDate.setText(note.getDate());
        cardRow.setTag(note.getId());
        btn.setTag(note.getId());

        return view;
    }
}
