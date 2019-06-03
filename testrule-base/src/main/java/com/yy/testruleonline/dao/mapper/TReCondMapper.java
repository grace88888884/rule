package com.yy.testruleonline.dao.mapper;

import com.yy.testruleonline.dao.entity.TReCond;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yy
 * @since 2019-04-17
 */
public interface TReCondMapper extends BaseMapper<TReCond> {
    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " t_re_cond \n" +
            "where\n" +
            "cond_name in" +
            "<foreach collection='condNames' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<TReCond> selectByConditionNames(@Param("condNames") Set<String> condNames);

}
