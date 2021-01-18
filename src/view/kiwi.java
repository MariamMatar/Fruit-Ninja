package view;

import controller.Actions;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class kiwi extends Fruits {
	
	 private ImageView imgKiwi;
	 private ImageView KiwiHalf1;
     private ImageView KiwiHalf2;
     private Label live; 
     private boolean sliced;
 
     
	    
    
     private double X;
    private double Y;
    private Actions actions;

     
     public kiwi() {
    	
     }
	public  kiwi(Image image, double x, double y, Group group) {

    	 imgKiwi = new ImageView(image);
//         
         
         sliced=true;
         imgKiwi.setX(x);
         imgKiwi.setY(y);
         imgKiwi.setFitWidth(82);
         imgKiwi.setFitHeight(89);
         imgKiwi.setVisible(true);
        
         
         group.getChildren().add(imgKiwi);
        ImageView KiwiHalf1 = new ImageView("/img/kiwi_half1.png");
	ImageView  KiwiHalf2 = new ImageView("/img/kiwi_half2.png");
         this.KiwiHalf1=KiwiHalf1;
         this.KiwiHalf2=KiwiHalf2;
         KiwiHalf1.setVisible(false);
         group.getChildren().remove(KiwiHalf1);
	   group.getChildren().remove(KiwiHalf2);
	}
          
    public void playCutSound()
    {
        
        AudioClip cut[] = new AudioClip[4];
        
       
        String sounds[] = {"/sounds/cut_fruit1.mp3", "/sounds/cut_fruit2.mp3","/sounds/cut_fruit3.mp3","/sounds/cut_fruit4.mp3"};
        
       
        for (int i = 0; i < cut.length; i++)
        {
            cut[i] = new AudioClip(this.getClass().getResource(sounds[i]).toExternalForm());
        }
        
       
        int randomSound = (int) (4 * Math.random());
        
      
        cut[randomSound].setCycleCount(1);
        cut[randomSound].play();
    }
	@Override
	public void slice(Group group,double startX,double startY) {

	        getKiwiHalf1().setRotate(22);
	        getKiwiHalf2().setRotate(25);

	        getKiwiHalf1().setLayoutX(startX - 2);
	        getKiwiHalf1().setLayoutY(startY + 38);
	        
	        getKiwiHalf2().setLayoutX(startX - 5);
	        getKiwiHalf2().setLayoutY(startY - 18);

	        getKiwiHalf1().setFitWidth(92);
	        getKiwiHalf1().setFitHeight(76);
	        
	        getKiwiHalf2().setFitWidth(92);
	        getKiwiHalf2().setFitHeight(76);
	        
	        // Add the two halves to the pane
	        group.getChildren().add(getKiwiHalf1());
	        group.getChildren().add(getKiwiHalf2());
	        
	        // Remove the kiwi and make the two halves of kiwi visible
	        getImgKiwi().setVisible(false);
	        getKiwiHalf1().setVisible(true);
	        getKiwiHalf2().setVisible(true);
	        
	        // Play the cut sound
	        this.playCutSound();
	        
	        // Start animation
	        Timeline animation = new Timeline(new KeyFrame(Duration.millis(5), e -> 
	        {
	        	Animation(getKiwiHalf1(), getKiwiHalf2());
	        }));
	        
	        animation.setCycleCount(Timeline.INDEFINITE);
	        animation.play();		
	}

	@Override
	public void Animation(ImageView fruitHalf1, ImageView fruitHalf2) {
        getKiwiHalf1().setRotate(getKiwiHalf1().getRotate() + 1);
        getKiwiHalf2().setRotate(getKiwiHalf2().getRotate() + 1);
        
        getKiwiHalf1().setX(getKiwiHalf1().getX() + 1);
        getKiwiHalf1().setY(getKiwiHalf1().getY() + 1);
        
        getKiwiHalf2().setX(getKiwiHalf2().getX() - 1);
        getKiwiHalf2().setY(getKiwiHalf2().getY() + 1);
		
	}

	

	@Override
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

        showSlash.setCycleCount(1);
        showSlash.play();
		
	}

	@Override
	public void appearFruit(Group group) {
           
            
		Timeline makeAnimation = new Timeline();
               
        makeAnimation.getKeyFrames().add(new KeyFrame( Duration.millis(getActions().levelvelocityX()),e->{
                    if(getKiwiHalf1().isVisible()==false){
                    System.out.println(" kiwi has moved off");
                      if(getActions().getLive()==1)
                           makeAnimation.stop();
                      
                          getActions().updateLives(-1);  
                       
                  
                        setSliced(false);
                        System.out.println("live:"+getActions().getLive());
                      
                    }
                },
                new KeyValue(getImgKiwi().xProperty(), 860),new KeyValue(getImgKiwi().yProperty(),700),new KeyValue(getImgKiwi().rotateProperty(), 400)));
   
        makeAnimation.getKeyFrames().add(new KeyFrame( Duration.millis(getActions().levelvelocityY()),
                new KeyValue(getImgKiwi().xProperty(),360),new KeyValue(getImgKiwi().yProperty(),300),new KeyValue(getImgKiwi().rotateProperty(), 200)));
    
        makeAnimation.play();

	}

  

    public Label getLive() {
        return live;
    }

    public void setLive(Label live) {
        this.live = live;
    }
        

    public boolean isSliced() {
        return sliced;
    }

    public void setSliced(boolean sliced) {
        this.sliced = sliced;
    }

    public Actions getActions() {
        return actions;
    }

    public void setActions(Actions actions) {
        this.actions = actions;
    }

    /**
     * @return the imgKiwi
     */
    public ImageView getImgKiwi() {
        return imgKiwi;
    }

    /**
     * @param imgKiwi the imgKiwi to set
     */
    public void setImgKiwi(ImageView imgKiwi) {
        this.imgKiwi = imgKiwi;
    }

    /**
     * @return the KiwiHalf1
     */
    public ImageView getKiwiHalf1() {
        return KiwiHalf1;
    }

    /**
     * @param KiwiHalf1 the KiwiHalf1 to set
     */
    public void setKiwiHalf1(ImageView KiwiHalf1) {
        this.KiwiHalf1 = KiwiHalf1;
    }

    /**
     * @return the KiwiHalf2
     */
    public ImageView getKiwiHalf2() {
        return KiwiHalf2;
    }

    /**
     * @param KiwiHalf2 the KiwiHalf2 to set
     */
    public void setKiwiHalf2(ImageView KiwiHalf2) {
        this.KiwiHalf2 = KiwiHalf2;
    }

    /**
     * @return the X
     */
    public double getX() {
        return X;
    }

    /**
     * @param X the X to set
     */
    public void setX(double X) {
        this.X = X;
    }

    /**
     * @return the Y
     */
    public double getY() {
        return Y;
    }

    /**
     * @param Y the Y to set
     */
    public void setY(double Y) {
        this.Y = Y;
    }

    
	

}