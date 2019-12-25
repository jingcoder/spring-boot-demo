package com.example.demo.common.query;

import com.example.demo.common.entity.DataEntity;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/20 8:51
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public class QueryConfig {
    public static List<SqlQueryParams> defaultParams = new LinkedList<>();

    static {
        defaultParams.add(new SqlQueryParams("delFlag",JoinTypeEnum.and,CompareTypeEnum.equal,DataEntity.DEL_FLAG_NORMAL));
    }
}
