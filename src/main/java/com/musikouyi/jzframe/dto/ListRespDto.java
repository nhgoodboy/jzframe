package com.musikouyi.jzframe.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListRespDto<T> {

    private List<T> items;
    private long total;

}
