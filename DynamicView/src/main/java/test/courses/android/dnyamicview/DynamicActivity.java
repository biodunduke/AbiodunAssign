package test.courses.android.dnyamicview;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DynamicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);

        TextView contactUs = new TextView(this);
        contactUs.setText(R.string.name);
        contactUs.setTextSize(25);
        contactUs.setId(R.id.tv1);

        TextView myName = new TextView(this);
        myName.setText(R.string.myName);
        myName.setTextSize(30);
        myName.setId(R.id.name1);

        ImageView image1 = new ImageView(this);
        image1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_dominos));

// Code used for RelativeLayout
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        text1.setLayoutParams(params);

// For Constraint Layout we use the code below
//        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
//                ConstraintLayout.LayoutParams.WRAP_CONTENT);

    //    text1.setLayoutParams(params);
    //    image1.setLayoutParams(params);


        ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.View1);
        constraintLayout.addView(contactUs);
        constraintLayout.addView(image1);
        constraintLayout.addView(myName); //Added this to show my name

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        //Below code adds constraints to textview widget. One constraint aligns the view to
        // the top side of the parent and second one make it align to right side of the parent with
        // constraint margin 10.

        constraintSet.connect(contactUs.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 18);
        // to center in the middle
        constraintSet.connect(contactUs.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 18);
        constraintSet.connect(contactUs.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 18);
     //   constraintSet.setHorizontalBias(contactUs.getId(), 0.3f);
        constraintSet.applyTo(constraintLayout);

        // setting the location for the image using constraint
        constraintSet.connect(image1.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM, 150);
        // to center in the middle
        constraintSet.connect(image1.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 10);
        constraintSet.connect(image1.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 10);
      //  constraintSet.setHorizontalBias(image1.getId(), 0.5f);
        constraintSet.applyTo(constraintLayout);

        constraintSet.connect(myName.getId(),ConstraintSet.TOP,constraintLayout.getId(),ConstraintSet.BOTTOM,80);
        constraintSet.connect(myName.getId(),ConstraintSet.LEFT,constraintLayout.getId(), ConstraintSet.LEFT, 18);
        constraintSet.connect(myName.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 18);
        //constraintSet.setHorizontalBias(contactUs.getId(), 0.5f);
        constraintSet.applyTo(constraintLayout);


        // fix the orientation to portrait only
         setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



    }
}
