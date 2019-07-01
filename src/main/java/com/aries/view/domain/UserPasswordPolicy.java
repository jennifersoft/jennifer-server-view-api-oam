package com.aries.view.domain;

import aries.lang.TMAP;

/**
 * @version 0.1
 */
public class UserPasswordPolicy
{
    private boolean enablePasswordPolicy;
    private boolean delayPeriod;
    private short passwordLength;
    private short minUppercase;
    private short minLowercase;
    private short minDigit;
    private short minSpecial;
    private short changingDate;

    public short getSignLockCount()
    {
        return signLockCount;
    }

    public void setSignLockCount(short signLockCount)
    {
        this.signLockCount = signLockCount;
    }

    private short signLockCount;

    public boolean isEnablePasswordPolicy()
    {
        return enablePasswordPolicy;
    }

    public void setEnablePasswordPolicy(boolean enablePasswordPolicy)
    {
        this.enablePasswordPolicy = enablePasswordPolicy;
    }

    public boolean isDelayPeriod()
    {
        return delayPeriod;
    }

    public void setDelayPeriod(boolean delayPeriod)
    {
        this.delayPeriod = delayPeriod;
    }

    public short getPasswordLength()
    {
        return passwordLength;
    }

    public void setPasswordLength(short passwordLength)
    {
        this.passwordLength = passwordLength;
    }

    public short getMinUppercase()
    {
        return minUppercase;
    }

    public void setMinUppercase(short minUppercase)
    {
        this.minUppercase = minUppercase;
    }

    public short getMinLowercase()
    {
        return minLowercase;
    }

    public void setMinLowercase(short minLowercase)
    {
        this.minLowercase = minLowercase;
    }

    public short getMinDigit()
    {
        return minDigit;
    }

    public void setMinDigit(short minDigit)
    {
        this.minDigit = minDigit;
    }

    public short getMinSpecial()
    {
        return minSpecial;
    }

    public void setMinSpecial(short minSpecial)
    {
        this.minSpecial = minSpecial;
    }

    public short getChangingDate()
    {
        return changingDate;
    }

    public void setChangingDate(short changingDate)
    {
        this.changingDate = changingDate;
    }

    public boolean checkPasswordLength()
    {
        short total = (short) (this.minDigit + this.minLowercase + this.minSpecial + this.minUppercase);

        return total <= this.passwordLength;
    }

    public TMAP toMap()
    {
        TMAP map = new TMAP();

        return map;
    }

    public UserPasswordPolicy fromMap(TMAP map)
    {
        return this;
    }
}