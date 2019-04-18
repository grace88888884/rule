package com.yy.testruleonline.service.impl;

import com.yy.testruleonline.entity.Tag;
import com.yy.testruleonline.entity.TagRange;
import com.yy.testruleonline.mapper.TagRangeMapper;
import com.yy.testruleonline.service.ITagRangeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yy.testruleonline.service.ITagService;
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
public class TagRangeServiceImpl extends ServiceImpl<TagRangeMapper, TagRange> implements ITagRangeService {
    @Autowired
    ITagService tagService;

    @Transactional
    @Override
    public boolean addTag(Tag tag, List<TagRange> tagRanges) {
        tagService.insert(tag);
        for(TagRange tagRange :tagRanges){
            tagRange.setClassifyId(tag.getId());
        }
        insertBatch(tagRanges);
        return false;
    }
}
