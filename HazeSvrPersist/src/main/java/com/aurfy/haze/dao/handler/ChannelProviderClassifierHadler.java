package com.aurfy.haze.dao.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.aurfy.haze.core.model.configuration.channel.ChannelProviderClassifier;

public class ChannelProviderClassifierHadler extends BaseTypeHandler<ChannelProviderClassifier> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, ChannelProviderClassifier parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.getCode());
	}

	@Override
	public ChannelProviderClassifier getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String columnValue = rs.getString(columnName);
		return this.getChannelProviderClassifier(columnValue);
	}

	@Override
	public ChannelProviderClassifier getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String columnValue = rs.getString(columnIndex);
		return this.getChannelProviderClassifier(columnValue);
	}

	@Override
	public ChannelProviderClassifier getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String columnValue = cs.getString(columnIndex);
		return this.getChannelProviderClassifier(columnValue);
	}

	private ChannelProviderClassifier getChannelProviderClassifier(String columnValue) {
		return ChannelProviderClassifier.parseByCode(columnValue);
	}
}
