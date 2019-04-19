package com.yy.testruleonline.mapper;

import com.yy.testruleonline.entity.ConditionDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yy.testruleonline.entity.ConditionGroup;
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
public interface ConditionDetailMapper extends BaseMapper<ConditionDetail> {
    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " condition_detail \n" +
            "where\n" +
            "name in" +
            "<foreach collection='conditionDetailNames' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<ConditionDetail> selectByConditionGroupNames(@Param("conditionDetailNames") Set<String> conditionDetailNames);

}
