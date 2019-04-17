package com.yy.testruleonline.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yy.testruleonline.entity.Param;
import com.yy.testruleonline.entity.ParamClassify;
import com.yy.testruleonline.mapper.ParamMapper;
import com.yy.testruleonline.service.IParamClassifyService;
import com.yy.testruleonline.service.IParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mht
 * @since 2019-04-17
 */
@Service
public class ParamServiceImpl extends ServiceImpl<ParamMapper, Param> implements IParamService {
    @Autowired
    IParamClassifyService paramClassifyService;
    
    @Transactional
    @Override
    public boolean addParamClassify(ParamClassify paramClassify, List<Param> params) {
        paramClassifyService.insert(paramClassify);
        for(Param param :params){
            param.setClassifyId(paramClassify.getId());
        }
        insertBatch(params);
        return false;
    }
}
