package com.example.prateekpande.todolistapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TaskFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TaskFragment extends Fragment implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    private OnFragmentInteractionListener mListener;
    private ArrayAdapter arrayAdapter;
    private ListView listTaskView;
    private List<String> tasksList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tasksList = new ArrayList<>();
        listTaskView = (ListView) getActivity().findViewById(R.id.listViewTasks);
        setAdapter();

        // register onItemClick listener
        listTaskView.setOnItemClickListener(this);
        //register onItemLongClick listener
        listTaskView.setOnItemLongClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * This method configures adapter with
     * the list view.
     */
    public void setAdapter(){
        arrayAdapter = new ArrayAdapter(getActivity(),R.layout.support_simple_spinner_dropdown_item,tasksList);
        listTaskView.setAdapter(arrayAdapter);
    }

    /**
     * This method adds task to
     * fragment(List View)
     * @param task
     */
    public void addTaskToList(String task,int position){

        tasksList.add(position, task);
        arrayAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String completedTask = listTaskView.getItemAtPosition(position).toString();
        updateCompletedTaskList(position);
        mListener.onTaskFragmentEditTaskInteraction(completedTask, position);
    }

    /**
     * This method removes completed tasks
     * from existing list.
     */
    public void updateCompletedTaskList(int position) {
        tasksList.remove(position);
        listTaskView.getChildAt(position).setEnabled(true);
        arrayAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        listTaskView.getChildAt(position).setEnabled(false);

        mListener.onTaskFragmentDeleteTaskInteraction(position);
//        parent.getChildAt(position).setEnabled(true);
        return true;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onTaskFragmentEditTaskInteraction(String task,int position);
        void onTaskFragmentDeleteTaskInteraction(int position);
    }
}