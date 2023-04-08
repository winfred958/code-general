package com.winfred.code.general.base;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;


/**
 * @author winfred
 */
@AllArgsConstructor
@Builder
public class PageData<T> {

  /**
   * 当前页 [1, Integer.MAX)
   */
  private Integer page;
  /**
   * 当前页面显示数
   */
  private Integer size;
  /**
   * 总页数
   */
  private Integer totalPage;
  /**
   * 数据总数
   */
  private Long total;
  private List<T> list;

  private Long offset;

  public PageData() {
  }

  public PageData(Integer page, Integer size, Long total, List<T> list) {
    this.page = page;
    this.size = size;
    this.total = total;
    this.list = list;
  }

  public Integer getPage() {
    if (null == this.page) {
      this.page = DefaultValue.DEFAULT_PAGE_NUMBER;
    }
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getSize() {
    if (this.size == null) {
      this.size = DefaultValue.DEFAULT_PAGE_SIZE;
    }
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Integer getTotalPage() {
    if (this.total % this.size == 0) {
      this.totalPage = Math.toIntExact(this.total / this.size);
    } else {
      this.totalPage = Math.toIntExact(this.total / this.size + 1);
    }
    return totalPage;
  }

  public void setTotalPage(Integer totalPage) {
    this.totalPage = totalPage;
  }

  public Long getTotal() {
    if (this.total == null) {
      total = DefaultValue.DEFAULT_TOTAL;
    }
    return total;
  }

  public void setTotal(Long total_size) {
    this.total = total_size;
  }

  public List<T> getList() {
    if (this.list == null) {
      this.list = new ArrayList<>();
    }
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

  public Long getOffset() {
    if (offset == null) {
      offset = (long) (getPage() - 1) * getSize();
    }
    return offset;
  }

  public void setOffset(Long offset) {
    this.offset = offset;
  }
}
