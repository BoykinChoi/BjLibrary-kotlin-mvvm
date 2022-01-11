package com.boykinchoi.star.reflect;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/12/25 9:42
 */
public class Son extends Father {
    private String mSonName;
    protected int mSonAge;
    public String mSonBirthDay;

    public void printSonMsg() {
        System.out.println("Son msg ---- name=" + mSonName + ";age=" + mSonAge);
    }

    public String getSonName() {
        return mSonName;
    }

    public void setSonName(String mSonName) {
        this.mSonName = mSonName;
    }

    public int getSonAge() {
        return mSonAge;
    }

    public void setSonAge(int mSonAge) {
        this.mSonAge = mSonAge;
    }

    public String getSonBirthDay() {
        return mSonBirthDay;
    }

    public void setSonBirthDay(String mSonBirthDay) {
        this.mSonBirthDay = mSonBirthDay;
    }
}
