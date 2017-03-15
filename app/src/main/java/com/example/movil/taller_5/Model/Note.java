package com.example.movil.taller_5.Model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by movil on 3/13/17.
 */
public class Note implements Serializable {
    private int id;
    private String title;
    private String content;
    private String date;

    public Note() {
    }

    public Note(int id, String title, String content, String date) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.title = title;
    }

    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        setDate();
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        setDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        this.date = df.format(date);
    }

    public void setDate(String date) {
        this.date = date;
    }

}
