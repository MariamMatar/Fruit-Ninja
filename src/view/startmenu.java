package view;

import Model.GameObject;
import Model.IStrategy;
import Model.arcadeLevel;
import controller.Actions;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class startmenu {
	private Stage stage;
	private Scene scene;
	private gamescene gamescene;
	private arcade arcade;
	private Level levels;
	private Watermelon watermelon1;
	private banana banana1;
	private GameObject fruit;
	private Image[] img;
    private Actions actions;
       
	private double startX;
	private double startY;
	private double endX;
	private double endY;

	public startmenu(Stage stage,GameObject fruit,Watermelon watermelon1,banana banana1) {
		this.stage=stage;
		this.fruit=fruit;
		this.watermelon1=watermelon1;
		this.banana1=banana1;
	}
	
public void preparescene() {
	
	    Group g=new Group();
	    Image image1= new Image("/img/Fruit+Ninja+(Main+Graphic2).jpg");
	    ImageView mv1= new ImageView(image1);
	    mv1.setFitHeight(550);
	    mv1.setFitWidth(900);
	    
	    Image ring= new Image("/img/h.png");
	    ImageView re= new ImageView(ring);
	    re.setFitHeight(320);
	    re.setFitWidth(420);
	    re.setLayoutX(430);
	    re.setLayoutY(280);
	    
	    
	    Image ring1= new Image("/img/k.png");
	    ImageView re1= new ImageView(ring1);
	    re1.setFitHeight(320);
	    re1.setFitWidth(420);
	    re1.setLayoutX(-20);
	    re1.setLayoutY(280);
	    
	    
	    setImg(getFruit().getImage());
	    
	    ImageView watermelon= new ImageView(getImg()[1]);
	    ImageView banana= new ImageView(getImg()[2]);
    
	    setWatermelon1(new Watermelon(getImg()[1],100,100, g));
	    setBanana1(new banana(getImg()[2], 100, 100, g));
	    
	    watermelon.setLayoutX(153);
	    watermelon.setLayoutY(405);
	    
	    banana.setFitHeight(75);
	    banana.setFitWidth(75);
	    banana.setLayoutX(600);
	    banana.setLayoutY(405);
	    
	    watermelon.setFitHeight(73);
	    watermelon.setFitWidth(73);

  
	    Label start = new Label ();
		Label start2=new Label();
		  
		   Timeline bananamodeRotation = new Timeline(
	                new KeyFrame(Duration.millis(10), e -> re.setRotate(re.getRotate() + 1))
	        );
	        
	        bananamodeRotation.setCycleCount(Timeline.INDEFINITE);
	        bananamodeRotation.play();
	        
	        Timeline watermelon1Rotation = new Timeline(
	                new KeyFrame(Duration.millis(10), e -> re1.setRotate(re1.getRotate() + 1))
	        );
	        
	        watermelon1Rotation.setCycleCount(Timeline.INDEFINITE);
	        watermelon1Rotation.play();
          
 
   
		
	      Glow glow = new Glow(); 
	      glow.setLevel(0.3); 
	      watermelon.setEffect(glow);   
	      banana.setEffect(glow);    
          re.setEffect(glow);
          re1.setEffect(glow);
	      
		  start.setText("CLASSIC MODE");
		  Reflection r = new Reflection();
		  r.setFraction(2.0f);	   
		  start.setEffect(r);
		  start2.setEffect(r);	 
          start.setStyle(" -fx-text-fill: linear-gradient( #32cd32,#adff2f); -fx-font: normal bold 25px 'cursive' ");
          start2.setText("ARCADE MODE");
          start2.setStyle(" -fx-text-fill: linear-gradient(#ff0000,#ffd700); -fx-font: normal bold 25px 'cursive' ");
       
          start.setLayoutX(250);
          start.setLayoutY(390);
          start2.setLayoutX(650);
          start2.setLayoutY(390);
	  
  		g.getChildren().addAll(mv1,re,re1,watermelon,banana);
  		
  		Timeline watermelonRotation = new Timeline(
                new KeyFrame(Duration.millis(10), e -> watermelon.setRotate(watermelon.getRotate() + 1))
        );
        
        watermelonRotation.setCycleCount(Timeline.INDEFINITE);
        watermelonRotation.play();
        
        Timeline bananaRotation = new Timeline(
                new KeyFrame(Duration.millis(10), e -> banana.setRotate(banana.getRotate() + 1))
        );
        
        bananaRotation.setCycleCount(Timeline.INDEFINITE);
        bananaRotation.play();
        
		mv1.setOnDragDetected(dragDetected -> mv1.startFullDrag());	
		
        watermelon.setOnMouseDragEntered(dragEntered -> 
        {
            // Get start x, y
        	
            setStartX(dragEntered.getX());
            setStartY(dragEntered.getY());      

        });
        
        watermelon.setOnMouseDragExited(dragExit ->
        {
            setEndX(dragExit.getX());
            setEndY(dragExit.getY());

            getWatermelon1().slice(g,220,350);
            getWatermelon1().createSlashTrace(220, 350, 260, 350, g);
            watermelon.setVisible(false);
            Timeline delay = new Timeline(
                    new KeyFrame(Duration.millis(500), eventDelay1 ->
                    {
                        getLevels().preparescene();
            		    getStage().setScene(getLevels().getScene());
                    }
                    )
            		);
            
            delay.setCycleCount(1);
            delay.play();

           
        });
        
        banana.setOnMouseDragEntered(dragEntered -> 
        {
            // Get start x, y
        	
            setStartX(dragEntered.getX());
            setStartY(dragEntered.getY());
            

        });
        
        banana.setOnMouseDragExited(dragExit ->
        {
            setEndX(dragExit.getX());
            setEndY(dragExit.getY());

            getBanana1().slice(g,500,350);
            getBanana1().createSlashTrace(500, 350, 510, 350, g);
            banana.setVisible(false);
            Timeline delay = new Timeline(
                    new KeyFrame(Duration.millis(500), eventDelay1 ->
                    {       
                        getActions().setName("arcade");
                        IStrategy arcadeLevel = new arcadeLevel();
                           getActions().setStrategy(arcadeLevel);
                            getArcade().prepareScene();
            		    getStage().setScene(getArcade().getScene());
                    }
                    )
            		);
            
            delay.setCycleCount(1);
            delay.play();

           
        });
  		setScene(new Scene(g,900,550));
}

public Level getLevels() {
	return levels;
}
public void setLevels(Level levels) {
	this.levels = levels;
}
public arcade getArcade() {
	return arcade;
}
public void setArcade(arcade arcade) {
	this.arcade = arcade;
}
public Stage getStage() {
	return stage;
}
public void setStage(Stage stage) {
	this.stage = stage;
}
public Scene getScene() {
	return scene;
}
public void setScene(Scene scene) {
	this.scene = scene;
}
public gamescene getGamescene() {
	return gamescene;
}
public void setGamescene(gamescene gamescene) {
	this.gamescene = gamescene;
}

    public Actions getActions() {
        return actions;
    }

    public void setActions(Actions actions) {
        this.actions = actions;
    }

    /**
     * @return the watermelon1
     */
    public Watermelon getWatermelon1() {
        return watermelon1;
    }

    /**
     * @param watermelon1 the watermelon1 to set
     */
    public void setWatermelon1(Watermelon watermelon1) {
        this.watermelon1 = watermelon1;
    }

    /**
     * @return the banana1
     */
    public banana getBanana1() {
        return banana1;
    }

    /**
     * @param banana1 the banana1 to set
     */
    public void setBanana1(banana banana1) {
        this.banana1 = banana1;
    }

    /**
     * @return the fruit
     */
    public GameObject getFruit() {
        return fruit;
    }

    /**
     * @param fruit the fruit to set
     */
    public void setFruit(GameObject fruit) {
        this.fruit = fruit;
    }

    /**
     * @return the img
     */
    public Image[] getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(Image[] img) {
        this.img = img;
    }

    /**
     * @return the startX
     */
    public double getStartX() {
        return startX;
    }

    /**
     * @param startX the startX to set
     */
    public void setStartX(double startX) {
        this.startX = startX;
    }

    /**
     * @return the startY
     */
    public double getStartY() {
        return startY;
    }

    /**
     * @param startY the startY to set
     */
    public void setStartY(double startY) {
        this.startY = startY;
    }

    /**
     * @return the endX
     */
    public double getEndX() {
        return endX;
    }

    /**
     * @param endX the endX to set
     */
    public void setEndX(double endX) {
        this.endX = endX;
    }

    /**
     * @return the endY
     */
    public double getEndY() {
        return endY;
    }

    /**
     * @param endY the endY to set
     */
    public void setEndY(double endY) {
        this.endY = endY;
    }



}