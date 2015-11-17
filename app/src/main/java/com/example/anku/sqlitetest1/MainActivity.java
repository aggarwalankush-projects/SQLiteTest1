package com.example.anku.sqlitetest1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
        final String DROP_TABLE="DROP TABLE IF EXISTS contacts";
        final String CREATE_TABLE="CREATE TABLE contacts(name TEXT, phone INTEGER, email TEXt)";
        final String insert1="INSERT INTO contacts VALUES('ankush', 656564, 'ankush@gmail.com')";
        final String insert2="INSERT INTO contacts VALUES('ankit', 374746, 'ankit@gmail.com')";
        final String query = "Select * from contacts";
        sqLiteDatabase.execSQL(DROP_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(insert1);
        sqLiteDatabase.execSQL(insert2);
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                int phone = cursor.getInt(1);
                String email = cursor.getString(2);
                Toast.makeText(getBaseContext(), name + " - " + phone + " - " + email, Toast.LENGTH_LONG).show();
            } while (cursor.moveToNext());
        }else {
            Toast.makeText(getBaseContext(), "Error retrieving data", Toast.LENGTH_LONG).show();
        }
        cursor.close();
        sqLiteDatabase.close();

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
