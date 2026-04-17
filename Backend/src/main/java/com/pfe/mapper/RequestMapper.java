package com.pfe.mapper;

import com.pfe.dto.RequestDto;
import com.pfe.model.Request;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface RequestMapper  extends BaseMapper<RequestDto, Request>{

}
