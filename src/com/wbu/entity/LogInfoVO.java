package com.wbu.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class LogInfoVO {

    private LogInfo logInfo;
    private List<LogInfo> logInfoList;
    private Map<String,String> conditionMap;
    private Set<String> titileSet;
    public LogInfoVO() {
        super();
    }
    public LogInfoVO(LogInfo logInfo, List<LogInfo> logInfoList, Map<String, String> conditionMap,
            Set<String> titileSet) {
        super();
        this.logInfo = logInfo;
        this.logInfoList = logInfoList;
        this.conditionMap = conditionMap;
        this.titileSet = titileSet;
    }
    public LogInfo getLogInfo() {
        return logInfo;
    }
    public void setLogInfo(LogInfo logInfo) {
        this.logInfo = logInfo;
    }
    public List<LogInfo> getLogInfoList() {
        return logInfoList;
    }
    public void setLogInfoList(List<LogInfo> logInfoList) {
        this.logInfoList = logInfoList;
    }
    public Map<String, String> getConditionMap() {
        return conditionMap;
    }
    public void setConditionMap(Map<String, String> conditionMap) {
        this.conditionMap = conditionMap;
    }
    public Set<String> getTitileSet() {
        return titileSet;
    }
    public void setTitileSet(Set<String> titileSet) {
        this.titileSet = titileSet;
    }

}
