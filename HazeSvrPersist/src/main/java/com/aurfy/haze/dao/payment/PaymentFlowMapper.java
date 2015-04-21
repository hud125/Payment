package com.aurfy.haze.dao.payment;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.payment.PaymentFlowEntity;

@Component(AOP_MAPPER.PAYMENT_FLOW_MAPPER)
@MapperEntity(value = PaymentFlowEntity.class, CRUDBeanRequired = false)
public interface PaymentFlowMapper extends CRUDMapper {

	PaymentFlowEntity selectByBankOrderId(@Param("bankOrderId") String bankOrderId);
}
