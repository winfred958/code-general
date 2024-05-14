package com.winfred.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author winfred
 */
@Getter
public class BasePageRequest extends PageModel implements Serializable {

  private static final long serialVersionUID = -5700842088869071012L;

  @Setter
  @TableField(exist = false)
  private List<Order> orders;

  @Setter
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(exist = false)
  private String startDateStr;

  @Setter
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(exist = false)
  private String endDateStr;
}
