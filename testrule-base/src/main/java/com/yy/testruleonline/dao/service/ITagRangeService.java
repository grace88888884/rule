package com.yy.testruleonline.dao.service;

import com.yy.testruleonline.dao.entity.TReTag;
import com.yy.testruleonline.dao.entity.TReTagRng;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yy
 * @since 2019-04-18
 */
public interface ITagRangeService extends IService<TReTagRng> {
     boolean addTag(TReTag TReTag, List<TReTagRng> TReTagRngs) ;


    }
