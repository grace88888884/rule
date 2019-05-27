package com.yy.testruleonline.dao.mapper;

import com.yy.testruleonline.dao.entity.TReCondGrp;
import com.baomidou.mybatisplus.mapper.BaseMapper;
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
public interface TReCondGrpMapper extends BaseMapper<TReCondGrp> {

    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " t_re_cond_grp \n" +
            "where\n" +
            "cond_grp_name in" +
            "<foreach collection='condGrpNames' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<TReCondGrp> selectByConditionGroupNames(@Param("condGrpNames") Collection<String> condGrpNames);

    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " t_re_cond_grp \n" +
            "where\n" +
            "rt_cond_grp_name in" +
            "<foreach collection='rtCondGrpNames' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<TReCondGrp> selectByRootConditionGroupName(@Param("rtCondGrpNames") Collection<String> rtCondGrpNames);

}
