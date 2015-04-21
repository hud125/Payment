package com.aurfy.haze.dao.payment;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.payment.PaymentSummaryEntity;

@Component(AOP_MAPPER.PAYMENT_SUMMARY_MAPPER)
@MapperEntity(value = PaymentSummaryEntity.class, CRUDBeanRequired = false)
public interface PaymentSummaryMapper extends CRUDMapper {

}
