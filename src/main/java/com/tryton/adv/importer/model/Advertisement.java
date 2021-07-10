package com.tryton.adv.importer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class Advertisement {
    private String datasource;
    private String campaign;
    private Date daily;
    private int clicks;
    private int impressions;
}
