package com.aurfy.haze.dao.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.aurfy.haze.core.model.txn.SignMethod;

/**
 * 由于SignMethod存到数据库中必须是枚举，故这里将SignMethod转成枚举
 * 
 * BaseTypeHandler具体作用？？
 * 
 * @author rocket
 *
 */
public class SignMethodTypeHandler extends BaseTypeHandler<SignMethod> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, SignMethod parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.getCipherAlgorithm().name());
	}

	@Override
	public SignMethod getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String columnValue = rs.getString(columnName);
		return this.getSignMethod(columnValue);
	}

	@Override
	public SignMethod getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String columnValue = rs.getString(columnIndex);
		return this.getSignMethod(columnValue);
	}

	@Override
	public SignMethod getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String columnValue = cs.getString(columnIndex);
		return this.getSignMethod(columnValue);
	}
	
	public SignMethod getSignMethod(String columnValue) {
		return SignMethod.parseByName(columnValue);
	}

}
