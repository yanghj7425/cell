package com.self.cell.common.pojo;


import cn.hutool.core.map.MapUtil;
import com.self.cell.common.util.HttpServletUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class PageParam {


    private Map<String, Object> params;

    private int pageSize;
    private int pageNum;

    public PageParam(Builder builder) {
        this.pageNum = builder.pageNum;
        this.pageSize = builder.pageSize;
        this.params = builder.target;
    }

    public Map<String, Object> getParams() {
        return params;
    }


    public int getPageSize() {
        return pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageParam{");
        sb.append("params=").append(params);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", pageNum=").append(pageNum);
        sb.append('}');
        return sb.toString();
    }

    public class Builder {


        private int pageNum;
        private int pageSize;
        private Map<String, Object> target;


        public Builder() {
            this.pageNum = 1;
            this.pageSize = 7;
            this.target = MapUtil.newHashMap();
        }

        public Builder(HttpServletRequest request) {
            this();
            this.pageNum = HttpServletUtils.getParameter(request, "pageNum", 1);
            this.pageSize = HttpServletUtils.getParameter(request, "pageSize", 7);
        }

        public Builder addParam(String key, Object value) {
            target.put(key, value);
            return this;
        }

        public PageParam build() {
            return new PageParam(this);
        }
    }


}
