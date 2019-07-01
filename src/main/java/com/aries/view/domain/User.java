package com.aries.view.domain;

import aries.lang.TMAP;

/**
 * @version 0.1
 */
public class User
{
    private String id;
    private String groupId;
    private String password;
    private String name;
    private String email;
    private String company;
    private String dept;
    private String jobTitle;
    private String phone;
    private String cellphone;
    private String lastIp;
    private String lastDt;
    private String lastTm;
    private int loginCnt = 0;
    private int loginFailCnt = 0;
    private boolean loginLock = false;
    private int defaultMenuId = 0;
    private String defaultMenuName;
    private String defaultMenuCode;
    private String userType;
    private String language;
    private Group group;

    private String changePwdDt; // 최근 비밀번호 변경날짜
    private String checkAlarmDt = "0"; // 최근 알림 확인날짜
    private String themeName = "classic"; // 화면 테마 이름

    private boolean browserPush = false;
    private boolean desktopPush = false;
    private boolean soundPush = false;

    private boolean emailPush = false;

    private int expandedDashboardTargetCount = 10; // 확장형 대시보드 전환 인스턴스 개수

    @Deprecated
    private boolean normalSound = false;
    @Deprecated
    private boolean warningSound = false;
    @Deprecated
    private boolean fatalSound = false;

    private boolean adapterMode = false;

    public int getExpandedDashboardTargetCount()
    {
        return expandedDashboardTargetCount;
    }

    public void setExpandedDashboardTargetCount(int expandedDashboardTargetCount)
    {
        this.expandedDashboardTargetCount = expandedDashboardTargetCount;
    }

    public boolean isSoundPush()
    {
        return soundPush;
    }

    public void setSoundPush(boolean soundPush)
    {
        this.soundPush = soundPush;
    }

    public String getCheckAlarmDt()
    {
        return checkAlarmDt;
    }

    public void setCheckAlarmDt(String checkAlarmDt)
    {
        this.checkAlarmDt = checkAlarmDt;
    }

    public String getThemeName()
    {
        // TODO: ARIES-7244 테마 이름 변경에 따른 호환성 처리
        if (!themeName.equals("dark"))
        {
            return "classic";
        }

        return themeName;
    }

    public void setThemeName(String themeName)
    {
        this.themeName = themeName;
    }

    public boolean isLoginLock()
    {
        return loginLock;
    }

    public void setLoginLock(boolean loginLock)
    {
        this.loginLock = loginLock;
    }

    public String getChangePwdDt()
    {
        return changePwdDt;
    }

    public void setChangePwdDt(String changePwdDt)
    {
        this.changePwdDt = changePwdDt;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCompany()
    {
        return company;
    }

    public void setCompany(String company)
    {
        this.company = company;
    }

    public String getDept()
    {
        return dept;
    }

    public void setDept(String dept)
    {
        this.dept = dept;
    }

    public String getJobTitle()
    {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getCellphone()
    {
        return cellphone;
    }

    public void setCellphone(String cellphone)
    {
        this.cellphone = cellphone;
    }

    public String getLastIp()
    {
        return lastIp;
    }

    public void setLastIp(String lastIp)
    {
        this.lastIp = lastIp;
    }

    public String getLastDt()
    {
        return lastDt;
    }

    public void setLastDt(String lastDt)
    {
        this.lastDt = lastDt;
    }

    public String getLastTm()
    {
        return lastTm;
    }

    public void setLastTm(String lastTm)
    {
        this.lastTm = lastTm;
    }

    public int getLoginCnt()
    {
        return loginCnt;
    }

    public void setLoginCnt(int loginCnt) { this.loginCnt = loginCnt; }

    public int getLoginFailCnt()
    {
        return loginFailCnt;
    }

    public void setLoginFailCnt(int loginFailCnt) { this.loginFailCnt = loginFailCnt; }

    public int getDefaultMenuId()
    {
        return defaultMenuId;
    }

    public void setDefaultMenuId(int defaultMenuId)
    {
        this.defaultMenuId = defaultMenuId;
    }

    public String getDefaultMenuName()
    {
        return defaultMenuName;
    }

    public void setDefaultMenuName(String defaultMenuName)
    {
        this.defaultMenuName = defaultMenuName;
    }

    public String getDefaultMenuCode()
    {
        return defaultMenuCode;
    }

    public void setDefaultMenuCode(String defaultMenuCode)
    {
        this.defaultMenuCode = defaultMenuCode;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }

    public Group getGroup()
    {
        return group;
    }

    public void setGroup(Group group)
    {
        this.group = group;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public boolean isBrowserPush()
    {
        return browserPush;
    }

    public void setBrowserPush(boolean browserPush)
    {
        this.browserPush = browserPush;
    }

    public boolean isDesktopPush()
    {
        return desktopPush;
    }

    public void setDesktopPush(boolean desktopPush)
    {
        this.desktopPush = desktopPush;
    }

    public boolean isNormalSound()
    {
        return normalSound;
    }

    public void setNormalSound(boolean normalSound)
    {
        this.normalSound = normalSound;
    }

    public boolean isWarningSound()
    {
        return warningSound;
    }

    public void setWarningSound(boolean warningSound)
    {
        this.warningSound = warningSound;
    }

    public boolean isFatalSound()
    {
        return fatalSound;
    }

    public void setFatalSound(boolean fatalSound)
    {
        this.fatalSound = fatalSound;
    }

    public boolean isEmailPush()
    {
        return emailPush;
    }

    public void setEmailPush(boolean emailPush)
    {
        this.emailPush = emailPush;
    }

    public boolean isAdapterMode()
    {
        return adapterMode;
    }

    public void setAdapterMode(boolean adapterMode)
    {
        this.adapterMode = adapterMode;
    }

    public TMAP toMap()
    {
        TMAP map = new TMAP();


        return map;
    }

    public User fromMap(TMAP map)
    {

        return this;
    }
}