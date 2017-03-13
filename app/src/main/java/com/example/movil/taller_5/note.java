package com.example.movil.taller_5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.movil.taller_5.DAO.DataEntryDAO;
import com.example.movil.taller_5.Model.Note;

public class note extends AppCompatActivity {
    private DataEntryDAO mDataEntryDAO;
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mDataEntryDAO = new DataEntryDAO(getApplicationContext());

        type = "update";
    }

    private void saveData(View view) {


        if (type == "update"){

            /*entry.set_field1(Integer.valueOf(mEditTextField1
                    .getText().toString()));
            entry.set_field2(Integer.valueOf(mEditTextField2
                    .getText().toString()));
            mDataEntryDAO.updateEntry(entry);

            mEditTextField1.setText("");
            mEditTextField2.setText("");*/

        } else {
            Note entry = new Note("Hola", "Mundo");
            mDataEntryDAO.addDataEntry(entry);
        }
    }
}
