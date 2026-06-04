package com.pfe.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pfe.dto.AnnouncementDto;
import com.pfe.exception.NotFoundException;
import com.pfe.mapper.AnnouncementMapper;
import com.pfe.model.Announcement;
import com.pfe.model.Role;
import com.pfe.model.User;
import com.pfe.repository.AnnouncementRepository;
import com.pfe.repository.UserRepository;
import com.pfe.service.AnnouncementService;
import jakarta.persistence.EntityNotFoundException;
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
	private final UserRepository userRepository ;
	private Announcement save(Announcement entity) {
		return announcementRepository.save(entity);
	}

	@Override
	@Transactional
	public AnnouncementDto create(AnnouncementDto dto) {
		throw new IllegalArgumentException("L'identifiant du recruteur est requis (path variable recruiterId)");
	}

	@Override
	@Transactional
	public AnnouncementDto create(AnnouncementDto dto, Integer recruiterId) {
		log.info("Création d'une nouvelle annonce: {} pour le recruteur {}", dto.getTitle(), recruiterId);

		User recruiter = userRepository.findById(recruiterId)
				.orElseThrow(() -> new EntityNotFoundException("Recruteur non trouvé avec l'ID : " + recruiterId));
		if (recruiter.getRole() != Role.RECRUTEUR) {
			throw new IllegalArgumentException("L'utilisateur avec l'ID " + recruiterId + " n'est pas un recruteur");
		}

		Announcement announcement = announcementMapper.toENTITY(dto);
		announcement.setRecruiter(recruiter);

		Announcement savedAnnouncement = save(announcement);
		log.info("Annonce sauvegardée avec l'ID: {}", savedAnnouncement.getId());
		return announcementMapper.toDTO(savedAnnouncement);
	}




	@Override
	@Transactional
	public AnnouncementDto update(Integer id, AnnouncementDto dto) throws NotFoundException {
		Optional<Announcement> optional = announcementRepository.findById(id);
		if(optional.isEmpty())
			throw new NotFoundException("Service not found");
		Announcement existing = optional.get();
		if (dto.getRecruiterId() != null) {
			User recruiter = userRepository.findById(dto.getRecruiterId())
					.orElseThrow(() -> new EntityNotFoundException("Recruteur non trouvé avec l'ID : " + dto.getRecruiterId()));
			if (recruiter.getRole() != Role.RECRUTEUR) {
				throw new IllegalArgumentException("L'utilisateur avec l'ID " + dto.getRecruiterId() + " n'est pas un recruteur");
			}
			existing.setRecruiter(recruiter);
		}
		announcementMapper.partialUpdate(existing, dto);
		return announcementMapper.toDTO(save(existing));
	}
	@Override
	@Transactional
	public AnnouncementDto findById(Integer id) throws NotFoundException {
		Optional<Announcement> optional = announcementRepository.findById(id);
		if(optional.isEmpty())
			throw new NotFoundException("Service not found");
		return announcementMapper.toDTO(optional.get());
	}
	@Override
	@Transactional
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
