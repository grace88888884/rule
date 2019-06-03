package com.yy.testruleonline.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.testruleonline.dao.entity.TReActn;
import com.yy.testruleonline.dao.entity.TReRule;
import com.yy.testruleonline.dao.entity.TReRuleFlow;
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
 * @since 2019-05-29
 */
public interface TReRuleFlowMapper extends BaseMapper<TReRuleFlow> {

    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " t_re_rule_flow \n" +
            "where\n" +
            "rule_name_list in" +
            "<foreach collection='flowNames' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<TReRule> selectByRuleNames(@Param("flowNames") Collection<String> flowNames);

}
