package Model;

/**
 *
 * @author scs
 */
public class EasyLevel implements IStrategy{

    public EasyLevel() {
    }

    @Override
    public int velocityX() {
    
        return 3660;
    }

    @Override
    public int velocityY() {
        return 1830;
    }
    
    
}