package com.example.prateekpande.todolistapp;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskFragment.OnFragmentInteractionListener{

    EditText editTxtAddTask;
    List<String> tasksList;
    ArrayAdapter arrayAdapter;
    ListView listTaskView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasksList = new ArrayList<>();

        // retrieve view ids
        editTxtAddTask = (EditText) findViewById(R.id.addTaskEditTask);
        listTaskView = (ListView) findViewById(R.id.listViewTasks);

        //register adapter
        addArrayAdapterToListView();

        //add task to list
        addNewTask();
    }

    /**
     * This method configures adapter with
     * the list view.
     */
    public void addArrayAdapterToListView(){

        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,tasksList);
        listTaskView.setAdapter(arrayAdapter);
    }

    /**
     * This method is responsible for add new tasks
     * to the list.
     */
    public void addNewTask() {

        editTxtAddTask.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
                    tasksList.add(0,editTxtAddTask.getText().toString());
                    arrayAdapter.notifyDataSetChanged();
                    refreshFragment();
                    editTxtAddTask.setText("");
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * This method is responsible for reloading
     * the fragment.
     */
    public void refreshFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.addTaskFragment,new TaskFragment());
        transaction.commit();
    }

    @Override
    public void onTaskFragmentInteraction(Uri uri) {
        //TODO
    }
}
