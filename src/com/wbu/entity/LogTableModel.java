package com.wbu.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

public class LogTableModel extends AbstractTableModel {

    private List<LogInfo> logInfos;
    private LogInfoVO logInfoVO;
    private Set<String> titleSet=new HashSet();
    

    public LogTableModel(LogInfoVO logInfoVO) {	
        this.logInfos = logInfoVO.getLogInfoList();
        this.logInfoVO = logInfoVO;
        this.titleSet = titleSet;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        if(null !=this.logInfoVO){
            return this.logInfoVO.getLogInfoList().size();
        }
        return -1;
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        if(null !=this.logInfoVO){
            return this.logInfoVO.getTitileSet().size();
        }
        return -1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub

        if(rowIndex<this.logInfoVO.getLogInfoList().size()&&columnIndex<this.logInfoVO.getTitileSet().size()){
            return this.logInfos.get(rowIndex).toString().split(",")[columnIndex];
        }
        return null;        
    }

    @Override
    public String getColumnName(int column) {
        String titleArray[]={"日志编号","信息类型","记录时间","登录ip","异常信息"};        
        return titleArray[column];
    }


}
