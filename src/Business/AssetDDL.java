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
    private double[] annualDepreciation;
    private double[] endingBalance;

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
                this.beginningBalance = new double[super.getLifeOfItem()];
                this.endingBalance = new double[super.getLifeOfItem()];
                this.annualDepreciation = new double[super.getLifeOfItem()];
                
                double doubleDecliningRate = (1 / super.getLifeOfItem()) * 2.0;
                
                for(int year = 1; year < getLifeOfItem(); year++) {
                    
                }
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

        return this.annualDepreciation[year - 1];
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
