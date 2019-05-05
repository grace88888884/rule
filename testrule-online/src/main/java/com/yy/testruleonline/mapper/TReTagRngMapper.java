package com.yy.testruleonline.mapper;

import com.yy.testruleonline.entity.TReTagRng;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yy
 * @since 2019-04-18
 */
public interface TReTagRngMapper extends BaseMapper<TReTagRng> {
    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " t_re_tag_rng \n" +
            "where\n" +
            "tag_rng_name in" +
            "<foreach collection='tagRanges' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<TReTagRng> selectByTagRanges(@Param("tagRanges") Collection<String> tagRanges);

}
