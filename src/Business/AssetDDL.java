package Business;

/*
 * @author Keith Emery
 * IS 287
 * Spring, 2017
 * Instructor P. Daniel
 * Assignment 3
 */
public class AssetDDL extends AssetDeclining {
    public static final double FACTOR = 2.0;
    
    public AssetDDL() {
        super();        
    }

    public AssetDDL(String name, double cost, double salvage, int life) {
        super(name, cost, salvage, life, AssetDDL.FACTOR);        
        
        if (super.isValid()) {
            // ?
        }
    }   
    
}
