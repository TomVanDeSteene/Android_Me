package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by Tom Van de Steene on 07/18/2017.
 */

public class MasterListFragment extends Fragment {

    OnImageClickListener mCallback;

    public interface OnImageClickListener{
        void onImageSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //This makes sure that the hostactivity has implemented the callback interface
        //If not it throws an exception
        try {
            mCallback = (OnImageClickListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+ "must implement onImageClickListener");
        }
    }

    //Mandatory empty constructor
    public MasterListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        //Get a reference to the GridView in the fragment_master_list xml layout file
        GridView gridView = (GridView)rootView.findViewById(R.id.images_grid_view);

        //Create the adapter
        //this adapter takes in the context and an ArrayList of all the imageresources to display
        MasterListAdapter mAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        //set the adapter on the GridView
        gridView.setAdapter(mAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                //Trigger the callback method and pass in the positon that was clicked
                mCallback.onImageSelected(position);
            }
        });
        //Return the root view
        return rootView;

    }
}
