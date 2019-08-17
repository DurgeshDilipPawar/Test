package choicetechlab.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class MultiViewItemsActivity extends AppCompatActivity {
   private ArrayList<Model> list= new ArrayList();
    private  int images[]=  {R.drawable.sample_image_one,R.drawable.sample_image_two,R.drawable.sample_image_three,R.drawable.sample_image_four};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
/*To Inflate item 10 times adding 40 items with random TYPES*/
        for (int i=0;i<40;i++){
            Model model=new Model();
            model.setText(getResources().getString(R.string.sample_text)+"\t"+i);
            model.setImage(images[getRandomNumberInRange(0,3)]);
            model.setType(getRandomNumberInRange(0,3));
            list.add(model);
        }

        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list,this);
        @SuppressLint("WrongConstant") LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        RecyclerView mRecyclerView =  findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

    /**
     *
     * @param min min start value
     * @param max mzx value
     * @return random int value
     */
    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
