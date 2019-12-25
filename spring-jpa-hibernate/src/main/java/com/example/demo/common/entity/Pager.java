package com.example.demo.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/20 8:27
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pager<T> {
    private List<T> list;
    private Long total;
    private Integer totalPages;
    private Integer pageSize;
    private Boolean first;
    private Boolean last;
    private Boolean previous;
    private Boolean next;

    public Pager(List<T> list, Long total, Integer totalPages) {
        this.list = list;
        this.total = total;
        this.totalPages = totalPages;
    }

    public Pager(List<T> list, Long total, Integer totalPages, Integer pageSize, Boolean first, Boolean last, Boolean previous, Boolean next) {
        this.list = list;
        this.total = total;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
        this.first = first;
        this.last = last;
        this.previous = previous;
        this.next = next;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Boolean getPrevious() {
        return previous;
    }

    public void setPrevious(Boolean previous) {
        this.previous = previous;
    }

    public Boolean getNext() {
        return next;
    }

    public void setNext(Boolean next) {
        this.next = next;
    }
}
