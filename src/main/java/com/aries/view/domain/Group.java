package com.aries.view.domain;

import aries.lang.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Group
{
    private String id;
    private String name;
    private String target;

    //ARIES-4293
    //기존의 기능별 권한은 short[] 으로 선언되어 나열되 있었다.
    //이것을 5.0.12에서부터 사용하는 GroupV1에서는 functionalAuthority로 대체해서 사용한다.
    private short[] permission;

    //key값은 PermissionDef를 참조, value는 현재 true, false만 존재하나 차후에 다른값이 사용될수 있다.
    private Map<Short, Object> functionalAuthority = new HashMap<>();
    private Map<String, String[]> accessibleDashboardURLArray = new HashMap<>();
    private Map<String, String[]> accessibleRealtimeURLArray = new HashMap<>();
    private Map<String, String[]> accessibleAnalysisURLArray = new HashMap<>();
    private Map<String, String[]> accessibleStatisticsURLArray = new HashMap<>();
    private Map<String, String[]> accessibleManagementURLArray = new HashMap<>();
    private Map<String, String[]> accessibleReportURLArray = new HashMap<>();
    private Map<String, Boolean> accessibleUserDefine = new HashMap<>();

    public Map<String, Boolean> getAccessibleUserDefine()
    {
        return accessibleUserDefine;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTarget()
    {
        if (target == null || target.equals(""))
        {
            return "{}";
        }

        return target;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    public Map<String, String[]> getAccessibleDashboardURLArray()
    {
        return accessibleDashboardURLArray;
    }

    public void setAccessibleDashboardURLArray(Map<String, String[]> accessbleMenus)
    {
        this.accessibleDashboardURLArray = accessbleMenus;
    }

    public Map<String, String[]> getAccessibleRealtimeURLArray()
    {
        return accessibleRealtimeURLArray;
    }

    public void setAccessibleRealtimeURLArray(Map<String, String[]> accessbleRealtimeURLArray)
    {
        this.accessibleRealtimeURLArray = accessbleRealtimeURLArray;
    }

    public Map<String, String[]> getAccessibleAnalysisURLArray()
    {
        return accessibleAnalysisURLArray;
    }

    public void setAccessibleAnalysisURLArray(Map<String, String[]> accessbleAnalysisURLArray)
    {
        this.accessibleAnalysisURLArray = accessbleAnalysisURLArray;
    }

    public Map<String, String[]> getAccessibleStatisticsURLArray()
    {
        return accessibleStatisticsURLArray;
    }

    public void setAccessibleStatisticsURLArray(Map<String, String[]> accessbleStatisticsURLArray)
    {
        this.accessibleStatisticsURLArray = accessbleStatisticsURLArray;
    }

    public Map<String, String[]> getAccessibleManagementURLArray()
    {
        return accessibleManagementURLArray;
    }

    public void setAccessibleManagementURLArray(Map<String, String[]> accessbleManagementURLArray)
    {
        this.accessibleManagementURLArray = accessbleManagementURLArray;
    }

    public Map<String, String[]> getAccessibleReportURLArray()
    {
        return accessibleReportURLArray;
    }

    public void setAccessibleReportURLArray(Map<String, String[]> accessbleReportURLArray)
    {
        this.accessibleReportURLArray = accessbleReportURLArray;
    }

    public String[] getDashboard()
    {
        return null;
    }

    public String[] getRealtime()
    {
        return null;
    }

    public String[] getAnalysis()
    {
        return null;
    }

    public String[] getStatistics()
    {
        return null;
    }

    public String[] getReport()
    {
        return null;
    }


    public String[] getManagement()
    {
        return null;
    }

    private String[] getDashboard(String platform)
    {
        return this.accessibleDashboardURLArray.get(platform);
    }

    private String[] getRealtime(String platform)
    {
        return this.accessibleRealtimeURLArray.get(platform);
    }

    private String[] getAnalysis(String platform)
    {
        return this.accessibleAnalysisURLArray.get(platform);
    }

    private String[] getStatistics(String platform)
    {
        return this.accessibleStatisticsURLArray.get(platform);
    }

    private String[] getManagement(String platform)
    {
        return this.accessibleManagementURLArray.get(platform);
    }

    private String[] getReport(String platform)
    {
        return this.accessibleReportURLArray.get(platform);
    }

    public void setDashboard(String[] dashboard)
    {
    }

    public void setRealtime(String[] realtime)
    {
    }

    public void setAnalysis(String[] analysis)
    {
    }

    public void setStatistics(String[] statistics)
    {
    }

    public void setManagement(String[] management)
    {
    }

    public void setReport(String[] report)
    {
    }

    public void setUserDefine(boolean accessible)
    {
    }

    public Boolean getUserDefine()
    {
        return false;
    }

    public String[] getAccessibleMenus(String type, String platform)
    {


        return getDashboard(platform);
    }

    /**
     * 기존 로직을 수행하기 위해 변경
     */
    public void setPermission(short[] permission)
    {
        Arrays.sort(permission);

        for (short aPermission : permission)
        {
            functionalAuthority.put(aPermission, true);
        }

        for (Short key : functionalAuthority.keySet())
        {
            functionalAuthority.put(key, Arrays.binarySearch(permission, key) > -1);
        }
    }

    public short[] getPermission()
    {
        return permission;
    }

    public Map<Short, Object> getFunctionalAuthority()
    {
        return functionalAuthority;
    }

    public TMAP toMap()
    {
        TMAP map = new TMAP();

        return map;
    }

    public Group fromMap(TMAP map)
    {
        return this;
    }
}
