package com.example.movil.taller_5;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.movil.taller_5.DAO.DataEntryDAO;
import com.example.movil.taller_5.Model.Note;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    private DataEntryDAO mDataEntryDAO;
    private ListView list;
    private ViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note = new Note("hola", "mundo");
                Toast.makeText(getApplicationContext(), "fecha: " + note.getDate(), Toast.LENGTH_SHORT).show();
            }
        });

        mDataEntryDAO = new DataEntryDAO(getApplicationContext());

        list = (ListView) findViewById(R.id.list);
        //loadData();
        List<Note> entries = mDataEntryDAO.getAllEntries();

        adapter = new ViewAdapter(getApplicationContext(), entries);
        list.setDivider(null);
        list.setAdapter(adapter);
    }

    public void openNote(View view) {
        System.out.println("ENTRO");

        int position = (Integer) view.getTag();

        System.out.println("posicion: " + position);
    }

    private void loadData(){
        List<Note> entries = mDataEntryDAO.getAllEntries();

        adapter = new ViewAdapter(getApplicationContext(), entries);
        list.setDivider(null);
        list.setAdapter(adapter);
    }

    public void deleteNote(View view) {

        int position = (Integer) view.getTag();

        System.out.println("posicion: "+position);

        Note entry = mDataEntryDAO.getAllEntries().get(position);

        System.out.println(entry.getId());

        mDataEntryDAO.deleteEntry(entry);
        loadData();
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
