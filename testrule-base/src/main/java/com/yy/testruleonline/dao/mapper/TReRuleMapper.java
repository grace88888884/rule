package com.yy.testruleonline.dao.mapper;

import com.yy.testruleonline.dao.entity.TReRule;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mht
 * @since 2019-04-17
 */
@Mapper
public interface TReRuleMapper extends BaseMapper<TReRule> {
//    @Select("select * from rule where cond_grp_name in")


    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " t_re_rule \n" +
            "where\n" +
            "cond_grp_name in" +
            "<foreach collection='condGrpNames' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<TReRule> getRuleByconditionGroupNames(@Param("condGrpNames") List<String> condGrpNames);
    
}
