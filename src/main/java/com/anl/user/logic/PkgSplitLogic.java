package com.anl.user.logic;

import com.anl.user.persistence.po.FlowPacketDefinition;

public interface PkgSplitLogic {

	void pkgSplit(FlowPacketDefinition flowPacketDefinition, int userId,String source) throws Exception;

}
