package com.yy.testruleonline.service.impl;

import com.yy.testruleonline.entity.Tag;
import com.yy.testruleonline.mapper.TagMapper;
import com.yy.testruleonline.service.ITagService;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {
  
}
