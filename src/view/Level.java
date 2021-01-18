package view;

import Model.EasyLevel;
import Model.GameObject;
import Model.HardLevel;
import Model.IStrategy;
import Model.MediumLevel;
import controller.Actions;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Level {

	private Stage stage;
	private Scene scene;
	private startmenu startmenu;
	private gamescene gamescene;
    private Actions actions;
	private mango mango1;
	private kiwi kiwi1;
	
	private dragon dragon1;
	private GameObject fruit;
	private Image[] img;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
	
	
	public Level(Stage stage,GameObject fruit ,kiwi kiwi,mango mango, dragon dragon) {
	
		this.stage=stage;             
		this.fruit=fruit;
		this.kiwi1=kiwi;
		this.mango1=mango;
		this.dragon1=dragon;
		
		
	}
	public void preparescene() {

	     	 Group g=new Group();
	     	 Circle whole = new Circle(80,80, 80);
	         Circle inside = new Circle(80,80, 70);
	         
	         Shape donutShape = Shape.subtract(whole, inside);
	         Shape donutShape1 = Shape.subtract(whole, inside);
	         Shape donutShape2 = Shape.subtract(whole, inside);
	         
	         donutShape.setStyle(" -fx-fill: linear-gradient(#b22222,#ffff00);");
	         donutShape.setLayoutX(347);
	         donutShape.setLayoutY(350);
	         
	         donutShape1.setStyle(" -fx-fill: linear-gradient(#00bfff,#ffff00);");
	         donutShape1.setLayoutX(600);
	         donutShape1.setLayoutY(273);
	         
	         donutShape2.setStyle(" -fx-fill: linear-gradient(#228b22,#b8860b);");
	         donutShape2.setLayoutX(100);
	         donutShape2.setLayoutY(273);
	         
		    setImg(getFruit().getImage());
		    ImageView kiwi= new ImageView(getImg()[0]);
		    ImageView mango= new ImageView(getImg()[3]);
		    ImageView dragon= new ImageView(getImg()[4]);
		    
		    
		    Glow glow = new Glow(); 

		      glow.setLevel(0.4); 

		      kiwi.setEffect(glow);   
		      mango.setEffect(glow);    
		      dragon.setEffect(glow);
		   
		    setKiwi1(new kiwi(getImg()[1], 250, 350, g));
		    setMango1(new mango(getImg()[1], 500, 350, g));
		    setDragon1(new dragon(getImg()[1], 500, 350, g));
		    
		    
		    kiwi.setFitHeight(100);
		    kiwi.setFitWidth(100);
		    mango.setFitHeight(100);
		    mango.setFitWidth(100);
		    dragon.setFitHeight(100);
		    dragon.setFitWidth(100);
		    			
		    FadeTransition fadeIn = new FadeTransition(Duration.millis(1000));
            FadeTransition fadeIn1 = new FadeTransition(Duration.millis(1000));
            FadeTransition fadeIn2 = new FadeTransition(Duration.millis(1000));
                Label level1 = new Label ("EASY");
                level1.setStyle(" -fx-text-fill: linear-gradient(#228b22,#b8860b); -fx-font: normal bold 30px 'cursive' ");
                fadeIn.setNode(level1);

                fadeIn.setFromValue(0.2);
                fadeIn.setToValue(1);
                fadeIn.setCycleCount(1000);
                fadeIn.setAutoReverse(true);
                fadeIn.play();
                Label level2 = new Label ("MEDIUM");
                level2.setStyle(" -fx-text-fill: linear-gradient(#b22222,#ffff00); -fx-font: normal bolder 30px 'cursive'; ");
                fadeIn1.setNode(level2);

                fadeIn1.setFromValue(0.2);
                fadeIn1.setToValue(1);
                fadeIn1.setCycleCount(1000);
                fadeIn1.setAutoReverse(true);
                fadeIn1.play();
                Label level3 = new Label ("HARD");
                level3.setStyle(" -fx-text-fill: linear-gradient(#00bfff,#ffff00); -fx-font: normal bold 30px 'cursive';");
                fadeIn2.setNode(level3);

                fadeIn2.setFromValue(0.2);
                fadeIn2.setToValue(1);
                fadeIn2.setCycleCount(1000);
                fadeIn2.setAutoReverse(true);
                fadeIn2.play();          
                
                
             
		Image image =new Image("/img/Wiki-background.jpg");
	    ImageView mv1= new ImageView(image);
	    mv1.setFitHeight(550);
	    mv1.setFitWidth(900);
	    Image image2 =new Image("/img/logo.png");
	    ImageView mv2= new ImageView(image2);
	    mv2.setFitHeight(150);
	    mv2.setFitWidth(400);
	    mv2.setRotate(-10);
     	mv2.setLayoutY(85);
     	mv2.setLayoutX(230);
	    
		g.getChildren().addAll(mv1,kiwi,mango,dragon,level1,level2,level3,mv2,donutShape,donutShape1,donutShape2);
		   
	    kiwi.setLayoutX(130);
	    kiwi.setLayoutY(300);
		level1.setLayoutX(145);
		level1.setLayoutY(220);
	   
	    mango.setLayoutX(380);
	    mango.setLayoutY(380);
		level2.setLayoutX(359);
		level2.setLayoutY(310);
		
		level3.setLayoutX(630);
		level3.setLayoutY(220);
		dragon.setLayoutX(630);
		dragon.setLayoutY(300);
		
		
		Timeline kiwirotation = new Timeline(
                new KeyFrame(Duration.millis(10), e -> kiwi.setRotate(kiwi.getRotate() + 1))
        );
        
        kiwirotation.setCycleCount(Timeline.INDEFINITE);
        kiwirotation.play();
        
        Timeline mangorotation = new Timeline(
                new KeyFrame(Duration.millis(8), e -> mango.setRotate(mango.getRotate() + 1))
        );
        
        mangorotation.setCycleCount(Timeline.INDEFINITE);
        mangorotation.play();
        Timeline donutrotation = new Timeline(
                new KeyFrame(Duration.millis(8), e -> donutShape.setRotate(donutShape.getRotate() + 1))
        );
        
        donutrotation.setCycleCount(Timeline.INDEFINITE);
        donutrotation.play();
        
        
        Timeline dragonrotation = new Timeline(
                new KeyFrame(Duration.millis(10), e -> dragon.setRotate(dragon.getRotate() + 1))
        );
        
        dragonrotation.setCycleCount(Timeline.INDEFINITE);
        dragonrotation.play();
		mv1.setOnDragDetected(dragDetected -> mv1.startFullDrag());	
		
	
		 kiwi.setOnMouseDragEntered(dragEntered -> 
	        {
	            // Get start x, y
	        	
	            setStartX(dragEntered.getX());
	            setStartY(dragEntered.getY());      

	        });
	        
	        kiwi.setOnMouseDragExited(dragExit ->
	        {
	            setEndX(dragExit.getX());
	            setEndY(dragExit.getY());

	            getKiwi1().slice(g,220,350);
	            getKiwi1().createSlashTrace(220, 350, 260, 350, g);
	            kiwi.setVisible(false);
	            Timeline delay = new Timeline(
	                    new KeyFrame(Duration.millis(500), eventDelay1 ->
	                    {
	                    	EasyLevel easy=new EasyLevel();
                          getActions().setName("easy");
                          getActions().setStrategy(easy);
                        
                          getGamescene().preparescene();
                          getGamescene().doTime();
                          getStage().setScene(getGamescene().getScene());
	                    }
	                    )
	            		);
	            
	            delay.setCycleCount(1);
	            delay.play();

	           
	        });
		
	        mango.setOnMouseDragEntered(dragEntered -> 
	        {
	            // Get start x, y
	        	
	            setStartX(dragEntered.getX());
	            setStartY(dragEntered.getY());      

	        });
	        
	        mango.setOnMouseDragExited(dragExit ->
	        {
	            setEndX(dragExit.getX());
	            setEndY(dragExit.getY());

	            getMango1().slice(g,220,350);
	            getMango1().createSlashTrace(220, 350, 260, 350, g);
	            mango.setVisible(false);
	            Timeline delay = new Timeline(
	                    new KeyFrame(Duration.millis(500), eventDelay1 ->
	                    {
	                      MediumLevel Medium=new MediumLevel();
                          getActions().setName("medium");
                          getGamescene().preparescene();
                          getGamescene().doTime();
                          getActions().setStrategy(Medium);
			              getStage().setScene(getGamescene().getScene());
	                    }
	                    )
	            		);
	            
	            delay.setCycleCount(1);
	            delay.play();

	           
	        });
	        dragon.setOnMouseDragExited(dragExit ->
	        {
	            setEndX(dragExit.getX());
	            setEndY(dragExit.getY());

	            getDragon1().slice(g,220,350);
	            getDragon1().createSlashTrace(220, 350, 260, 350, g);
	            dragon.setVisible(false);
	            Timeline delay = new Timeline(
	                    new KeyFrame(Duration.millis(500), eventDelay1 ->
	                    {
	                    	 HardLevel Hard= new HardLevel();
                           getActions().setName("hard");
                           getGamescene().preparescene();
                           getGamescene().doTime();
			               getActions().setStrategy(Hard);
                           getStage().setScene(getGamescene().getScene());
	                    }
	                    )
	            		);
	            
	            delay.setCycleCount(1);
	            delay.play();

	           
	        });

		setScene(new Scene(g,900,550));

                
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
	public startmenu getStartmenu() {
		return startmenu;
	}
	public void setStartmenu(startmenu startmenu) {
		this.startmenu = startmenu;
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
     * @return the mango1
     */
    public mango getMango1() {
        return mango1;
    }

    /**
     * @param mango1 the mango1 to set
     */
    public void setMango1(mango mango1) {
        this.mango1 = mango1;
    }

    /**
     * @return the kiwi1
     */
    public kiwi getKiwi1() {
        return kiwi1;
    }

    /**
     * @param kiwi1 the kiwi1 to set
     */
    public void setKiwi1(kiwi kiwi1) {
        this.kiwi1 = kiwi1;
    }

    /**
     * @return the dragon1
     */
    public dragon getDragon1() {
        return dragon1;
    }

    /**
     * @param dragon1 the dragon1 to set
     */
    public void setDragon1(dragon dragon1) {
        this.dragon1 = dragon1;
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