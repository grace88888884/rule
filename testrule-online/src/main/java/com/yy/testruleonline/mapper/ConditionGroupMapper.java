package com.yy.testruleonline.mapper;

import com.yy.testruleonline.entity.ConditionGroup;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yy.testruleonline.entity.Rule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
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
public interface ConditionGroupMapper extends BaseMapper<ConditionGroup> {

    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " condition_group \n" +
            "where\n" +
            "name in" +
            "<foreach collection='conditionGroupNames' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<ConditionGroup> selectByConditionGroupNames(@Param("conditionGroupNames") Collection<String> conditionGroupNames);

    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " condition_group \n" +
            "where\n" +
            "root_condition_group_name in" +
            "<foreach collection='rootConditionGroupNames' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<ConditionGroup> selectByRootConditionGroupName(@Param("rootConditionGroupNames") Collection<String> rootConditionGroupNames);

}
