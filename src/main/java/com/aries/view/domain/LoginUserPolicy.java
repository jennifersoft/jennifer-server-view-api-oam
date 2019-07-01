package com.aries.view.domain;

import aries.lang.TMAP;

public class LoginUserPolicy
{
    private int totalSessionCount;
    private boolean isDuplicatedLogin;
    private short signLockCount;

    public short getSignLockCount()
    {
        return signLockCount;
    }

    public void setSignLockCount(short signLockCount)
    {
        this.signLockCount = signLockCount;
    }

    public int getTotalSessionCount()
    {
        return totalSessionCount;
    }

    public void setTotalSessionCount(int totalSessionCount)
    {
        this.totalSessionCount = totalSessionCount;
    }

    public boolean isDuplicatedLogin()
    {
        return isDuplicatedLogin;
    }

    public void setIsDuplicatedLogin(boolean isDuplicatedLogin)
    {
        this.isDuplicatedLogin = isDuplicatedLogin;
    }

    public TMAP toMap()
    {
        TMAP map = new TMAP();

        return map;
    }

    public LoginUserPolicy fromMap(TMAP map)
    {
        return this;
    }
}
