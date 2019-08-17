package choicetechlab.demo;


import java.io.Serializable;

/**
 * Created by Durgesh on 17/08/19.
 */
public class Model implements Serializable {

    public static final int TEXT_TYPE = 0;
    public static final int IMAGE_TYPE = 1;
    public static final int RATING_BAR = 2;
    public static final int RADIO_BUTTON = 3;

    public int type;
    public int image;
    public float ratings;
    public String text;
    public boolean isChecked = false;

    public Model(int type, String text, int image, float ratings) {
        this.type = type;
        this.text = text;
        this.image = image;
        this.ratings = ratings;

    }

    public Model() {

    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }


}