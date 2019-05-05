package com.yy.testruleonline.service;

import com.yy.testruleonline.entity.TReTag;
import com.yy.testruleonline.entity.TReTagRng;
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
