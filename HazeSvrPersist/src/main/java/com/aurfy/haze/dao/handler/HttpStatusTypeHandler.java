package com.aurfy.haze.dao.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.aurfy.haze.core.model.http.HttpStatus;

public class HttpStatusTypeHandler extends BaseTypeHandler<HttpStatus> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, HttpStatus parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.getCode());
	}

	@Override
	public HttpStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String columnValue = rs.getString(columnName);
		return this.getHttpStatus(columnValue);
	}

	@Override
	public HttpStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String columnValue = rs.getString(columnIndex);
		return this.getHttpStatus(columnValue);
	}

	@Override
	public HttpStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String columnValue = cs.getString(columnIndex);
		return this.getHttpStatus(columnValue);
	}

	public HttpStatus getHttpStatus(String columnValue) {
		return HttpStatus.parseByCode(columnValue);
	}

}
