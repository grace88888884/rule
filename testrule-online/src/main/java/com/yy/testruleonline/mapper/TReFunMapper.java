package com.yy.testruleonline.mapper;

import com.yy.testruleonline.entity.TReFun;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yy.testruleonline.entity.TReTag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yy
 * @since 2019-04-30
 */
public interface TReFunMapper extends BaseMapper<TReFun> {
    @Select("<script>" +
            "select\n" +
            " *\n" +
            "from\n" +
            " t_re_fun \n" +
            "where\n" +
            "fun_name in" +
            "<foreach collection='funNames' item='item' open='(' separator=',' close=')'>" +
            "#{item} "+
            "</foreach>" +
            "</script>")
    List<TReFun> selectByFunNames(@Param("funNames") List<String> funNames);

}
