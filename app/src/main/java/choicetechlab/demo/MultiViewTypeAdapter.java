package choicetechlab.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Durgesh on 17/08/19.
 */
public class MultiViewTypeAdapter extends RecyclerView.Adapter {

    Context mContext;
    int total_types;
    private ArrayList<Model> dataSet;

    /**
     *
     * @param data data set
     * @param context activity context
     */
    public MultiViewTypeAdapter(ArrayList<Model> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case Model.TEXT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_type, parent, false);
                return new TextTypeViewHolder(view);
            case Model.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_view_type, parent, false);
                return new ImageTypeViewHolder(view);
            case Model.RATING_BAR:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_bar_type, parent, false);
                return new RatingBarTypeViewHolder(view);
            case Model.RADIO_BUTTON:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.radio_btn_type, parent, false);
                return new RadioButtonTypeViewHolder(view);
        }
        return null;
    }

    /**
     *  setting up the viewType among 4
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return Model.TEXT_TYPE;
            case 1:
                return Model.IMAGE_TYPE;
            case 2:
                return Model.RATING_BAR;
            case 3:
                return Model.RADIO_BUTTON;
            default:
                return -1;
        }
    }

    /**
     *
     * @param holder holder will be set depends of view type
     * @param listPosition postion of current holder
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final Model object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case Model.TEXT_TYPE:
                    ((TextTypeViewHolder) holder).txtType.setText(dataSet.get(holder.getAdapterPosition()).getText());

                    break;
                case Model.IMAGE_TYPE:
                    ((ImageTypeViewHolder) holder).image.setImageResource(dataSet.get(holder.getAdapterPosition()).getImage());
                    break;
                case Model.RATING_BAR:
                    ((RatingBarTypeViewHolder) holder).ratingBar.setRating(dataSet.get(holder.getAdapterPosition()).getRatings());
                    ((RatingBarTypeViewHolder) holder).ratingBar.setOnRatingBarChangeListener(null);
                    ((RatingBarTypeViewHolder) holder).ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        // Called when the user swipes the RatingBar
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                            dataSet.get(holder.getAdapterPosition()).setRatings(rating);
                        }
                    });

                    break;
                case Model.RADIO_BUTTON:
                    ((RadioButtonTypeViewHolder) holder).radioButton.setChecked(dataSet.get(holder.getAdapterPosition()).getIsChecked());
                    ((RadioButtonTypeViewHolder) holder).radioButton.setText(dataSet.get(holder.getAdapterPosition()).getText());
                    ((RadioButtonTypeViewHolder) holder).radioButton.setOnCheckedChangeListener(null);
                    ((RadioButtonTypeViewHolder) holder).radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView,
                                                     boolean isChecked) {
                            dataSet.get(holder.getAdapterPosition()).setIsChecked(isChecked);
                        }
                    });

                    break;

            }
        }


    }

    /**
     *  this will return total count of 'dataset'
     * @return
     */
    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    /**
     * ViewHolder for Text View Type
     */
    public static class TextTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;


        public TextTypeViewHolder(View itemView) {
            super(itemView);
            this.txtType = itemView.findViewById(R.id.type);
        }
    }

    /**
     * ViewHolder for Image View Type
     */
    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.background);
        }
    }

    /**
     * ViewHolder for RatingBar View Type
     */
    public static class RatingBarTypeViewHolder extends RecyclerView.ViewHolder {
        RatingBar ratingBar;

        public RatingBarTypeViewHolder(View itemView) {
            super(itemView);
            this.ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }

    /**
     * ViewHolder for Radio Button View Type
     */
    public static class RadioButtonTypeViewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButton;

        public RadioButtonTypeViewHolder(View itemView) {
            super(itemView);
            this.radioButton = itemView.findViewById(R.id.radioButton);
        }

    }
}