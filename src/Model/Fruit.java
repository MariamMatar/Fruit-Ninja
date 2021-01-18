package Model;

import java.util.Random;
import javax.imageio.ImageIO;
import javafx.scene.image.Image;

public class Fruit implements GameObject{

	private Image[] image=new Image[5];
	
	public Fruit() {
	}

	@Override
	public Image[] getImage() {
		
       Image kiwi = new Image("/img/Kiwi.png");
       Image watermelon = new Image("/img/watermelon.png");
       Image banana = new Image("/img/Banana.png");
       Image mango = new Image("/img/Red_Apple_1.png");
       Image dragon = new Image("/img/download.png");
      
       image[0]= kiwi;
       image[1]= watermelon;
       image[2]=banana;
       image[3]=mango;
       image[4]=dragon;
       
		return image;
	}

    /**
     * @param image the image to set
     */
    public void setImage(Image[] image) {
        this.image = image;
    }
	
	
}
