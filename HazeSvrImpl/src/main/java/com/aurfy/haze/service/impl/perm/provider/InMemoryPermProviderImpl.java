package com.aurfy.haze.service.impl.perm.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aurfy.haze.core.model.perm.ViewScope;
import com.aurfy.haze.dao.perm.ColumnScopeMapper;
import com.aurfy.haze.dao.perm.RowScopeMapper;
import com.aurfy.haze.entity.perm.ColumnScopeEntity;
import com.aurfy.haze.entity.perm.RowScopeEntity;
import com.aurfy.haze.service.bean.perm.PermAssignmentBean;
import com.aurfy.haze.service.impl.helper.POJOBuilder;

/**
 * A.USER_PERM_PROVIDER,用户权限缓存：每个用户对于每个entry都有且仅有一个权限值，故登录时做缓存，再次请求时直接拉取即可
 * TODO:1.设置过期时间
 * 
 * B.VIEWSCOPE_PERM_PROVIDER,viewScope缓存：由于对于系统而言每一个entrykey对应的viewScope都只有一份，故所有用户均可共享
 * 
 * TODO:1.据说缓存可以做延迟加载，那这个等到后面再看看
 * 		2.设置过期时间
 * 
 * 
 * 
 * 过期时间可以做成定时任务，比如每小时重新生成一份即可
 * 
 * @author rocket
 *
 */
@Component
public class InMemoryPermProviderImpl implements InitializingBean {

	/*
	 * USER_PERM_PROVIDER:store the entry of every user and its permission in the system
	 */
	public static HashMap<PermValidateKey, PermAssignmentBean> USER_PERM_PROVIDER = new HashMap<PermValidateKey, PermAssignmentBean>();

	public static PermAssignmentBean getPermProvider(PermValidateKey pvk) {
		synchronized (USER_PERM_PROVIDER) {
			if (USER_PERM_PROVIDER.containsKey(pvk)) {
				return USER_PERM_PROVIDER.get(pvk);
			}
			return null;
		}
	}

	public static void setPermProvider(PermValidateKey pvk, PermAssignmentBean pvr) {
		synchronized (USER_PERM_PROVIDER) {
			USER_PERM_PROVIDER.put(pvk, pvr);
		}
	}

	/*
	 * VIEWSCOPE_PERM_PROVIDER:store the viewscope of every entry in the system
	 */
	public static HashMap<String, ViewScope> VIEWSCOPE_PERM_PROVIDER = new HashMap<String, ViewScope>();

	@Autowired
	private RowScopeMapper rowMapper;

	@Autowired
	private ColumnScopeMapper columnMapper;

	/**
	 * getViewScope by the given entryKey
	 * 
	 * @param entryKey
	 * @return
	 */
	public static ViewScope getViewScopeByEntryKey(String entryKey){
		return VIEWSCOPE_PERM_PROVIDER.get(entryKey);
	}
	
	/**
	 * initialize the system's viewScope of every entryKey when system starts up
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		initialViewScope();
	}

	private void initialViewScope() {
		// 1.list p_column_scopes and p_row_scopes
		List<RowScopeEntity> rowScopes = rowMapper.selectAll();
		List<ColumnScopeEntity> columnScopes = columnMapper.selectAll();

		List<ColumnScopeEntity> columnScopesTemp = new ArrayList<ColumnScopeEntity>();// p_column_scope

		// 2.deal with rowScopes, regard entryKey as the key of _permRowScopeMap
		String permEntryId = "";
		String entryKey = "";
		ViewScope viewScope = new ViewScope();

		String permEntryIdMark = permEntryId;
		for (RowScopeEntity row : rowScopes) {
			viewScope = new ViewScope();
			viewScope.setRowScope(POJOBuilder.toRowScope(row));
			VIEWSCOPE_PERM_PROVIDER.put(row.getPermEntryKey(), viewScope);
		}

		// 3.deal with columnScopes, regard entryKey as the key of _permColumnScopeMap
		permEntryId = "";
		entryKey = "";
		if (columnScopes.size() > 0) {
			permEntryId = columnScopes.get(0).getPermEntryId();
			entryKey = columnScopes.get(0).getPermEntryKey();
		}
		permEntryIdMark = permEntryId;
		for (ColumnScopeEntity column : columnScopes) {
			permEntryId = column.getPermEntryId();
			if (permEntryIdMark.equals(permEntryId)) {
				columnScopesTemp.add(column);
			} else {
				if (VIEWSCOPE_PERM_PROVIDER.containsKey(entryKey)) {
					viewScope = VIEWSCOPE_PERM_PROVIDER.get(entryKey);
				} else {
					viewScope = new ViewScope();
				}
				viewScope.setColumnScopes(POJOBuilder.toColumnScopeList(columnScopesTemp));
				VIEWSCOPE_PERM_PROVIDER.put(entryKey, viewScope);

				permEntryIdMark = permEntryId;
				entryKey = column.getPermEntryKey();
				columnScopesTemp = new ArrayList<ColumnScopeEntity>();
			}
		}
		if (columnScopesTemp.size() > 0) {
			if (VIEWSCOPE_PERM_PROVIDER.containsKey(entryKey)) {
				viewScope = VIEWSCOPE_PERM_PROVIDER.get(entryKey);
			} else {
				viewScope = new ViewScope();
			}
			viewScope.setColumnScopes(POJOBuilder.toColumnScopeList(columnScopesTemp));
			VIEWSCOPE_PERM_PROVIDER.put(entryKey, viewScope);
		}
	}

}
