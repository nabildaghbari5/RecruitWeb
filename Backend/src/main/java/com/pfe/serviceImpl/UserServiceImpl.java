package com.pfe.serviceImpl;

import com.pfe.dto.UserUpdateRequest;
import com.pfe.model.Role;
import com.pfe.model.User;
import com.pfe.repository.AnnouncementRepository;
import com.pfe.repository.SecteurRepository;
import com.pfe.repository.TokenRepository;
import com.pfe.repository.UserRepository;
import com.pfe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AnnouncementRepository announcementRepository;
    private final SecteurRepository secteurRepository;
    private final TokenRepository tokenRepository;

    @Override
    public User create(User user) {

        return userRepository.save(user);
    }

    @Override
    public User update(Integer id, UserUpdateRequest request) {

        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (StringUtils.hasText(request.getEmail())
                && userRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new IllegalArgumentException("Cet email est déjà utilisé");
        }

        if (StringUtils.hasText(request.getFirstname())) {
            existing.setFirstname(request.getFirstname());
        }
        if (StringUtils.hasText(request.getLastname())) {
            existing.setLastname(request.getLastname());
        }
        if (StringUtils.hasText(request.getEmail())) {
            existing.setEmail(request.getEmail());
        }
        if (request.getTelephone() != null) {
            existing.setTelephone(request.getTelephone());
        }
        if (request.getStatus() != null) {
            existing.setStatus(request.getStatus());
        }
        if (request.getEnabled() != null) {
            existing.setEnabled(request.getEnabled());
        }
        if (request.getAccountLocked() != null) {
            existing.setAccountLocked(request.getAccountLocked());
        }
        if (StringUtils.hasText(request.getPassword())) {
            existing.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return userRepository.save(existing);
    }

    @Override
    public User getById(Integer id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    @Override
    public List<User> getAll() {

        return userRepository.findAll();
    }

    @Override
    public void delete(Integer id) {

        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur introuvable");
        }

        if (!announcementRepository.findByRecruiterId(id).isEmpty()) {
            throw new IllegalArgumentException(
                    "Impossible de supprimer ce recruteur : des offres lui sont associées");
        }

        if (secteurRepository.existsByRecruteur_Id(id)) {
            throw new IllegalArgumentException(
                    "Impossible de supprimer ce recruteur : un secteur lui est associé");
        }

        tokenRepository.deleteByUser_Id(id);
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findByRole(Role role) {
        return userRepository.findByRole(role);
    }

}
