package view;

import Model.GameObject;
import controller.Actions;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class gamescene {
	private Stage stage;
	private Scene scene;
	private startmenu startmenu;
	private int startTime=0;
	private int y=0;
        private int z=0;
	private Integer seconds = startTime;
	
	private GameObject fruit;
	private kiwi kiwi;
        private Watermelon watermelon;
        private banana banana;
        private mango mango; 
        private dragon dragon;
	private Timeline kiwisAppear;
        private Timeline watermelonAppear;
        private Timeline bananasAppear;
        
        private Timeline mangosAppear;
        private Timeline dragonsAppear;
        private Timeline bombsAppear;
        private Timeline bomb2Appear;
	private AudioClip backgroundSound;
	private Actions actions;
        private Label scorelabel;
         private Label timerLabel;	
        private Label live;
        private ImageView heart3;
    private ImageView heart2;
    private ImageView heart;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private Group g = new Group();
    private Image[] img;
    private Image hearts;
    private GameObject bomba;
    private bomb bomb;
    private bomb2 bomb2;
        
	public gamescene(Stage stage ,GameObject fruit, GameObject bomba,bomb bomb,bomb2 bomb2,Actions actions){
	this.stage= stage;
	this.fruit=fruit;
	
        this.actions=actions;
      
        this.bomba=bomba;
	this.bomb=bomb;
	this.bomb2=bomb2;
       Label scorelabel  = new Label();
       Label lives  = new Label();
            this.live=lives;
        Image hearts =new Image("/img/heart.png",50,50,true,true);
        heart= new ImageView(hearts);

        this.hearts=hearts;
       this.scorelabel=scorelabel;
      

	}
	
	public void preparescene() {
             Button reset = new Button();
            Image resetimg=new Image("/img/replay.png",80,80,true,true);
	     ImageView m= new ImageView(resetimg);
	     reset.setGraphic(m);
	     reset.setStyle("-fx-background-color: rgba(0,0,0,0);");

		
            
		 setBackgroundSound(new AudioClip(this.getClass().getResource("/sounds/game_background_sound.mp3").toExternalForm()));
        getBackgroundSound().play(0.5);
        
        // Repeat the background sound when it ends as the setCycleCount for audio clip not working
        Timeline repeatSound = new Timeline(new KeyFrame(Duration.seconds(41), e -> {
            getBackgroundSound().play(0.5);
        }));
        
        repeatSound.setCycleCount(Timeline.INDEFINITE);
        repeatSound.play();


    	
	     
	     Image image =new Image("/img/Wiki-background.jpg",900,600,true,true);
	     ImageView mv1= new ImageView(image);
             
	     
          //   ImageView heart3= new ImageView(image2);
	     
             try {
			getActions().load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     Label highscore= new Label ();
	     highscore.setTextFill(Color.WHITE);
	     highscore.setStyle("-fx-font-size: 3em; -fx-border-color:linear-gradient(#ff0000,#ffd700); -fx-padding:15px;");
	     highscore.setText("HIGH-SCORE: " + getActions().getBestscore());
	    
             getScorelabel().setTextFill(Color.WHITE);
	     getScorelabel().setStyle("-fx-font-size: 4em;");
             getLive().setTextFill(Color.WHITE);
	     getLive().setStyle("-fx-font-size: 4em;");
	     
	     getHeart().setLayoutX(810);
   	     getHeart().setLayoutY(70);

             getScorelabel().setLayoutX(100);
	     getScorelabel().setLayoutY(0);
             getLive().setLayoutX(775);
             getLive().setLayoutY(60);
             highscore.setLayoutX(300);
 
               reset.setLayoutX(650);
    	     reset.setLayoutY(0);	
		 
		 
		
		
		 mv1.setOnDragDetected(dragDetected -> mv1.startFullDrag());
              //   kiwi.apearlives(g);
		 kiwisAppearTask();
                    Timeline delay = new Timeline(new KeyFrame(Duration.millis(1500), delayVisible ->
            {
               watermelonAppearTask();
                bombAppearTask();
                bananasAppearTask();
            }));

            delay.setCycleCount(1);
            delay.play();
            
               //  watermelonAppearTask();
                 
		 mangosAppearTask();
                 dragonsAppearTask();
		 
                 
                
                 bomb2AppearTask();
                 
                    reset.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					getActions().ResetGame();
					setSeconds((Integer) 0);
					setZ(0);
					setY(0);
//					
//                                kiwi.imgKiwi.setVisible(false);
//		                
//		                watermelon.imgwatermelon.setVisible(false);
//		               
//		                mango.imgmango.setVisible(false);
//		                
//		                banana.imgBanana.setVisible(false);
//		               
//		                dragon.imgdragon.setVisible(false);
		              
		                getTimerLabel().setVisible(false);
//		                bomb.imgBomb.setVisible(false);
//		                bomb2.imgBomb.setVisible(false);
				
                              getKiwisAppear().stop();
	                getBananasAppear().stop();
	                getWatermelonAppear().stop();
	                getDragonsAppear().stop();
	                getMangosAppear().stop();
	                getBombsAppear().stop();
	                getBomb2Appear().stop();
					
					getKiwisAppear().playFromStart();
	                getBananasAppear().playFromStart();
	                getWatermelonAppear().playFromStart();
	                getDragonsAppear().playFromStart();
	                getMangosAppear().playFromStart();               
	                getBombsAppear().playFromStart();
	                getBomb2Appear().playFromStart();
	                doTime();
	                getScorelabel().setText("score:"+String.valueOf(getActions().getScore()));
	                getLive().setText(String.valueOf(getActions().getLive()));
				}
			});   
                 
                  getScorelabel().setText("score:"+String.valueOf(getActions().getScore()));
                  System.out.println(getActions().getLive()+"mininini");
                  getLive().setText(String.valueOf(getActions().getLive()));
//                  System.out.println(actions.getLive());
//                  if(actions.getLive()==2){
//                    heart3.setVisible(false);
//                } else if(actions.getLive()==1){  
//                    heart2.setVisible(false); 
//                            }else{
//                   heart.setVisible(false); 
//                }
		 
 
	     getG().getChildren().addAll(mv1, getScorelabel(), getHeart(), getLive(),reset,highscore);

	         
	   

	    
	       Image icursor = new Image(getClass().getResourceAsStream("/img/spark1.png"),42,42,true,true);
		   ImageView imageview = new ImageView(icursor);
		    
		   
			
			
		   setScene(new Scene(getG(), 900, 550));
               //    System.out.println(scene.getHeight());
		   getScene().setCursor(new ImageCursor(icursor,0,0));
		 
	}
        
        public void doTime()
	{
		setTimerLabel(new Label());
		getTimerLabel().setLayoutX(760);
	     getTimerLabel().setLayoutY(0);
	     getTimerLabel().setText("");
	     getTimerLabel().setTextFill(Color.WHITE);
	     getTimerLabel().setStyle("-fx-font-size: 4em;");
		Timeline time= new Timeline();
		time.setCycleCount(time.INDEFINITE);
		if(time!=null) {
			time.stop();
		}
		KeyFrame keyframe= new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				setSeconds((Integer) (getSeconds() + 1));
				if(getSeconds()<=9){
					getTimerLabel().setText(String.valueOf(getZ())+String.valueOf(getY())+":"+"0"+getSeconds().toString());
                                        if(getActions().getLive()==0)
                                            try {
                                                time.stop();
                                                Endgame();
                                        } catch (IOException ex) {
                                            Logger.getLogger(gamescene.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                }
				else if(getSeconds()<60){
					getTimerLabel().setText(String.valueOf(getZ())+String.valueOf(getY())+":"+getSeconds().toString());
                                        if(getActions().getLive()==0)
                                            try {
                                                time.stop();
                                                Endgame();
                                        } catch (IOException ex) {
                                            Logger.getLogger(gamescene.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                }
				if(getSeconds()>59) {
					setSeconds((Integer) 0);

					setY(getY() + 1);
			
					if(getSeconds()<=9){
						getTimerLabel().setText(String.valueOf(getZ())+String.valueOf(getY())+":"+"0"+getSeconds().toString());
                                                        if(getActions().getLive()==0)
                                            try {
                                                time.stop();
                                                Endgame();
                                        } catch (IOException ex) {
                                            Logger.getLogger(gamescene.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        }
					else if(getSeconds()>9 ){
						getTimerLabel().setText(String.valueOf(getZ())+String.valueOf(getY())+getSeconds().toString());
                                                if(getActions().getLive()==0)
                                            try {
                                                time.stop();
                                                Endgame();
                                        } catch (IOException ex) {
                                            Logger.getLogger(gamescene.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        }
					
				
				}

			}
		
		});
		time.getKeyFrames().add(keyframe);
		time.playFromStart();
		getG().getChildren().add(getTimerLabel());
	}
	public void kiwisAppearTask()
    {
        setKiwisAppear(new Timeline(new KeyFrame(Duration.seconds(3), (e) -> {
            setImg(getFruit().getImage());
            setKiwi(new kiwi(getImg()[0], randomNumber(0, 350), randomNumber(500, 800), getG()));
            getKiwi().setActions(getActions());
            getKiwi().getImgKiwi().setVisible(false);
            getKiwi().getImgKiwi().setOnMouseDragEntered((dragEntered) -> {
                setStartX(dragEntered.getX());
                setStartY(dragEntered.getY());
                System.out.println(getStartX());
                System.out.println(getStartY());
            });
            getKiwi().getImgKiwi().setOnMouseDragExited((dragExit) -> {
                setEndX(dragExit.getX());
                setEndY(dragExit.getY());
                getKiwi().slice(getG(), getStartX(), getStartY());
                getActions().slice(true, false);
                getActions().setSliced(true);
                getScorelabel().setText("score:" + String.valueOf(getActions().getScore()));
                System.out.println(getActions().getLive());
                getKiwi().createSlashTrace(getStartX(), getStartY(), getEndX(), getEndY(), getG());
            });
            getKiwi().appearFruit(getG());
            getLive().setText(String.valueOf(getActions().getLive()));
            Timeline delay = new Timeline(new KeyFrame(Duration.millis(3), (delayVisible) -> {
                getKiwi().getImgKiwi().setVisible(true);
            }));
            delay.setCycleCount(1);
            delay.play();
        })));
        
        getKiwisAppear().setCycleCount(Timeline.INDEFINITE);
        getKiwisAppear().play();
    }
        	public void watermelonAppearTask()
    {
        setWatermelonAppear(new Timeline(new KeyFrame(Duration.seconds(3), (e) -> {
            setImg(getFruit().getImage());
            setWatermelon(new Watermelon(getImg()[1], randomNumber(0, 300), randomNumber(500, 700), getG()));
            getWatermelon().setActions(getActions());
            getWatermelon().getImgwatermelon().setVisible(false);
            getWatermelon().getImgwatermelon().setOnMouseDragEntered((dragEntered) -> {
                setStartX(dragEntered.getX());
                setStartY(dragEntered.getY());
                System.out.println(getStartX());
                System.out.println(getStartY());
            });
            getWatermelon().getImgwatermelon().setOnMouseDragExited((dragExit) -> {
                setEndX(dragExit.getX());
                setEndY(dragExit.getY());
                getWatermelon().slice(getG(), getStartX(), getStartY());
                getActions().slice(true, false);
                getActions().setSliced(true);
                getLive().setText(String.valueOf(getActions().getLive()));
                getScorelabel().setText("score:" + String.valueOf(getActions().getScore()));
                getLive().setText(String.valueOf(getActions().getLive()));
                System.out.println(getActions().getLive());
                getWatermelon().createSlashTrace(getStartX(), getStartY(), getEndX(), getEndY(), getG());
            });
            getWatermelon().appearFruit(getG());
            getWatermelon().getImgwatermelon().setVisible(true);
            Timeline delay = new Timeline(new KeyFrame(Duration.millis(120), (delayVisible) -> {
                getWatermelon().getImgwatermelon().setVisible(true);
            }));
            delay.setCycleCount(1);
            delay.play();
        })));
        
        getWatermelonAppear().setCycleCount(Timeline.INDEFINITE);
        getWatermelonAppear().play();
    }
     public void bananasAppearTask()
	    {

	        setBananasAppear(new Timeline(new KeyFrame(Duration.millis(2600), (e) -> {
            setImg(getFruit().getImage());
            setBanana(new banana(getImg()[2], 0, 600, getG()));
            getBanana().setActions(getActions());
            getBanana().getImgBanana().setVisible(false);
            getBanana().getImgBanana().setFitWidth(100);
            getBanana().getImgBanana().setFitHeight(150);
            getBanana().getImgBanana().setOnMouseDragEntered((dragEntered) -> {
                setStartX(dragEntered.getX());
                setStartY(dragEntered.getY());
            });
            getBanana().getImgBanana().setOnMouseDragExited((dragExit) -> {
                setEndX(dragExit.getX());
                setEndY(dragExit.getY());
                getBanana().slice(getG(), getStartX(), getStartY());
                getActions().slice(true, false);
                getLive().setText(String.valueOf(getActions().getLive()));
                getScorelabel().setText("score:" + String.valueOf(getActions().getScore()));
                getBanana().createSlashTrace(getStartX(), getStartY(), getEndX(), getEndY(), getG());
            });
            getBanana().appearFruit(getG());
            Timeline delay = new Timeline(new KeyFrame(Duration.millis(2600), (delayVisible) -> {
                getBanana().getImgBanana().setVisible(true);
            }));
            delay.setCycleCount(1);
            delay.play();
        })));
	        
	        getBananasAppear().setCycleCount(Timeline.INDEFINITE);
	        getBananasAppear().play();
	    }
	 public void dragonsAppearTask()
	    {
		 setDragonsAppear(new Timeline(new KeyFrame(Duration.seconds(3), (e) -> {
            setImg(getFruit().getImage());
            setDragon(new dragon(getImg()[4], randomNumber(0, 200), randomNumber(500, 700), getG()));
            getDragon().setActions(getActions());
            getDragon().getImgdragon().setVisible(false);
            getDragon().getImgdragon().setOnMouseDragEntered((dragEntered) -> {
                setStartX(dragEntered.getX());
                setStartY(dragEntered.getY());
            });
            getDragon().getImgdragon().setOnMouseDragExited((dragExit) -> {
                setEndX(dragExit.getX());
                setEndY(dragExit.getY());
                getDragon().slice(getG(), getStartX(), getStartY());
                getActions().slice(true, true);
                getActions().setSliced(true);
                getLive().setText(String.valueOf(getActions().getLive()));
                getScorelabel().setText("score:" + String.valueOf(getActions().getScore()));
                getDragon().createSlashTrace(getStartX(), getStartY(), getEndX(), getEndY(), getG());
            });
            getDragon().appearFruit(getG());
            getLive().setText(String.valueOf(getActions().getLive()));
            Timeline delay = new Timeline(new KeyFrame(Duration.millis(200), (delayVisible) -> {
                getDragon().getImgdragon().setVisible(true);
            }));
            delay.setCycleCount(1);
            delay.play();
        })));
	        
		 getDragonsAppear().setCycleCount(Timeline.INDEFINITE);
		 getDragonsAppear().play();
	    }
	 public void mangosAppearTask()
	    {
		 setMangosAppear(new Timeline(new KeyFrame(Duration.seconds(3), (e) -> {
            setImg(getFruit().getImage());
            setMango(new mango(getImg()[3], randomNumber(0, 200), randomNumber(600,800 ), getG()));
            getMango().setActions(getActions());
            getMango().getImgmango().setVisible(false);
            getMango().getImgmango().setOnMouseDragEntered((dragEntered) -> {
                setStartX(dragEntered.getX());
                setStartY(dragEntered.getY());
                System.out.println(getStartX());
                System.out.println(getStartY());
            });
            getMango().getImgmango().setOnMouseDragExited((dragExit) -> {
            	
                setEndX(dragExit.getX());
                setEndY(dragExit.getY());
                getMango().slice(getG(), getStartX(), getStartY());
                getActions().slice(true, true);
                getActions().setSliced(true);
                getLive().setText(String.valueOf(getActions().getLive()));
                getScorelabel().setText("score:" + String.valueOf(getActions().getScore()));
                getMango().createSlashTrace(getStartX(), getStartY(), getEndX(), getEndY(), getG());
            });
            getMango().appearFruit(getG());
            Timeline delay = new Timeline(new KeyFrame(Duration.millis(3), (delayVisible) -> {
                getMango().getImgmango().setVisible(true);
            }));
            delay.setCycleCount(1);
            delay.play();
        })));
	        
		 getMangosAppear().setCycleCount(Timeline.INDEFINITE);
		 getMangosAppear().play();
	    }
          public void bombAppearTask()
	    {
		
	      setBombsAppear(new Timeline(new KeyFrame(Duration.seconds(3), (e) -> {
            setImg(getBomba().getImage());
            setBomb(new bomb(getImg()[0], randomNumber(0, 200), randomNumber(600,850 ), getG()));
            getBomb().getImgBomb().setVisible(false);
            getBomb().getImgBomb().setOnMouseDragEntered((dragEntered) -> {
                setStartX(dragEntered.getX());
                setStartY(dragEntered.getY());
                System.out.println(getStartX());
                System.out.println(getStartY());
                FadeTransition ft = new FadeTransition(Duration.millis(300), getG());
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.setCycleCount(1);
                ft.setAutoReverse(true);
                ft.play();
                getKiwisAppear().stop();
                getBananasAppear().stop();
                getWatermelonAppear().stop();
                getDragonsAppear().stop();
                getMangosAppear().stop();
                getBombsAppear().stop();
                getBomb2Appear().stop();
                getActions().setLive(0);
                getLive().setText("0");
                try {
                    Endgame();
                } catch (IOException ex) {
                    Logger.getLogger(gamescene.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            getBomb().getImgBomb().setOnMouseDragExited((dragExit) -> {
                setEndX(dragExit.getX());
                setEndY(dragExit.getY());
                getKiwisAppear().stop();
                getBananasAppear().stop();
                getWatermelonAppear().stop();
                getDragonsAppear().stop();
                getMangosAppear().stop();
                getBombsAppear().stop();
                getBomb2Appear().stop();
                getBomb().createSlashTrace(getStartX(), getStartY(), getEndX(), getEndY(), getG());
            });
            getBomb().appearBomb(getG());
            Timeline delay = new Timeline(new KeyFrame(Duration.millis(5), (delayVisible) -> {
                getBomb().getImgBomb().setVisible(true);
            }));
            delay.setCycleCount(1);
            delay.play();
              })));
              
              getBombsAppear().setCycleCount(Timeline.INDEFINITE);
	        getBombsAppear().play();
	    }
	 public void bomb2AppearTask()
	    {
	      setBomb2Appear(new Timeline(new KeyFrame(Duration.seconds(3), (e) -> {
	    	 
            setImg(getBomba().getImage());
            setBomb2(new bomb2(getImg()[1], randomNumber(0, 100), randomNumber(200,500 ), getG()));
            getBomb2().getImgBomb().setVisible(false);
            getBomb2().getImgBomb().setCache(true);
            getBomb2().getImgBomb().setOnMouseDragEntered((dragEntered) -> {
         
   	    	  
                setStartX(dragEntered.getX());
                setStartY(dragEntered.getY());
                System.out.println(getStartX());
                System.out.println(getStartY());
                FadeTransition ft = new FadeTransition(Duration.millis(300), getG());
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.setCycleCount(1);
                ft.setAutoReverse(true);
                ft.play();
          
            });
            getBomb2().getImgBomb().setOnMouseDragExited((dragExit) -> {
            	getBomb2().getImgBomb().setVisible(false);
                setEndX(dragExit.getX());
                setEndY(dragExit.getY());
                getBomb2().createSlashTrace(getStartX(), getStartY(), getEndX(), getEndY(), getG());
                getActions().slice(false, false);
                getScorelabel().setText("score:" + String.valueOf(getActions().getScore()));
            });
            getBomb2().appearBomb2(getG());
         
            Timeline delay = new Timeline(new KeyFrame(Duration.millis(5), (delayVisible) -> {
                getBomb2().getImgBomb().setVisible(false);
                getBomb2().getImgBomb().setVisible(true);
            }));
            delay.setCycleCount(1);
            delay.play();
              })));
              getBomb2Appear().setCycleCount(Timeline.INDEFINITE);
	        getBomb2Appear().play();
	    }
	
         
         public void Endgame() throws IOException{
                    
                 getBomb2().getImgBomb().setVisible(false);
	                getKiwi().getImgKiwi().setVisible(false);
	                
	                getWatermelon().getImgwatermelon().setVisible(false);
	               
	                getMango().getImgmango().setVisible(false);
	                
	                getBanana().getImgBanana().setVisible(false);
	             
	                getDragon().getImgdragon().setVisible(false);
	               
	                getBomb().getImgBomb().setVisible(false);
                           					
                        getKiwisAppear().stop();
	                getBananasAppear().stop();
	                getWatermelonAppear().stop();
	                getDragonsAppear().stop();
	                getMangosAppear().stop();
	                getBombsAppear().stop();
	                getBomb2Appear().stop();
                        
                     
              Image gameoverimg=new Image("/img/Game.png",600,600,true,true);
	     ImageView mg= new ImageView(gameoverimg);
	     Image exit=new Image("/img/exit.png",150,100,true,true);
	     ImageView ex= new ImageView(exit);
                AudioClip  backgroundSound = new AudioClip(this.getClass().getResource("/sounds/gameover.wav").toExternalForm());
	        
	                VBox pauseRoot = new VBox();
	                Label gameover = new Label ();
	                gameover.setGraphic(mg);
	                gameover.setLayoutX(400);
	                gameover.setLayoutY(400);
	                pauseRoot.getChildren().add(gameover);
	                
	                pauseRoot.setStyle("-fx-background-color: rgba(0,0,0, 0);");
	                pauseRoot.setAlignment(Pos.CENTER);
	                pauseRoot.setPadding(new Insets(40));

	                Button OK = new Button();
	                
	                OK.setGraphic(ex);
	                OK.setStyle("-fx-background-color: rgba(0,0,0, 0);");
	                pauseRoot.getChildren().add(OK);
	                OK.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							Platform.exit();
						}
					});
                        
	                Stage popupStage = new Stage(StageStyle.TRANSPARENT);
	                popupStage.initOwner(Mainn.primaryStage);
	                popupStage.initModality(Modality.APPLICATION_MODAL);
	                popupStage.setX(300);
	                popupStage.setY(100);	                
	                popupStage.setScene(new Scene(pauseRoot, Color.TRANSPARENT));
	                backgroundSound.play();
	                popupStage.show();
                     
                    
                        
                        getLive().setText("0");
                        getActions().save();
                       
               
         }
         
         public static double randomNumber(double min,double max){
             double x=(Math.random()*((max-min)+1))+min;
             return x;
         }
	
	public GameObject getFruit() {
		return fruit;
	}

	public void setFruit(GameObject fruit) {
		this.fruit = fruit;
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

    /**
     * @return the startTime
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the z
     */
    public int getZ() {
        return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * @return the seconds
     */
    public Integer getSeconds() {
        return seconds;
    }

    /**
     * @param seconds the seconds to set
     */
    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    /**
     * @return the kiwi
     */
    public kiwi getKiwi() {
        return kiwi;
    }

    /**
     * @param kiwi the kiwi to set
     */
    public void setKiwi(kiwi kiwi) {
        this.kiwi = kiwi;
    }

    /**
     * @return the watermelon
     */
    public Watermelon getWatermelon() {
        return watermelon;
    }

    /**
     * @param watermelon the watermelon to set
     */
    public void setWatermelon(Watermelon watermelon) {
        this.watermelon = watermelon;
    }

    /**
     * @return the banana
     */
    public banana getBanana() {
        return banana;
    }

    /**
     * @param banana the banana to set
     */
    public void setBanana(banana banana) {
        this.banana = banana;
    }

    /**
     * @return the mango
     */
    public mango getMango() {
        return mango;
    }

    /**
     * @param mango the mango to set
     */
    public void setMango(mango mango) {
        this.mango = mango;
    }

    /**
     * @return the dragon
     */
    public dragon getDragon() {
        return dragon;
    }

    /**
     * @param dragon the dragon to set
     */
    public void setDragon(dragon dragon) {
        this.dragon = dragon;
    }

    /**
     * @return the kiwisAppear
     */
    public Timeline getKiwisAppear() {
        return kiwisAppear;
    }

    /**
     * @param kiwisAppear the kiwisAppear to set
     */
    public void setKiwisAppear(Timeline kiwisAppear) {
        this.kiwisAppear = kiwisAppear;
    }

    /**
     * @return the watermelonAppear
     */
    public Timeline getWatermelonAppear() {
        return watermelonAppear;
    }

    /**
     * @param watermelonAppear the watermelonAppear to set
     */
    public void setWatermelonAppear(Timeline watermelonAppear) {
        this.watermelonAppear = watermelonAppear;
    }

    /**
     * @return the bananasAppear
     */
    public Timeline getBananasAppear() {
        return bananasAppear;
    }

    /**
     * @param bananasAppear the bananasAppear to set
     */
    public void setBananasAppear(Timeline bananasAppear) {
        this.bananasAppear = bananasAppear;
    }

    /**
     * @return the mangosAppear
     */
    public Timeline getMangosAppear() {
        return mangosAppear;
    }

    /**
     * @param mangosAppear the mangosAppear to set
     */
    public void setMangosAppear(Timeline mangosAppear) {
        this.mangosAppear = mangosAppear;
    }

    /**
     * @return the dragonsAppear
     */
    public Timeline getDragonsAppear() {
        return dragonsAppear;
    }

    /**
     * @param dragonsAppear the dragonsAppear to set
     */
    public void setDragonsAppear(Timeline dragonsAppear) {
        this.dragonsAppear = dragonsAppear;
    }

    /**
     * @return the bombsAppear
     */
    public Timeline getBombsAppear() {
        return bombsAppear;
    }

    /**
     * @param bombsAppear the bombsAppear to set
     */
    public void setBombsAppear(Timeline bombsAppear) {
        this.bombsAppear = bombsAppear;
    }

    /**
     * @return the bomb2Appear
     */
    public Timeline getBomb2Appear() {
        return bomb2Appear;
    }

    /**
     * @param bomb2Appear the bomb2Appear to set
     */
    public void setBomb2Appear(Timeline bomb2Appear) {
        this.bomb2Appear = bomb2Appear;
    }

    /**
     * @return the backgroundSound
     */
    public AudioClip getBackgroundSound() {
        return backgroundSound;
    }

    /**
     * @param backgroundSound the backgroundSound to set
     */
    public void setBackgroundSound(AudioClip backgroundSound) {
        this.backgroundSound = backgroundSound;
    }

    /**
     * @return the actions
     */
    public Actions getActions() {
        return actions;
    }

    /**
     * @param actions the actions to set
     */
    public void setActions(Actions actions) {
        this.actions = actions;
    }

    /**
     * @return the scorelabel
     */
    public Label getScorelabel() {
        return scorelabel;
    }

    /**
     * @param scorelabel the scorelabel to set
     */
    public void setScorelabel(Label scorelabel) {
        this.scorelabel = scorelabel;
    }

    /**
     * @return the timerLabel
     */
    public Label getTimerLabel() {
        return timerLabel;
    }

    /**
     * @param timerLabel the timerLabel to set
     */
    public void setTimerLabel(Label timerLabel) {
        this.timerLabel = timerLabel;
    }

    /**
     * @return the live
     */
    public Label getLive() {
        return live;
    }

    /**
     * @param live the live to set
     */
    public void setLive(Label live) {
        this.live = live;
    }

    /**
     * @return the heart3
     */
    public ImageView getHeart3() {
        return heart3;
    }

    /**
     * @param heart3 the heart3 to set
     */
    public void setHeart3(ImageView heart3) {
        this.heart3 = heart3;
    }

    /**
     * @return the heart2
     */
    public ImageView getHeart2() {
        return heart2;
    }

    /**
     * @param heart2 the heart2 to set
     */
    public void setHeart2(ImageView heart2) {
        this.heart2 = heart2;
    }

    /**
     * @return the heart
     */
    public ImageView getHeart() {
        return heart;
    }

    /**
     * @param heart the heart to set
     */
    public void setHeart(ImageView heart) {
        this.heart = heart;
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

    /**
     * @return the g
     */
    public Group getG() {
        return g;
    }

    /**
     * @param g the g to set
     */
    public void setG(Group g) {
        this.g = g;
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
     * @return the hearts
     */
    public Image getHearts() {
        return hearts;
    }

    /**
     * @param hearts the hearts to set
     */
    public void setHearts(Image hearts) {
        this.hearts = hearts;
    }

    /**
     * @return the bomba
     */
    public GameObject getBomba() {
        return bomba;
    }

    /**
     * @param bomba the bomba to set
     */
    public void setBomba(GameObject bomba) {
        this.bomba = bomba;
    }

    /**
     * @return the bomb
     */
    public bomb getBomb() {
        return bomb;
    }

    /**
     * @param bomb the bomb to set
     */
    public void setBomb(bomb bomb) {
        this.bomb = bomb;
    }

    /**
     * @return the bomb2
     */
    public bomb2 getBomb2() {
        return bomb2;
    }

    /**
     * @param bomb2 the bomb2 to set
     */
    public void setBomb2(bomb2 bomb2) {
        this.bomb2 = bomb2;
    }
	
}