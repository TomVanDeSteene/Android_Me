package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom Van de Steene on 07/17/2017.
 */

public class BodyPartFragment extends Fragment {

    //Final strings to store state information about the list of images and list index
    private static final String IMAGE_ID_LIST = "image_ids";
    private static final String LIST_INDEX = "list_index";

    //Tag for logging
    private  static  final String TAG = "BodyPartFragment";

    //Variables to store a list of imageResources and the index of the image that the fragment displays
    private List<Integer> mImageIds;
    private int mListIndex;

    //empty constructor
    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        //Get a reference to the ImageView in the fragment layout
        final ImageView imageView = (ImageView)rootView.findViewById(R.id.body_part_image_view);

        if (mImageIds != null){
            //set the image resource to the list item at the stored index
            imageView.setImageResource(mImageIds.get(mListIndex));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //increment position as long as the index remains <= the size of the image id's list
                    if (mListIndex <= mImageIds.size()-1){
                        mListIndex++;
                    }else{
                        //the end of list index is reached so return to beginning index
                        mListIndex = 0;
                    }
                    //set the image resource to the new list item
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        }else{
            // Log a message saying the id list is null
            Log.v(TAG, "This fragment has a null list of image id's");
        }
        return rootView;
    }

    //setters
    public void setImageIds(List<Integer> imageIds) {
        mImageIds = imageIds;
    }

    public void setListIndex(int index) {
        mListIndex = index;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {

        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mListIndex);
    }
}
