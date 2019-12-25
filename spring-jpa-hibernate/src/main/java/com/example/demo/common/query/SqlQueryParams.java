package com.example.demo.common.query;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/20 8:47
 * @Email xu.xiaojing@frontsurf.com
 * @Description  动态SQL的查询参数
 */

public class SqlQueryParams {

    private String paramName;
    private JoinTypeEnum joinType;
    private CompareTypeEnum compareType;
    private Object value;

    public SqlQueryParams(String paramName, JoinTypeEnum joinType, CompareTypeEnum compareType, Object value) {
        this.paramName = paramName;
        this.joinType = joinType;
        this.compareType = compareType;
        this.value = value;
    }

    public SqlQueryParams(String paramName, CompareTypeEnum compareType, Object value) {
        this.paramName = paramName;
        this.joinType = JoinTypeEnum.and;
        this.compareType = compareType;
        this.value = value;
    }

    public SqlQueryParams(String paramName, Object value) {
        this.paramName = paramName;
        this.joinType = JoinTypeEnum.and;
        this.compareType = CompareTypeEnum.equal;
        this.value = value;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public JoinTypeEnum getJoinType() {
        return joinType;
    }

    public void setJoinType(JoinTypeEnum joinType) {
        this.joinType = joinType;
    }

    public CompareTypeEnum getCompareType() {
        return compareType;
    }

    public void setCompareType(CompareTypeEnum compareType) {
        this.compareType = compareType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
