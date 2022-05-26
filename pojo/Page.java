package edu.wtbu.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Page {
    private int pageSize;
    private int startPage;
    private int total;

    public Page() {
    }

    public Page(int pageSize, int startPage, int total) {
        this.pageSize = pageSize;
        this.startPage = startPage;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageSize=" + pageSize +
                ", startPage=" + startPage +
                ", total=" + total +
                '}';
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
