package com.wheeler.core.dto.model.partial;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EnumDto {

    private String name;
    private String description;
    private String display;
    private Integer rank;

    public EnumDto(final EnumDtoMappable value) {
        this.name = value.name();
        this.description = value.getDescription();
        this.display = value.getDisplay();
        this.rank = value.getRank();
    }
}
