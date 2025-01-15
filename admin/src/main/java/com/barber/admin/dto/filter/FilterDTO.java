package com.barber.admin.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FilterDTO {
    private int page;
    private int pageSize;

}
