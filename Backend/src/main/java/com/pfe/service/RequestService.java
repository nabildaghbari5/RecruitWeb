package com.pfe.service;

import com.pfe.dto.RequestDto;

import java.util.List;



public interface RequestService extends BaseService<RequestDto, Integer> {

	RequestDto creats(RequestDto dto  , Integer announcementId);

	List<RequestDto> findByCandidatId(String candidatId);

	RequestDto updateStatus(Integer demandeId, String status);

	List<RequestDto> findByStatus();




}
