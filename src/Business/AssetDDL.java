package Business;

/*
 * @author Keith Emery
 * IS 287
 * Spring, 2017
 * Instructor P. Daniel
 * Assignment 2
 */
public class AssetDDL extends Asset {

    private double[] beginningBalance;
    private double[] endingBalance;
    private double annualDepreciation;
    private boolean built;

    public AssetDDL() {
        super();
        
        this.built = false;
    }

    public AssetDDL(String name, double cost, double salvage, int life) {
        super(name, cost, salvage, life);

        if (super.isValid()) {
            build();
        }
    }
    
    
    private void build() {
        if(!super.isValid()) {
            this.built = false;
        } else {
            try {
                double depreciationStraightLine = ((super.getAssetCost() - super.getSalvageValue()) / super.getLifeOfItem());
                this.beginningBalance = new double[super.getLifeOfItem()];
                this.endingBalance = new double[super.getLifeOfItem()];
                
                this.annualDepreciation = (1.0 / super.getLifeOfItem()) * 2;
                this.beginningBalance[0] = super.getAssetCost();
                
                for(int year = 0; year < super.getLifeOfItem(); year++) {
                    if(year > 0) {
                        this.beginningBalance[year] = this.endingBalance[year - 1];
                    }
                    double depreciationWork = this.beginningBalance[year] * annualDepreciation;
                    if (depreciationWork < depreciationStraightLine) {
                        depreciationWork = depreciationStraightLine;
                    }
                    if ((this.beginningBalance[year] - depreciationWork) < super.getSalvageValue()) {
                    depreciationWork = this.beginningBalance[year] - super.getSalvageValue();
                    }
                    this.annualDepreciation = depreciationWork;
                    this.endingBalance[year] = this.beginningBalance[year] - this.annualDepreciation;
                                                        
                }
                this.built = true;
                
            } catch (Exception e) {
                this.built = false;
            }
        }        
    }      
    

    
        public double getAnnualDepreciation(int year) {

        if (!this.built) {
            if (isValid()) {
                build();
            }
            if (!this.built) {
                return -1;
            }
    } 

        return this.annualDepreciation;
    }
    
        
        
        
    
    public double getBeginningBalance(int year) {

        if (!this.built) {
            if (isValid()) {
                build();
            }
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
