package com.winfred.code.general.base;

import lombok.Getter;
import lombok.Setter;


/**
 * 分页模型
 *
 * @author winfred
 */
public class PageModel implements CleanerBean {

    /**
     * 总数
     */
    @Getter
    @Setter
    private transient int total;

    /**
     * 页数, [1, Integer.MAX_VALUE)
     */

    @Setter
    private transient Integer page;

    /**
     * 偏移量
     */
    private transient Integer offset;

    /**
     * 页大小
     */
    @Setter
    private transient Integer size;

    /**
     * 分页标签需要访问的ACTION地址
     */
    @Getter
    @Setter
    protected transient String pagerUrl;

    @Getter
    @Setter
    private transient int recordsTotal;

    @Getter
    @Setter
    private transient int recordsFiltered;

    @Getter
    @Setter
    private transient int draw;

    private transient boolean automaticOffset = true;

    @Override
    public void clean() {
        this.total = 0;
    }

    public Integer getPage() {
        if (null == this.page || this.page.compareTo(0) <= 0) {
            this.page = DefaultValue.DEFAULT_PAGE_NUMBER;
        }
        return page;
    }

    public Integer getSize() {
        if (null == this.size || this.size.compareTo(0) <= 0) {
            this.size = DefaultValue.DEFAULT_PAGE_SIZE;
        }
        return size;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        this.automaticOffset = false;
    }

    public Integer getOffset() {
        if (automaticOffset) {
            // 自动计算
            this.offset = (getPage() - DefaultValue.DEFAULT_PAGE_NUMBER) * getSize();
        }
        return offset;
    }
}
