package com.ecochain.ledger.service.impl;


import com.ecochain.ledger.dao.DaoSupport;
import com.ecochain.ledger.model.PageData;
import com.ecochain.ledger.service.SysGenCodeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("sysGenCodeService")
public class SysGencodeServiceImpl implements SysGenCodeService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;
    @Override
    public List<PageData> findByGroupCode(String groupCode, String versionNo) throws Exception{
        return (List<PageData>)dao.findForList("SysGencodeMapper.findCode", groupCode);
    }
	
		
	
}
