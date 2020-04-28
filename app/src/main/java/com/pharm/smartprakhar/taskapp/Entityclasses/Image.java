package com.pharm.smartprakhar.taskapp.Entityclasses;

public class Image {
    public static final String TABLE_NAME = "image";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_IMAGEID = "imageid";
    public static final String COLUMN_PATH="imagepath";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_IMAGEID + " TEXT PRIMARY KEY,"
                    + COLUMN_TIMESTAMP+ " TEXT,"
                    + COLUMN_PATH + " TEXT"
                    + ")";


    String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    String imageid;
    String path;




}
