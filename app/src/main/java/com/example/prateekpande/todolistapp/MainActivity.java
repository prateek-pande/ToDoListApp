package com.example.prateekpande.todolistapp;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements TaskFragment.OnFragmentInteractionListener,CompletedTaskFragment.OnFragmentInteractionListener{

    EditText editTxtAddTask;
    TaskFragment taskFragment;
    CompletedTaskFragment completedTaskFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // retrieve view ids
        editTxtAddTask = (EditText) findViewById(R.id.addTaskEditTask);
        taskFragment =  (TaskFragment) getSupportFragmentManager().findFragmentById(R.id.addTaskFragment);

        //add task to list
        addNewTask();
    }

    /**
     * This method is responsible for calling appropriate
     * method to add new task to the list.
     */
    public void addNewTask() {

        editTxtAddTask.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
                    taskFragment.addTaskToList(editTxtAddTask.getText().toString());
                    refreshFragment(taskFragment);
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
    public void refreshFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.addTaskFragment,fragment);
        transaction.commit();
    }

    @Override
    public void onTaskFragmentInteraction(String task) {

    }

    @Override
    public void onCompletedTaskFragmentInteraction(Uri uri) {

    }
}