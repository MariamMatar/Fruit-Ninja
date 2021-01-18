package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Fruits {
	
	//public abstract boolean NotSliced(boolean notsliced);
	public abstract void slice(Group group,double startX, double startY);
    public abstract void Animation(ImageView fruitHalf1, ImageView fruitHalf2);
    //public abstract void Sound();
    public abstract void createSlashTrace(double startX, double startY, double endX, double endY, Group group);
    public abstract void appearFruit(Group group);
    

}