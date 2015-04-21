package com.aurfy.haze.dao.perm;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.aurfy.haze.core.model.perm.PermValueEnum;
import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.perm.AssigneeClassifierEnum;
import com.aurfy.haze.entity.perm.PermAssignmentEntity;

@Component(AOP_MAPPER.PERM_ASSIGNMENT_MAPPER)
@MapperEntity(value = PermAssignmentEntity.class, CRUDBeanRequired = false)
public interface PermAssignmentMapper extends CRUDMapper {

	/**
	 * 
	 * @param userId
	 * @param assigneeClassifier1
	 * @param assigneeClassifier2
	 * @param permValue1
	 * @param permValue2
	 * @return
	 */
	List<PermAssignmentEntity> selectGrantedAssignmentByUserId(@Param("userId") String userId,
			@Param("assigneeClassifier1") AssigneeClassifierEnum assigneeClassifier1,
			@Param("assigneeClassifier2") AssigneeClassifierEnum assigneeClassifier2,
			@Param("permValue1") PermValueEnum permValue1, @Param("permValue2") PermValueEnum permValue2);

	/**
	 * 
	 * @param userId
	 * @param assigneeClassifier1
	 * @param assigneeClassifier2
	 * @param permValue2
	 * @return
	 */
	List<PermAssignmentEntity> selectDeniedAssignmentByUserId(@Param("userId") String userId,
			@Param("assigneeClassifier1") AssigneeClassifierEnum assigneeClassifier1,
			@Param("assigneeClassifier2") AssigneeClassifierEnum assigneeClassifier2,
			@Param("permValue2") PermValueEnum permValue2);

}
