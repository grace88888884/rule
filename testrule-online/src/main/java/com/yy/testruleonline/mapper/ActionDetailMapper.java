package com.yy.testruleonline.mapper;

import com.yy.testruleonline.entity.ActionDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yy.testruleonline.entity.ConditionDetail;
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
 * @since 2019-04-17
 */
public interface ActionDetailMapper extends BaseMapper<ActionDetail> {
    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " action_detail \n" +
            "where\n" +
            "name in" +
            "<foreach collection='actionDetailNames' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<ActionDetail> selectByActionDetailNames(@Param("actionDetailNames") Collection<String> actionDetailNames);

}
