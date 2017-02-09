/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author Keith
 */
public class AssetOHDL extends AssetDeclining {
    
    private static final double FACTOR = 1.5;

    public AssetOHDL() {
        super();

    }

    public AssetOHDL(String name, double cost, double salvage, int life) {
        super(name, cost, salvage, life, AssetOHDL.FACTOR);        
    }
}
