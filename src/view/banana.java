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

public class banana extends Fruits{

   	 private ImageView imgBanana;
	 private ImageView bananaHalf1;
         private ImageView bananaHalf2;
         private boolean sliced;
         private Actions actions;
         private gamescene game;

     private double X;
     private double Y;

     
     
	public  banana(Image image, double x, double y, Group group) {
       
    	 imgBanana = new ImageView(image);
         imgBanana.setX(x);
         imgBanana.setY(y);
         imgBanana.setFitWidth(82);
         imgBanana.setFitHeight(89);
         
         
         group.getChildren().add(imgBanana);
         ImageView bananaHalf1 = new ImageView("/img/halfbanana.png");
	ImageView bananaHalf2 = new ImageView("/img/halfbanana.png");
         this.bananaHalf1=bananaHalf1;
         this.bananaHalf2=bananaHalf2;
         bananaHalf1.setVisible(false);
           group.getChildren().remove(bananaHalf1);
	        group.getChildren().remove(bananaHalf1);
	}

    public banana() {
    	 
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

	        getBananaHalf1().setRotate(22);
	        getBananaHalf2().setRotate(25);

	        getBananaHalf1().setLayoutX(startX - 2);
	        getBananaHalf1().setLayoutY(startY + 38);
	        
	        getBananaHalf2().setLayoutX(startX - 5);
	        getBananaHalf2().setLayoutY(startY - 18);

	        getBananaHalf1().setFitWidth(92);
	        getBananaHalf1().setFitHeight(76);
	        
	        getBananaHalf2().setFitWidth(92);
	        getBananaHalf2().setFitHeight(76);
	        
	        // Add the two halves to the pane
	        group.getChildren().add(getBananaHalf1());
	        group.getChildren().add(getBananaHalf2());
	        
	        // Remove the kiwi and make the two halves of kiwi visible
	        getImgBanana().setVisible(false);
	        getBananaHalf1().setVisible(true);
	        getBananaHalf2().setVisible(true);
	        
	         //Play the cut sound
	        playCutSound();
	        
	        // Start animation
	        Timeline animation = new Timeline(new KeyFrame(Duration.millis(5), e -> 
	        {
	        	Animation(this.getBananaHalf1(), this.getBananaHalf2());
	        }));
	        
	        animation.setCycleCount(Timeline.INDEFINITE);
	        animation.play();		
	}

	@Override
	public void Animation(ImageView fruitHalf1, ImageView fruitHalf2) {
		getBananaHalf1().setRotate(getBananaHalf1().getRotate() + 1);
        getBananaHalf2().setRotate(getBananaHalf2().getRotate() + 1);
        
        getBananaHalf1().setX(getBananaHalf1().getX() + 1);
        getBananaHalf1().setY(getBananaHalf1().getY() + 1);
        
        getBananaHalf2().setX(getBananaHalf2().getX() - 1);
        getBananaHalf2().setY(getBananaHalf2().getY() + 1);
		
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
                    if(getBananaHalf1().isVisible()==false){
                        
                    System.out.println("water has moved off");
                      
                   if(getActions().getLive()==1)
                           makeAnimation.stop();
                          getActions().updateLives(-1);  
                   

                   
                        setSliced(false);
                        System.out.println("live:"+getActions().getLive());
                      
                    }
                },
                new KeyValue(getImgBanana().xProperty(), 700),new KeyValue(getImgBanana().yProperty(),700),new KeyValue(getImgBanana().rotateProperty(), 400)));

        makeAnimation.getKeyFrames().add(new KeyFrame( Duration.millis(getActions().levelvelocityY()),
                new KeyValue(getImgBanana().xProperty(),350 ),new KeyValue(getImgBanana().yProperty(),100),new KeyValue(getImgBanana().rotateProperty(), 200)));

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
     * @return the imgBanana
     */
    public ImageView getImgBanana() {
        return imgBanana;
    }

    /**
     * @param imgBanana the imgBanana to set
     */
    public void setImgBanana(ImageView imgBanana) {
        this.imgBanana = imgBanana;
    }

    /**
     * @return the bananaHalf1
     */
    public ImageView getBananaHalf1() {
        return bananaHalf1;
    }

    /**
     * @param bananaHalf1 the bananaHalf1 to set
     */
    public void setBananaHalf1(ImageView bananaHalf1) {
        this.bananaHalf1 = bananaHalf1;
    }

    /**
     * @return the bananaHalf2
     */
    public ImageView getBananaHalf2() {
        return bananaHalf2;
    }

    /**
     * @param bananaHalf2 the bananaHalf2 to set
     */
    public void setBananaHalf2(ImageView bananaHalf2) {
        this.bananaHalf2 = bananaHalf2;
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