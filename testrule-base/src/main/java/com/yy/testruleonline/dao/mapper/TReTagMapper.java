package com.yy.testruleonline.dao.mapper;

import com.yy.testruleonline.dao.entity.TReTag;
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
public interface TReTagMapper extends BaseMapper<TReTag> {

    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " t_re_tag \n" +
            "where\n" +
            "tag_name in" +
            "<foreach collection='tagNames' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<TReTag> selectByTagNames(@Param("tagNames") List<String> tagNames);

}
