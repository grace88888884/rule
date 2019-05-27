package com.yy.testruleonline.dao.service.impl;

import com.yy.testruleonline.dao.entity.TReTag;
import com.yy.testruleonline.dao.entity.TReTagRng;
import com.yy.testruleonline.dao.mapper.TReTagRngMapper;
import com.yy.testruleonline.dao.service.ITagRangeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yy
 * @since 2019-04-18
 */
@Service
public class TagRangeServiceImpl extends ServiceImpl<TReTagRngMapper, TReTagRng> implements ITagRangeService {
    @Autowired
    TagServiceImpl tagService;

    @Transactional
    @Override
    public boolean addTag(TReTag TReTag, List<TReTagRng> TReTagRngs) {
        tagService.insert(TReTag);
        for(TReTagRng TReTagRng : TReTagRngs){
            TReTagRng.setTagName(TReTag.getTagName());
        }
        insertBatch(TReTagRngs);
        return false;
    }
}
