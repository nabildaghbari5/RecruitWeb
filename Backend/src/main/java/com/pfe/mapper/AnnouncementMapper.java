



package com.pfe.mapper;


import com.pfe.dto.AnnouncementDto;
import com.pfe.model.Announcement;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper extends BaseMapper<AnnouncementDto, Announcement> {

    @Override
    @Mapping(target = "recruiter", ignore = true)
    Announcement toENTITY(AnnouncementDto dto);

    @Override
    @Mapping(source = "recruiter.id", target = "recruiterId")
    @Mapping(source = "recruiter.email", target = "recruiterEmail")
    @Mapping(target = "recruiterFullName", expression = "java(entity.getRecruiter() != null ? entity.getRecruiter().fullName() : null)")
    AnnouncementDto toDTO(Announcement entity);

    @Override
    @Mapping(target = "recruiter", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Announcement entity, AnnouncementDto dto);
}
