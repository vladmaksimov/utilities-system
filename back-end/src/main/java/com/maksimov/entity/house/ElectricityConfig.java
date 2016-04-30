package com.maksimov.entity.house;

import com.maksimov.entity.Base;

import javax.persistence.Entity;

/**
 * Created on 11.04.2016.
 */

public class ElectricityConfig extends Base {

    private int upToHundred;
    private int hundredOrMore;
    private int moreThanSixHundred;

    public int getUpToHundred() {
        return upToHundred;
    }

    public void setUpToHundred(int upToHundred) {
        this.upToHundred = upToHundred;
    }

    public int getHundredOrMore() {
        return hundredOrMore;
    }

    public void setHundredOrMore(int hundredOrMore) {
        this.hundredOrMore = hundredOrMore;
    }

    public int getMoreThanSixHundred() {
        return moreThanSixHundred;
    }

    public void setMoreThanSixHundred(int moreThanSixHundred) {
        this.moreThanSixHundred = moreThanSixHundred;
    }
}
