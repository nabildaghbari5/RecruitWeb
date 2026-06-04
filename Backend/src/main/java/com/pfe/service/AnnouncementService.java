package com.pfe.service;


import com.pfe.dto.AnnouncementDto;

public interface AnnouncementService extends BaseService<AnnouncementDto, Integer> {

    AnnouncementDto create(AnnouncementDto dto, Integer recruiterId);

}
