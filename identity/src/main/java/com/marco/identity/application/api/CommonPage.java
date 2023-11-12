package com.marco.identity.application.api;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Paging data encapsulation class
 * @author MarcoDuong
 */
@ToString
@Data
public class CommonPage<T> {

    /**
     * current page number
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;
    /**
     * Quantity per page
     */
    private Integer totalPage;
    /**
     * total number
     */
    private Long total;
    /**
     * paginated data
     */
    private List<T> list;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
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

}
