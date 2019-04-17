package com.yy.testruleonline.service;

import com.yy.testruleonline.entity.Param;
import com.baomidou.mybatisplus.service.IService;
import com.yy.testruleonline.entity.ParamClassify;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mht
 * @since 2019-04-17
 */
public interface IParamService extends IService<Param> {

    boolean addParamClassify(ParamClassify paramClassify, List<Param> params);
}
