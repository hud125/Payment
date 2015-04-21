package com.aurfy.haze.entity.infra.mer;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.mer.Merchant;
import com.aurfy.haze.entity.Entity;

@Alias("MerchantEntity")
public class MerchantEntity extends Merchant implements Entity {

}
