package com.yy.testruleonline.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yy
 * @since 2019-04-30
 */
@TableName("t_re_fun")
public class TReFun extends Model<TReFun> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @TableField("fun_name")
    private String funName;

    @TableField("input_param_name_list")
    private String inputParamNameList;

    @TableField("output_name")
    private String outputName;

    @TableField("tag_name")
    private String tagName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }
    public String getInputParamNameList() {
        return inputParamNameList;
    }

    public void setInputParamNameList(String inputParamNameList) {
        this.inputParamNameList = inputParamNameList;
    }
    public String getOutputName() {
        return outputName;
    }

    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Fun{" +
        "id=" + id +
        ", funName=" + funName +
        ", inputParamNameList=" + inputParamNameList +
        ", outputName=" + outputName +
        ", tagName=" + tagName +
        "}";
    }
}
