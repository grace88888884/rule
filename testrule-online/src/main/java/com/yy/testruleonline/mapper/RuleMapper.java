package com.yy.testruleonline.mapper;

import com.yy.testruleonline.entity.Rule;
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
public interface RuleMapper extends BaseMapper<Rule> {
//    @Select("select * from rule where condition_group_name in")


    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " rule \n" +
            "where\n" +
            "condition_group_name in" +
            "<foreach collection='conditionGroupNames' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<Rule> getRuleByconditionGroupNames(@Param("conditionGroupNames") List<String> conditionGroupNames);
    
}
