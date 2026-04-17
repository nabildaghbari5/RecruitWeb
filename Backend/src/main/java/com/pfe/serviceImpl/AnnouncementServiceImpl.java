package com.pfe.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pfe.dto.AnnouncementDto;
import com.pfe.exception.NotFoundException;
import com.pfe.mapper.AnnouncementMapper;
import com.pfe.model.Announcement;
import com.pfe.repository.AnnouncementRepository;
import com.pfe.service.AnnouncementService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnnouncementServiceImpl  implements AnnouncementService {

	private final AnnouncementRepository announcementRepository ;
	private final AnnouncementMapper announcementMapper ;
	private Announcement save(Announcement entity) {
		return announcementRepository.save(entity);
	}

	@Override
	@Transactional
	public AnnouncementDto create(AnnouncementDto dto) {
		log.info("Création d'une nouvelle annonce: {}", dto.getTitle());

		// Conversion du DTO en entité
		Announcement announcement = announcementMapper.toENTITY(dto);

		// Sauvegarde initiale de l'annonce
		Announcement savedAnnouncement = save(announcement);
		log.info("Annonce sauvegardée avec l'ID: {}", savedAnnouncement.getId());



		// Conversion et retour du DTO
		return announcementMapper.toDTO(savedAnnouncement);
	}




	@Override
	public AnnouncementDto update(Integer id, AnnouncementDto dto) throws NotFoundException {
		Optional<Announcement> optional = announcementRepository.findById(id);
		if(optional.isEmpty())
			throw new NotFoundException("Service not found");
		Announcement existing = optional.get();
		announcementMapper.partialUpdate(existing, dto);
		return announcementMapper.toDTO(save(existing));
	}
	@Override
	public AnnouncementDto findById(Integer id) throws NotFoundException {
		Optional<Announcement> optional = announcementRepository.findById(id);
		if(optional.isEmpty())
			throw new NotFoundException("Service not found");
		return announcementMapper.toDTO(optional.get());
	}
	@Override
	public List<AnnouncementDto> findAll() {
		return announcementRepository.findAll()
				.stream()
				.map(announcementMapper::toDTO)
				.collect(Collectors.toList());
	}

	public Page<AnnouncementDto> findPage(int pageSize, int pageNumber) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return announcementRepository.findAll(page)
				.map(announcementMapper::toDTO);
	}
	@Override
	public void delete(Integer id) throws NotFoundException {

		if(!announcementRepository.existsById(id)) {
			throw new NotFoundException("Service not found");
		}
		
		announcementRepository.deleteById(id);
	}
	
	
	
	
}
