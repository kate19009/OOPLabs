package com.example.la6;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    private ArrayList<String> names;
    private ArrayAdapter<String> itemsAdapter;
    private ListView nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameList = (ListView) findViewById(R.id.nameList);
        names = new ArrayList<String>();
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);
        nameList.setAdapter(itemsAdapter);
        //nameList.setVisibility(View.VISIBLE);
        Utility.setListViewHeightBasedOnChildren(nameList);
        final EditText etNewName = (EditText) findViewById(R.id.newName);
        etNewName.setHint("Введите Имя");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Список");
        final FloatingActionButton addMan = findViewById(R.id.addMan);
        addMan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                String itemText = etNewName.getText().toString();

                if (etNewName.length() != 0 && addMan.isPressed())
                {
                    itemsAdapter.add(itemText);
                    writeItems();
                    etNewName.setText("");


                    Utility.setListViewHeightBasedOnChildren(nameList);
                }

            }
        });


        nameList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                names.remove(position);
                itemsAdapter.notifyDataSetChanged();
                writeItems();
                Utility.setListViewHeightBasedOnChildren(nameList);
                return true;
            }
        });


    }

    private void readItems()
    {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try
        {
            names = new ArrayList<>(FileUtils.readLines(todoFile));
        }
        catch (IOException e)
        {
            names = new ArrayList<>();
        }
    }

    private void writeItems()
    {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try
        {
            FileUtils.writeLines(todoFile, names);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
