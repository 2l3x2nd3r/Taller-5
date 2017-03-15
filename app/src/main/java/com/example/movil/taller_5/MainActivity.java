package com.example.movil.taller_5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.movil.taller_5.DAO.DataEntryDAO;
import com.example.movil.taller_5.Model.Note;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    private DataEntryDAO mDataEntryDAO;
    private ListView list;
    private ViewAdapter adapter;
    private Context mycontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mycontext = this;

        mDataEntryDAO = new DataEntryDAO(mycontext);

        list = (ListView) findViewById(R.id.list);

        List<Note> entries = mDataEntryDAO.getAllEntries();
        adapter = new ViewAdapter(mycontext, entries);
        list.setDivider(null);
        list.setAdapter(adapter);
        loadData();
    }

    public void openNote(View view) {
        int index = (Integer) view.getTag();
        Note note = mDataEntryDAO.getDataEntry(index);
        Intent i = new Intent(mycontext, note.class);
        i.putExtra("note", note);

        startActivityForResult(i, index);
    }

    public void createNote(View view) {
        long index = mDataEntryDAO.addDataEntry(new Note());
        Note note = mDataEntryDAO.getDataEntry((int) index);

        Intent i = new Intent(mycontext, note.class);
        i.putExtra("note", note);

        startActivityForResult(i, note.getId());
    }

    private void loadData(){
        List<Note> entries = mDataEntryDAO.getAllEntries();
        adapter.setData(entries);
        ((ViewAdapter) list.getAdapter()).notifyDataSetChanged();
    }

    private Note entry;

    public void deleteNote(View view) {
        int position = (Integer) view.getTag();
        entry = mDataEntryDAO.getDataEntry(position);
        new AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Eliminar Nota")
            .setMessage("¿Estas seguro de eliminar la nota " + entry.getTitle() + "?")
            .setPositiveButton("Si", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mDataEntryDAO.deleteEntry(entry);
                    loadData();
                }

            })
            .setNegativeButton("No", null)
            .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            loadData();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
