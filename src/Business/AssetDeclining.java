package Business;

/*
 * @author Keith Emery
 * IS 287
 * Spring, 2017
 * Instructor P. Daniel
 * Assignment 2
 */
public class AssetDeclining extends Asset {

    private double[] beginningBalance;
    private double[] endingBalance;
    private double[] annualDepreciation;
    private boolean built;
    
    private double rateFactor;
    
    public AssetDeclining() {
        super();
        
    //    this.built = false;
    }

    public AssetDeclining(String name, double cost, double salvage, int life, double rate) {
        super(name, cost, salvage, life);
        this.rateFactor = rate;

        if (super.isValid()) {
            build();
        }
    }
    
    
    private void build() {
       
        if(!super.isValid()) {
            this.built = false;
        } else {
            try {
                this.beginningBalance = new double[super.getLifeOfItem()];
                this.endingBalance = new double[super.getLifeOfItem()];
                this.annualDepreciation = new double[super.getLifeOfItem()];
                
                double rateDoubleDeclining = (1.0 / super.getLifeOfItem()) * rateFactor;
                double depreciationStraightLine = (super.getAssetCost() - super.getSalvageValue()) / super.getLifeOfItem();
                
                this.beginningBalance[0] = super.getAssetCost();
                for(int year = 0; year < super.getLifeOfItem(); year++) {
                    if(year > 0) {
                        this.beginningBalance[year] = this.endingBalance[year - 1];
                    }
                    double tempDoubleDeclining = this.beginningBalance[year] * rateDoubleDeclining;
                    if(tempDoubleDeclining < depreciationStraightLine) {
                        tempDoubleDeclining = depreciationStraightLine;
                    }
                    if((this.beginningBalance[year] - tempDoubleDeclining) < super.getSalvageValue()) {
                        tempDoubleDeclining = this.beginningBalance[year] - super.getSalvageValue();
                    }
                    this.annualDepreciation[year] = tempDoubleDeclining;
                    this.endingBalance[year] = this.beginningBalance[year] - this.annualDepreciation[year];                            
                }
                this.built = true;
                
            } catch (Exception e) {
                super.setErrorMessage("Build error: " + e.getMessage());
                this.built = false;
            }
        }        
    }    

    
    public double getAnnualDepreciation(int year) {

        if (!this.built) {
            build();
            if (!this.built) {
                return -1;
            }
    } 
        return this.annualDepreciation[year - 1];
    }
    
    
    public double getBeginningBalance(int year) {

        if (!this.built) {
                build();
            if (!this.built) {
                return -1;
            }
        }
        return beginningBalance[year - 1];
    } // end getBeginningBalance()

    
    public double getEndingBalance(int year) {

        if (!this.built) {
            if (isValid()) {
                build();
            }
            if (!this.built) {
                return -1;
            }
        }
        return endingBalance[year - 1];
    } // end getEndingBalance()  
    
    
}
