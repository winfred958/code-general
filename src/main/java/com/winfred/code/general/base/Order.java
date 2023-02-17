package com.winfred.code.general.base;

import lombok.Getter;
import lombok.Setter;

/**
 * @author kai.hu@shuyun.com
 */
public class Order {

    enum OrderType {
        /**
         * 正序
         */
        ASC("ASC"),
        /**
         * 倒序
         */
        DESC("DESC");

        OrderType(String rule) {
            this.rule = rule;
        }

        private String rule;

        public String getRule() {
            return rule;
        }
    }

    public static final String ASC = "ASC";
    public static final String DESC = "DESC";

    /**
     * 排序列
     */
    @Getter
    @Setter
    private String column;
    /**
     * 排序规则
     */
    @Setter
    private OrderType rule;

    public Order() {
    }

    public Order(String column, OrderType rule) {
        super();
        this.column = column;
        this.rule = rule;
    }

    public String getRule() {
        if (null == this.column) {
            // 共存
            this.rule = null;
            return null;
        }
        if (null == this.rule) {
            this.rule = OrderType.ASC;
        }
        if (this.rule.getRule().equalsIgnoreCase(DESC)) {
            this.rule = OrderType.DESC;
        } else {
            this.rule = OrderType.ASC;
        }
        return rule.getRule();
    }
}
