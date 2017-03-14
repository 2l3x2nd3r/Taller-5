package com.example.movil.taller_5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.movil.taller_5.DAO.DataEntryDAO;
import com.example.movil.taller_5.Model.Note;

public class note extends AppCompatActivity {
    private DataEntryDAO mDataEntryDAO;
    private Note note;
    private EditText etTitle;
    private EditText etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mDataEntryDAO = new DataEntryDAO(getApplicationContext());
        etTitle = (EditText) findViewById(R.id.etTitle);
        etContent = (EditText) findViewById(R.id.etContent);

        Intent i = getIntent();
        note = (Note) i.getSerializableExtra("note");

        etTitle.setText(note.getTitle());
        etContent.setText(note.getContent());
    }

    @Override
    public void onBackPressed() {
        saveData(null);
    }

    private void saveData(View view) {
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        if(!title.isEmpty() && !content.isEmpty()){
            Note entry = new Note(title, content);
            mDataEntryDAO.updateEntry(entry);
            Intent i = new Intent();
            setResult(Activity.RESULT_OK, i);
            finish();
        }else{
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Estimado usuario")
                    .setMessage("Debe poner minimo el titulo")
                    .setNeutralButton("Ok", null)
                    .show();
        }
    }
}
