package Model;

import java.util.Random;
import javax.imageio.ImageIO;
import javafx.scene.image.Image;

public class Bombs implements GameObject{
	
	Image[] image=new Image[2];
	
	public Bombs() {
	}


	@Override
	public Image[] getImage() {
		
       Image bomba1 = new Image("/img/Bomb.gif");
       Image bomba2 = new Image("/img/nonfatal.png");
       
      
       image[0]= bomba1;
       image[1]= bomba2;

       
		return image;
	}
	
	
}