package com.yy.testruleonline.service;

import com.yy.testruleonline.entity.Tag;
import com.yy.testruleonline.entity.TagRange;
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
public interface ITagRangeService extends IService<TagRange> {
     boolean addTag(Tag tag, List<TagRange> tagRanges) ;


    }
