package com.aurfy.haze.dao.txn;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.txn.MerTxnEntity;

@Component(AOP_MAPPER.MER_TXN_MAPPER)
@MapperEntity(value = MerTxnEntity.class, CRUDBeanRequired = true)
public interface MerTxnMapper extends CRUDMapper {

	/**
	 * select merchant transaction by the given parameter.<br/>
	 * note: the <code>date</code> parameter will be compared with day part only, no time part will be used.
	 * 
	 * date 年月日匹配
	 * 
	 * @param merId
	 * @param subMerId
	 * @param merOrderId
	 * @param date
	 * @return
	 */
	public MerTxnEntity retrieveTxnWithinSameDay(@Param("merId") String merId, @Param("subMerId") String subMerId,
			@Param("merOrderId") String merOrderId, @Param("date") Date date);
}
