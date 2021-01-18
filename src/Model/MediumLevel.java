package Model;

/**
 *
 * @author scs
 */
public class MediumLevel implements IStrategy{

    public MediumLevel() {
    }

    @Override
    public int velocityX() {
    
        return 2000;
    }

    @Override
    public int velocityY() {
        return 1000;
    }
    
    
}