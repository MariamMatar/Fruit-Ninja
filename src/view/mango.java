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

public class mango extends Fruits{

	private ImageView imgmango;
	 private ImageView star1;
	 private ImageView star2;
          private boolean sliced;
          private Actions actions;

	 
	 
   public mango() {
	   
    }
	public  mango(Image image, double x, double y, Group group) {
      
		imgmango = new ImageView(image);
		imgmango.setX(x);
		imgmango.setY(y);
		imgmango.setFitWidth(50);
		imgmango.setFitHeight(50);
                ImageView star1 = new ImageView("/img/stars.png");
		ImageView star2 = new ImageView("/img/stars.png");
                
               this.star1=star1;
               this.star2=star2;
               star1.setVisible(false);
               group.getChildren().remove(star1);
	       group.getChildren().remove(star2);
        
        group.getChildren().add(imgmango);		
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
	public void slice(Group group, double startX, double startY) {
		
	        

		getStar1().setRotate(24.8);
		getStar2().setRotate(31);

		getStar1().setLayoutX(startX -13);
		getStar1().setLayoutY(startY +96);
	        
		getStar2().setLayoutX(startX +20);
		getStar2().setLayoutY(startY -21);

		getStar1().setFitWidth(100);
		getStar1().setFitHeight(150);
	        
		getStar2().setFitWidth(100);
		getStar2().setFitHeight(150);
	        
	        group.getChildren().add(getStar1());
	        group.getChildren().add(getStar2());
	        
	        getImgmango().setVisible(false);
	        getStar1().setVisible(true);
	        getStar2().setVisible(true);
	        
	        playCutSound();
	        
	        Timeline animation = new Timeline(new KeyFrame(Duration.millis(5), e -> 
	        {
	        	Animation(getStar1(), getStar2());
	        }));
	        
	        animation.setCycleCount(Timeline.INDEFINITE);
	        animation.play();			
	}

	@Override
	public void Animation(ImageView fruitHalf1, ImageView fruitHalf2) {
		getStar1().setRotate(getStar1().getRotate() + 1);
		getStar2().setRotate(getStar2().getRotate() + 1);
       
		getStar1().setX(getStar1().getX() + 1);
		getStar1().setY(getStar1().getY() + 1);
       
		getStar2().setX(getStar2().getX() - 1);
		getStar2().setY(getStar2().getY() + 1);
		
	}

	@Override
	public void createSlashTrace(double startX, double startY, double endX, double endY, Group group) {
		 double slope = (endY - startY) / (endX - startX);
	        
	        double angle = Math.toDegrees(Math.atan(slope));
	        
	        ImageView trace = new ImageView("/img/slash_trace.gif");
	        
	        trace.setRotate(45 - angle);
	        
	        trace.setVisible(false);
	        
	        trace.setLayoutX(startX - 17);
	        trace.setLayoutY(startY + 21);
	        
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
                    if(getStar1().isVisible()==false){
                    System.out.println("mango has moved off");
                    if(getActions().getLive()==1)
                           makeAnimation.stop();
                    
                          getActions().updateLives(-1);  
                   
                        setSliced(false);
                        System.out.println("live:"+getActions().getLive());
                      
                    }
                },
               new KeyValue(getImgmango().xProperty(), 700),new KeyValue(getImgmango().yProperty(),600),new KeyValue(getImgmango().rotateProperty(), 300)));
    
       makeAnimation.getKeyFrames().add(new KeyFrame( Duration.millis(getActions().levelvelocityY()),
               new KeyValue(getImgmango().xProperty(),250 ),new KeyValue(getImgmango().yProperty(),100),new KeyValue(getImgmango().rotateProperty(), 100)));
     
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

    /**
     * @return the imgmango
     */
    public ImageView getImgmango() {
        return imgmango;
    }

    /**
     * @param imgmango the imgmango to set
     */
    public void setImgmango(ImageView imgmango) {
        this.imgmango = imgmango;
    }

    /**
     * @return the star1
     */
    public ImageView getStar1() {
        return star1;
    }

    /**
     * @param star1 the star1 to set
     */
    public void setStar1(ImageView star1) {
        this.star1 = star1;
    }

    /**
     * @return the star2
     */
    public ImageView getStar2() {
        return star2;
    }

    /**
     * @param star2 the star2 to set
     */
    public void setStar2(ImageView star2) {
        this.star2 = star2;
    }


}