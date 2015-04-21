package com.aurfy.haze.service.bean.configuration.channel;

import java.util.List;

import com.aurfy.haze.core.model.configuration.channel.Channel;
import com.aurfy.haze.service.bean.CRUDBean;

public class ChannelBean extends Channel implements CRUDBean {
	private List<ChannelParameterBean> channelParams;
	private boolean supportCardNoTrasmit;
	private boolean support3D;
	private boolean supportDCC;

	public boolean isSupportCardNoTrasmit() {
		return supportCardNoTrasmit;
	}

	public void setSupportCardNoTrasmit(boolean supportCardNoTrasmit) {
		this.supportCardNoTrasmit = supportCardNoTrasmit;
	}

	public boolean isSupport3D() {
		return support3D;
	}

	public void setSupport3D(boolean support3d) {
		support3D = support3d;
	}

	public boolean isSupportDCC() {
		return supportDCC;
	}

	public void setSupportDCC(boolean supportDCC) {
		this.supportDCC = supportDCC;
	}

	public List<ChannelParameterBean> getChannelParams() {
		return channelParams;
	}

	public void setChannelParams(List<ChannelParameterBean> channelParams) {
		this.channelParams = channelParams;
	}

}
