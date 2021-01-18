package view;

import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class bomb2 {
	
	private ImageView imgBomb;
       
     public bomb2() {
    	 
     }
	public  bomb2(Image image, double x, double y, Group group) {
       
    	 this.imgBomb = new ImageView(image);
         this.imgBomb.setX(x);
         this.imgBomb.setY(y);
         this.imgBomb.setFitWidth(82);
         this.imgBomb.setFitHeight(89);
         
         
         group.getChildren().add(this.imgBomb);		
	}

             public void playCutSound()
    {

        AudioClip cut = new AudioClip(this.getClass().getResource("/sounds/cut_bomb.mp3").toExternalForm());

        cut.setCycleCount(1);
        cut.play();
    }

	public void createSlashTrace(double startX, double startY, double endX, double endY, Group group) {
        double slope = (endY - startY) / (endX - startX);
        
       
        double angle = Math.toDegrees(Math.atan(slope));
        
        
        ImageView trace = new ImageView("/img/slash_trace.gif");
        
       
        trace.setRotate(45 - angle);
        
       
        trace.setVisible(false);
        
       
        trace.setLayoutX(startX - 28);
        trace.setLayoutY(startY - 25);
        
        
        group.getChildren().add(trace);
        
     
        Timeline showSlash = new Timeline(
                new KeyFrame(Duration.ZERO, e ->
                {
                    trace.setVisible(true);
                }
            ),
                new KeyFrame(Duration.millis(300), e-> 
                {
                    trace.setVisible(false);
                }
            )
        );
        
        playCutSound();
        showSlash.setCycleCount(1);
        showSlash.play();
		
	}

	public void appearBomb2(Group group) {
		Timeline makeAnimation = new Timeline();
		
		  AudioClip cut = new AudioClip(this.getClass().getResource("/sounds/bomb_appear2.mp3").toExternalForm());
     
        cut.setCycleCount(1);
        cut.play();
	      
     makeAnimation.getKeyFrames().add(new KeyFrame( Duration.millis(2460),
             new KeyValue(this.getImgBomb().xProperty(), 800),new KeyValue(this.getImgBomb().yProperty(),700),new KeyValue(this.getImgBomb().rotateProperty(), 400),new KeyValue(getImgBomb().cacheHintProperty(), CacheHint.QUALITY)));
  
     makeAnimation.getKeyFrames().add(new KeyFrame( Duration.millis(1230),
             new KeyValue(this.getImgBomb().xProperty(),350 ),new KeyValue(this.getImgBomb().yProperty(),100),new KeyValue(this.getImgBomb().rotateProperty(), 200),new KeyValue(getImgBomb().cacheHintProperty(), CacheHint.QUALITY)));
  
     makeAnimation.play();
	}

    /**
     * @return the imgBomb
     */
    public ImageView getImgBomb() {
        return imgBomb;
    }

    /**
     * @param imgBomb the imgBomb to set
     */
    public void setImgBomb(ImageView imgBomb) {
        this.imgBomb = imgBomb;
    }
	

}