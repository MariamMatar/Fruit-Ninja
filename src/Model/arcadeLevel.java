package Model;

/**
 *
 * @author scs
 */
public class arcadeLevel  implements IStrategy{
    public arcadeLevel() {
    }

    @Override
    public int velocityX() {
    
        return 2460;
    }

    @Override
    public int velocityY() {
        return 1230;
    }
}