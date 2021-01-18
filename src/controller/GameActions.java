package controller;

import Model.GameObject;
import java.io.IOException;
import javafx.scene.image.Image;

public interface GameActions {

  public int getScore();
  
  public Boolean hasMovedOffScreen();  
 
  public void slice(boolean isSliced , boolean bonus);  

  public void updateLives (int Live);
  
  public void updatescore(int score);
  
  public int levelvelocityX();
  
  public int levelvelocityY();

  public void ResetGame ();
          
  public void save() throws IOException;
  
  public void load() throws IOException;

}