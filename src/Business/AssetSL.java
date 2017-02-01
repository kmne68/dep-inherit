/*
 * @author Keith Emery
 * IS 287
 * Spring, 2017
 * Instructor P. Daniel
 * Assignment 2
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
        
        if (super.isValid()) {
            build();
        }
    }

    public void build() {
        if (!super.isValid()) {
            this.built = false;
        } else {
            try {
                this.beginningBalance = new double[super.getLifeOfItem()];
                this.endingBalance = new double[super.getLifeOfItem()];

                this.annualDepreciation = (super.getAssetCost() - super.getSalvageValue()) / super.getLifeOfItem();
                this.beginningBalance[0] = super.getAssetCost();   // beginning straight line           

                for (int year = 0; year < super.getLifeOfItem(); year++) {
                    // remember to right justify values.               
                    if (year > 0) {
                        this.beginningBalance[year] = this.endingBalance[year - 1];
                    }
                    this.endingBalance[year] = this.beginningBalance[year] - this.annualDepreciation;
                }
                this.built = true;

            } catch (Exception e) {
                //    this.errorMessage = "Build error: " + e.getMessage();
                this.built = false;
            }
        }
    } // end build

    public double getAnnualDepreciation() {

        if (!this.built) {
            if (isValid()) {
                build();
            }
            if (!this.built) {
                return -1;
            }
        }

        return this.annualDepreciation;
    } // getAnnualDepreciation()

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

} // end class AssetSL.java
