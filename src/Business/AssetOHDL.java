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
public class AssetOHDL extends Asset {

    private double[] beginningBalance;
    private double[] endingBalance;
    private double[] annualDepreciation;
    private boolean built;

    public AssetOHDL() {
        super();

        this.built = false;
    }

    public AssetOHDL(String name, double cost, double salvage, int life) {
        super(name, cost, salvage, life);

        if (super.isValid()) {
            build();
        }
    }

    private void build() {
        if (!super.isValid()) {
            this.built = false;
        } else {
            try {
                this.beginningBalance = new double[super.getLifeOfItem()];
                this.endingBalance = new double[super.getLifeOfItem()];
                this.annualDepreciation = new double[super.getLifeOfItem()];

                double rateOneHalfDeclining = (1.0 / super.getLifeOfItem()) * 1.5;
                double depreciationStraightLine = (super.getAssetCost() - super.getSalvageValue()) / super.getLifeOfItem();

                this.beginningBalance[0] = super.getAssetCost();
                for (int year = 0; year < super.getLifeOfItem(); year++) {
                    if (year > 0) {
                        this.beginningBalance[year] = this.endingBalance[year - 1];
                    }
                    double tempOHDL = this.beginningBalance[year] * rateOneHalfDeclining;
                    if (tempOHDL < depreciationStraightLine) {
                        tempOHDL = depreciationStraightLine;
                    }
                    if ((this.beginningBalance[year] - tempOHDL) < super.getSalvageValue()) {
                        tempOHDL = this.beginningBalance[year] - super.getSalvageValue();
                    }
                    this.annualDepreciation[year] = tempOHDL;
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
