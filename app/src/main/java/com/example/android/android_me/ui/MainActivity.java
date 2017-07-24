package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    //Variables to store the values for the list index of the selected images
    //The default value will be index = 0
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Define the behaviour for onImageSelected
    @Override
    public void onImageSelected(int position) {
        //Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        //bodyPartNumber will be = 0 for the head fragment, 1 for the body and 2 for the leg fragment
        //Dividing by 12 will give us these integers because each list of images has a size of 12
        int bodyPartNumber = position/12;

        //Store the correct list index no matter where in the image list has been clicked
        //This ensures that the value will always be between 0-11
        int listIndex = position - 12*bodyPartNumber;

        switch (bodyPartNumber){
            case 0: headIndex = listIndex;
                break;
            case 1: bodyIndex = listIndex;
                break;
            case 2: legIndex = listIndex;
                break;
            default: break;
        }

        //Put this information in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
        Bundle b = new Bundle();
        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legIndex", legIndex);

        //Attach the Bundle to an intent
        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        //The next button launches a new AndroidMeActivity
        Button nextButton = (Button)findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
