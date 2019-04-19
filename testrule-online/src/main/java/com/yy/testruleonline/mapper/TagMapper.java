package com.yy.testruleonline.mapper;

import com.yy.testruleonline.entity.Rule;
import com.yy.testruleonline.entity.Tag;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yy
 * @since 2019-04-18
 */
public interface TagMapper extends BaseMapper<Tag> {

    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " tag \n" +
            "where\n" +
            "name in" +
            "<foreach collection='tagNames' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<Tag> selectByTagNames(@Param("tagNames") List<String> tagNames);

}
