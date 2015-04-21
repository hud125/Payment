package com.aurfy.haze.dao.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.aurfy.haze.core.model.payment.ExpiryDate;

/**
 * ExpiryDate, from obj to str
 * 
 * @author rocket
 *
 */
public class ExpiryDateTypeHandler extends BaseTypeHandler<ExpiryDate> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, ExpiryDate parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.toString());
	}
	
	@Override
	public ExpiryDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String columnValue = rs.getString(columnName);
		return this.getExpiryDate(columnValue);
	}

	@Override
	public ExpiryDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String columnValue = rs.getString(columnIndex);
		return this.getExpiryDate(columnValue);
	}

	@Override
	public ExpiryDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String columnValue = cs.getString(columnIndex);
		return this.getExpiryDate(columnValue);
	}
	
	public ExpiryDate getExpiryDate(String dateStr){
		return ExpiryDate.parseByDateStr(dateStr);
	}


}
