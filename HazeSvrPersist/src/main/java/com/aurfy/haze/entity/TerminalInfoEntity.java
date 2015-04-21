package com.aurfy.haze.entity;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.TerminalInfo;

@Alias("TerminalInfoEntity")
public class TerminalInfoEntity extends TerminalInfo implements Entity{
	
}
