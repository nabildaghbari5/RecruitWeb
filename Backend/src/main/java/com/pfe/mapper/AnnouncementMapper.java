



package com.pfe.mapper;


import com.pfe.dto.AnnouncementDto;
import com.pfe.model.Announcement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface AnnouncementMapper extends BaseMapper<AnnouncementDto, Announcement>{
	
	
}
