package com.aurfy.haze.service.impl.perm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aurfy.haze.core.model.perm.PermValueEnum;
import com.aurfy.haze.core.model.perm.ViewScope;
import com.aurfy.haze.dao.perm.PermAssignmentMapper;
import com.aurfy.haze.entity.perm.AssigneeClassifierEnum;
import com.aurfy.haze.entity.perm.PermAssignmentEntity;
import com.aurfy.haze.service.api.perm.PermissionService;
import com.aurfy.haze.service.bean.perm.PermAssignmentBean;
import com.aurfy.haze.service.conf.ServiceImplConstant.AOP_NAME;
import com.aurfy.haze.service.impl.BaseHazeService;
import com.aurfy.haze.service.impl.helper.BeanBuilder;
import com.aurfy.haze.service.impl.perm.provider.InMemoryPermProviderImpl;
import com.aurfy.haze.service.impl.perm.provider.PermValidateKey;

/**
 * 1.登录时获取权限列表，并将结果集存到缓存USER_PERM_PROVIDER中 2.登录后再次请求时，通过userId和entryKey，从缓存USER_PERM_PROVIDER中获取对应的权限（无、Granted、denied）
 * 
 * @author rocket
 *
 */
@Service(AOP_NAME.PERMISSION_SERVICE)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PermissionServiceImpl extends BaseHazeService implements PermissionService {

	@Autowired
	private PermAssignmentMapper assignMapper;

	public PermissionServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * warning:hashmap get the value by the PermValidateKey which the functions of hashcode and equals were override.
	 * so, we only compare their values instead of pointers.
	 */
	@Override
	public PermAssignmentBean getAssignment(String userId, String permEntryKey) {
		return InMemoryPermProviderImpl.USER_PERM_PROVIDER.get(new PermValidateKey(userId, permEntryKey));
	}

	@Override
	public List<PermAssignmentBean> listPermission(String userId) {

		// 每个用户的权限列表都维护到了InMemoryPermProvider.USER_PERM_PROVIDER中，还需考虑考虑user和role的关系
		AssigneeClassifierEnum assigneeClassifier1 = AssigneeClassifierEnum.USER;
		AssigneeClassifierEnum assigneeClassifier2 = AssigneeClassifierEnum.ROLE;
		PermValueEnum permValue1 = PermValueEnum.GRANTED;
		PermValueEnum permValue2 = PermValueEnum.DENIED;
		List<PermAssignmentEntity> grantedPermAssignmentEntitys = assignMapper.selectGrantedAssignmentByUserId(userId,
				assigneeClassifier1, assigneeClassifier2, permValue1, permValue2);

		List<PermAssignmentEntity> deniedPermAssignmentEntitys = assignMapper.selectDeniedAssignmentByUserId(userId,
				assigneeClassifier1, assigneeClassifier2, permValue2);

		grantedPermAssignmentEntitys.addAll(deniedPermAssignmentEntitys);

		List<PermAssignmentBean> permAssignments = BeanBuilder.toPermAssignmentBeans(grantedPermAssignmentEntitys);

		for (PermAssignmentBean ga : permAssignments) {
			PermValidateKey pvk = new PermValidateKey(userId, ga.getPermEntryBean().getEntryKey());
			InMemoryPermProviderImpl.USER_PERM_PROVIDER.put(pvk, ga);
		}
		return permAssignments;
	}

	@Override
	public ViewScope getViewScopeByEntryKey(String entryKey) {
		return InMemoryPermProviderImpl.getViewScopeByEntryKey(entryKey);
	}

}
