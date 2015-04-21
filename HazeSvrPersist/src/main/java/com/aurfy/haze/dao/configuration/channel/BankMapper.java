package com.aurfy.haze.dao.configuration.channel;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.configuration.channel.BankEntity;

@Component(AOP_MAPPER.BANK_MAPPER)
@MapperEntity(value = BankEntity.class, CRUDBeanRequired = true)
public interface BankMapper extends CRUDMapper {

	/**
	 * get banks associated with the given bank name.
	 * 
	 * @param bankName
	 * @return
	 */
	List<BankEntity> selectBankByName(@Param("name") String bankName);

}
