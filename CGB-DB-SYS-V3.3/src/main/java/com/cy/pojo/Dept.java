package com.cy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
   private int id;
   private String name;
   private String parentName;
   private int sort;
   private int parentId;
}
