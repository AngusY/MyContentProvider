package com.wearable.mycontentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addRecord(View view) {
        EditText nameET = (EditText) findViewById(R.id.nameET);
        EditText batteryET = (EditText) findViewById(R.id.batteryET);
        ContentValues contentValues = new ContentValues();

        contentValues.put(
                MyContentProvider.NAME,
                nameET.getText().toString());

        contentValues.put(
                MyContentProvider.BATTERY,
                batteryET.getText().toString());

        Uri uri = getContentResolver().insert(
                MyContentProvider.URI,
                contentValues);

        Toast.makeText(MainActivity.this, uri.toString(),
                Toast.LENGTH_LONG).show();
    }

    public void retrieveRecord(View view) {
        String URL = "content://com.wearable.myprovider/devices";
        Uri devices = Uri.parse(URL);
        Cursor c = managedQuery(devices, null, null, null, "name");

        if (c.moveToFirst()) {
            do {
                Toast.makeText(MainActivity.this,
                        c.getString(c.getColumnIndex(MyContentProvider._ID))
                                + ", "
                                +  c.getString(
                                c.getColumnIndex( MyContentProvider.NAME))
                                + ", "
                                + c.getString(
                                c.getColumnIndex( MyContentProvider.BATTERY)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
}
