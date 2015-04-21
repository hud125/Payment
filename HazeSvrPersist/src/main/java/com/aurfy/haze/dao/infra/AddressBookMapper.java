package com.aurfy.haze.dao.infra;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.AddressBookEntity;

@Component(AOP_MAPPER.ADDRESS_BOOK_MAPPER)
@MapperEntity(value = AddressBookEntity.class, CRUDBeanRequired = true)
public interface AddressBookMapper extends CRUDMapper {

}
