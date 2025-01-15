package com.barber.admin.dto.user;

import com.barber.admin.dto.filter.FilterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilterDTO extends FilterDTO {
    private String email;
    private Boolean active;

    public UserFilterDTO(int page, int pageSize) {
        super(page, pageSize);
    }
}
