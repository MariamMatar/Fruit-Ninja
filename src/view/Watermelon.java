package view;

import controller.Actions;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class Watermelon extends Fruits{

   	     private ImageView imgwatermelon;
	     private ImageView waterHalf1;
         private ImageView waterHalf2;
         private boolean sliced;
         private Actions actions;
         private gamescene game;
         private double X;
         private double Y;

     
     
	public  Watermelon(Image image, double x, double y, Group group) {
       
    	 imgwatermelon = new ImageView(image);
         imgwatermelon.setX(x);
         imgwatermelon.setY(y);
         imgwatermelon.setFitWidth(82);
         imgwatermelon.setFitHeight(89);
         
         
         group.getChildren().add(imgwatermelon);
         ImageView waterHalf1 = new ImageView("/img/halfwatermelon.png");
	ImageView waterHalf2 = new ImageView("/img/halfwatermelon.png");
         this.waterHalf1=waterHalf1;
         this.waterHalf2=waterHalf2;
         waterHalf1.setVisible(false);
           group.getChildren().remove(waterHalf1);
	        group.getChildren().remove(waterHalf2);
	}

    public Watermelon() {
    	 
     }
    public void playCutSound()
    {
        // Create array of aduio clip 4 sounds
        AudioClip cut[] = new AudioClip[4];
        
        // Sounds urls
        String sounds[] = {"/sounds/cut_fruit1.mp3", "/sounds/cut_fruit2.mp3","/sounds/cut_fruit3.mp3","/sounds/cut_fruit4.mp3"};
        
        // Create objects
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


	        getWaterHalf1().setRotate(22);
	        getWaterHalf2().setRotate(25);

	        getWaterHalf1().setLayoutX(startX - 2);
	        getWaterHalf1().setLayoutY(startY + 38);
	        
	        getWaterHalf2().setLayoutX(startX - 5);
	        getWaterHalf2().setLayoutY(startY - 18);

	        getWaterHalf1().setFitWidth(92);
	        getWaterHalf1().setFitHeight(76);
	        
	        getWaterHalf2().setFitWidth(92);
	        getWaterHalf2().setFitHeight(76);
	        
	       
	        group.getChildren().add(getWaterHalf1());
	        group.getChildren().add(getWaterHalf2());
	        
	       
	        getImgwatermelon().setVisible(false);
	        getWaterHalf1().setVisible(true);
	        getWaterHalf2().setVisible(true);
	        
	        
	        playCutSound();
	        
	      
	        Timeline animation = new Timeline(new KeyFrame(Duration.millis(5), e -> 
	        {
	        	Animation(getWaterHalf1(), getWaterHalf2());
	        }));
	        
	        animation.setCycleCount(Timeline.INDEFINITE);
	        animation.play();		
	}

	@Override
	public void Animation(ImageView fruitHalf1, ImageView fruitHalf2) {
		getWaterHalf1().setRotate(getWaterHalf1().getRotate() + 1);
        getWaterHalf2().setRotate(getWaterHalf2().getRotate() + 1);
        
        getWaterHalf1().setX(getWaterHalf1().getX() + 1);
        getWaterHalf1().setY(getWaterHalf1().getY() + 1);
        
        getWaterHalf2().setX(getWaterHalf2().getX() - 1);
        getWaterHalf2().setY(getWaterHalf2().getY() + 1);
		
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
                    if(getWaterHalf1().isVisible()==false){
                    System.out.println("water has moved off");
                      
                   if(getActions().getLive()==1)
                           makeAnimation.stop();
                          getActions().updateLives(-1);  
                       
                 
                   
                        setSliced(false);
                        System.out.println("live:"+getActions().getLive());
                      
                    }
                },
                new KeyValue(getImgwatermelon().xProperty(), 700),new KeyValue(getImgwatermelon().yProperty(),700),new KeyValue(getImgwatermelon().rotateProperty(), 400)));
    
        makeAnimation.getKeyFrames().add(new KeyFrame( Duration.millis(getActions().levelvelocityY()),
                new KeyValue(getImgwatermelon().xProperty(),350 ),new KeyValue(getImgwatermelon().yProperty(),100),new KeyValue(getImgwatermelon().rotateProperty(), 200)));
     
        makeAnimation.play();
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

    public gamescene getGame() {
        return game;
    }

    public void setGame(gamescene game) {
        this.game = game;
    }

    /**
     * @return the imgwatermelon
     */
    public ImageView getImgwatermelon() {
        return imgwatermelon;
    }

    /**
     * @param imgwatermelon the imgwatermelon to set
     */
    public void setImgwatermelon(ImageView imgwatermelon) {
        this.imgwatermelon = imgwatermelon;
    }

    /**
     * @return the waterHalf1
     */
    public ImageView getWaterHalf1() {
        return waterHalf1;
    }

    /**
     * @param waterHalf1 the waterHalf1 to set
     */
    public void setWaterHalf1(ImageView waterHalf1) {
        this.waterHalf1 = waterHalf1;
    }

    /**
     * @return the waterHalf2
     */
    public ImageView getWaterHalf2() {
        return waterHalf2;
    }

    /**
     * @param waterHalf2 the waterHalf2 to set
     */
    public void setWaterHalf2(ImageView waterHalf2) {
        this.waterHalf2 = waterHalf2;
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