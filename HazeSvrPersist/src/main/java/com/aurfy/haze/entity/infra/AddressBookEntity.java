package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.AddressBook;
import com.aurfy.haze.entity.Entity;

@Alias("AddressBookEntity")
public class AddressBookEntity extends AddressBook implements Entity {

}
