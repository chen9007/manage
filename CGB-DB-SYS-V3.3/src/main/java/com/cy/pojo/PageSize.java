package com.cy.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;
@Data
@ConfigurationProperties(prefix = "db.page")

public class PageSize {
  private int pageSize=1;
}
