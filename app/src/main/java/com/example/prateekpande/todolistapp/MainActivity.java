package com.example.prateekpande.todolistapp;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TaskFragment.OnFragmentInteractionListener,CompletedTaskFragment.OnFragmentInteractionListener{

    private EditText editTxtAddTask;
    private TaskFragment taskFragment;
    private MenuItem deleteBtn;
    private CompletedTaskFragment completedTaskFragment;
    private int taskAddPosition = 0;
    private int taskDeletePosition;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        deleteBtn = menu.findItem(R.id.deleteBtn);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.deleteBtn :{
                taskFragment.updateCompletedTaskList(taskDeletePosition);
                deleteBtn.setVisible(false);
            }
        }
        return super.onOptionsItemSelected(item);
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
                    taskFragment.addTaskToList(editTxtAddTask.getText().toString(), taskAddPosition);
                    editTxtAddTask.setText("");
                    taskAddPosition=0;
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * This method is responsible for modifying
     * existing fragment.
     */
    public void modifyFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.addTaskFragment, fragment);
        transaction.commit();
    }

    @Override
    public void onTaskFragmentEditTaskInteraction(String task,int position) {
        editTxtAddTask.setText(task);
        taskAddPosition = position;
//        replaceFragment(taskFragment);
    }

    @Override
    public void onTaskFragmentDeleteTaskInteraction(int position) {
        deleteBtn.setVisible(true);
        taskDeletePosition = position;
    }

    @Override
    public void onCompletedTaskFragmentInteraction(Uri uri) {

    }
}