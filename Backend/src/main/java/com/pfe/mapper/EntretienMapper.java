package com.pfe.mapper;

import com.pfe.dto.EntretienDto;
import com.pfe.model.Entretien;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface EntretienMapper extends BaseMapper<EntretienDto, Entretien> {
}
