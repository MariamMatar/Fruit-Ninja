package Model;

/**
 *
 * @author scs
 */
public class HardLevel implements IStrategy{

   public HardLevel() {
    }

    @Override
    public int velocityX() {
    
        return 1500;
    }

    @Override
    public int velocityY() {
        return 750;
    }
}