package com.atguigu.survey.component.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;







import com.atguigu.survey.base.impl.BaseDaoImpl;
import com.atguigu.survey.component.dao.interfaces.LogDao;
import com.atguigu.survey.entities.manager.Log;
import com.atguigu.survey.utils.DataprocessUtils;

/**
 * @author shuai xu 2016年10月31日 上午10:15:48
 */
@SuppressWarnings("unchecked")
@Repository
public class LogDaoImpl extends BaseDaoImpl<Log> implements LogDao {

	public void createTable(String tableName) {
		String sql = "create table if not exists " + tableName
				+ " like survey160629.manager_log";
		updateBySql(sql);
	}

	public void saveLog(Log log) {
		String tableName = DataprocessUtils.generateTableName(0);
		String sql = "INSERT INTO " + tableName + "(" + "`OPERATOR`,"
				+ "`OPERATE_TIME`," + "`METHOD_NAME`," + "`TYPE_NAME`,"
				+ "`PARAM_LIST`," + "`RETURN_VALUE`," + "`EXCEPTION_TYPE`,"
				+ "`EXCEPTION_MESSAGE`) VALUES(?,?,?,?,?,?,?,?)";
		updateBySql(sql, log.getOperator(), log.getOperateTime(),
				log.getMethodName(), log.getTypeName(), log.getParamList(),
				log.getReturnValue(), log.getExceptionType(),
				log.getExceptionMessage());
	}

	@SuppressWarnings("unchecked")
	public List<String> getLogTableNames() {

		String sql = "select table_name from `information_schema`.`TABLES` where table_schema=?";

		return getEntityListBySql(sql, "survey160629_log");
	}

	public int getLogCount() {

		List<String> tableNameList = getLogTableNames();

		String subQuery = DataprocessUtils.generateSubQuery(tableNameList);

		String sql = "SELECT COUNT(union_alias.log_id) FROM (" + subQuery
				+ ") union_alias";

		return getCountBySql(sql);
	}

	public List<Log> getLogLimitedList(int pageNo, int pageSize) {
		
		List<String> tableNameList = getLogTableNames();
		
		String subQuery = DataprocessUtils.generateSubQuery(tableNameList);
		
		String sql = "SELECT union_alias.log_id,"
						+ "union_alias.`OPERATOR` ,"
						+ "union_alias.`OPERATE_TIME` ,"
						+ "union_alias.`METHOD_NAME` ,"
						+ "union_alias.`TYPE_NAME` ,"
						+ "union_alias.`PARAM_LIST` ,"
						+ "union_alias.`RETURN_VALUE` ,"
						+ "union_alias.`EXCEPTION_TYPE` ,"
						+ "union_alias.`EXCEPTION_MESSAGE` "
						+ "FROM ("+subQuery+") union_alias";
		
		@SuppressWarnings("unchecked")
		List<Object[]> list = getLimitedListBySql(pageNo, pageSize, sql);
		List<Log> logList = new ArrayList<Log>();
		
		for (Object[] objects : list) {
			
			Integer logId = (Integer) objects[0];
			String operator = (String) objects[1];
			String operateTime = (String) objects[2];
			String methodName = (String) objects[3];
			String typeName = (String) objects[4];
			String paramList = (String) objects[5];
			String returnValue = (String) objects[6];
			String exceptionType = (String) objects[7];
			String exceptionMessage = (String) objects[8];
			
			Log log = new Log(logId, operator, operateTime, methodName, typeName, paramList, returnValue, exceptionType, exceptionMessage);
			logList.add(log);
		}
		
		return logList;
	}
}
