package com.aurfy.haze.service.impl.helper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.core.model.perm.ColumnScope;
import com.aurfy.haze.core.model.perm.RowScope;
import com.aurfy.haze.entity.perm.ColumnScopeEntity;
import com.aurfy.haze.entity.perm.RowScopeEntity;

public class POJOBuilder {

	private static final Logger logger = LoggerFactory.getLogger(POJOBuilder.class);
	
	public static RowScope toRowScope(RowScopeEntity rowScopeEntity) {

		RowScope rowScope = new RowScope();
		try {
			BeanUtils.copyProperties(rowScope, rowScopeEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from RowScopeEntity to RowScope", e);
			throw new RuntimeException(e);
		}

		// copy other properties

		return rowScope;
	}
	
	public static List<ColumnScope> toColumnScopeList(List<ColumnScopeEntity> columnScopeEntitys) {

		List<ColumnScope> columnScopes = new ArrayList<ColumnScope>();
		try {
			ColumnScope columnScope = null;
			for(ColumnScopeEntity columnScopeEntity:columnScopeEntitys){
				columnScope = new ColumnScope();
				BeanUtils.copyProperties(columnScope, columnScopeEntity);
				columnScopes.add(columnScope);
			}
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from List<ColumnScopeEntity> to List<ColumnScope>", e);
			throw new RuntimeException(e);
		}

		// copy other properties

		return columnScopes;
	}
	
}
