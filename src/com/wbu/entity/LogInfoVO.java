package com.wbu.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LogInfoVO implements Serializable {

	private static final long serialVersionUID=1L;
    private LogInfo logInfo;
    public LogInfoVO(LogInfo logInfo) {
        super();
        this.logInfo = logInfo;
    }

    private List<LogInfo> logInfoList;
    private Map<String,String> conditionMap;
    private static Set<String> titleSet=new HashSet();
    static {
    	titleSet.add("日志编号");
        titleSet.add("信息类型");
    	titleSet.add("记录时间");
    	titleSet.add("登录ip");
    	titleSet.add("异常信息");
    }

    public LogInfoVO(Map<String, String> conditionMap) {
        super();             
        this.conditionMap = conditionMap;
    }

    public LogInfoVO() {
        super();
    }
    public LogInfoVO(LogInfo logInfo, List<LogInfo> logInfoList, Map<String, String> conditionMap,
            Set<String> titileSet) {
        super();
        this.logInfo = logInfo;
        this.logInfoList = logInfoList;
        this.conditionMap = conditionMap;
        this.titleSet = titileSet;
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
        return titleSet;
    }
    public void setTitileSet(Set<String> titileSet) {
        this.titleSet = titileSet;
    }

    public Object getInstance(){
        if(null!=this.logInfo){
            return this.logInfo;
        }
        if(null!=this.logInfoList){
            return this.logInfoList;
        }
        if(null!=this.conditionMap){
            return this.conditionMap;
        }
        if(null!=this.logInfo){
            return this.logInfo;
        }

        return null;
    }

}
