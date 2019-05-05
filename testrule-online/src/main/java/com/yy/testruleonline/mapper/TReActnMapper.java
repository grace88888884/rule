package com.yy.testruleonline.mapper;

import com.yy.testruleonline.entity.TReActn;
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
 * @since 2019-04-17
 */
public interface TReActnMapper extends BaseMapper<TReActn> {
    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " t_re_actn \n" +
            "where\n" +
            "actn_name in" +
            "<foreach collection='actionNames' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<TReActn> selectByActionDetailNames(@Param("actionNames") Collection<String> actionNames);

}
