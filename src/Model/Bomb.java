package Model;

import Model.GameObject;
import javafx.scene.image.Image;

public class Bomb implements GameObject{

    @Override
    public Image[] getImage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	public enum bomb {
      fatal,nonfatal
		}
	bomb bomb;
	public Enum getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	 
	 
}