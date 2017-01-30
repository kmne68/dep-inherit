/*
 * @author Keith Emery
 * IS 287
 * Spring, 2017
 * Instructor P. Daniel
 */
package Business;

/**
 *
 * @author Keith
 */
public class AssetSL extends Asset {
    
    private double[] beginningBalance;
    private double[] endingBalance;
    private double annualDepreciation;
    private boolean built;
        
    
    public AssetSL() {
        super();
        
        this.built = false;
    }
    
    public AssetSL(String name, double cost, double salvage, int life) {
        super(name, cost, salvage, life);
        
        build();
    }
    
    
    public void build() {

        try {
            this.beginningBalance = new double[this.getLifeOfItem()];
            this.endingBalance = new double[this.getLifeOfItem()];

            this.annualDepreciation = (super.getAssetCost() - super.getSalvageValue()) / super.getLifeOfItem();

            this.beginningBalance[0] = super.getAssetCost();   // beginning straight line           
            
            for (int year = 0; year < super.getLifeOfItem(); year++) {
                // remember to right justify values.               
                if (year > 0) {
                    this.beginningBalance[year] = this.endingBalance[year - 1];
                }
                this.endingBalance[year] = this.beginningBalance[year] - this.annualDepreciation;
           }
            built = true;
        
        } catch (Exception e) {
        //    this.errorMessage = "Build error: " + e.getMessage();
            this.built = false;
        }
    } // end build
    
}
