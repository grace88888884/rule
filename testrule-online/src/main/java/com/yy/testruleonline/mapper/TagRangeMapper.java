package com.yy.testruleonline.mapper;

import com.yy.testruleonline.entity.ConditionDetail;
import com.yy.testruleonline.entity.TagRange;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yy
 * @since 2019-04-18
 */
public interface TagRangeMapper extends BaseMapper<TagRange> {
    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " tag_range \n" +
            "where\n" +
            "name in" +
            "<foreach collection='tagRanges' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<TagRange> selectByTagRanges(@Param("tagRanges") Collection<String> tagRanges);

}
