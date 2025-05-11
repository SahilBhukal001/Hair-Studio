package com.salon.service;

import com.salon.dto.SalonDTO;
import com.salon.domain.Salon;
import com.salon.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SalonService {

    @Autowired
    SalonRepository salonRepository ;

    public Salon createSalon(SalonDTO salonDTO , Long userId){
        Salon salon = new Salon() ;

        salon.setName(salonDTO.getName());
        salon.setImages(salonDTO.getImages());
        salon.setAddress(salonDTO.getAddress());
        salon.setPhoneNumber(salonDTO.getPhoneNumber());
        salon.setCity(salonDTO.getCity());
        salon.setOwnerId(userId);
        salon.setOpenTime(salonDTO.getOpenTime());
        salon.setCloseTime(salonDTO.getCloseTime());

        return salonRepository.save(salon);

    }

    public Salon updateSalon(SalonDTO salonDTO, Long userId, Long salonId) {
        Long currentUserId = 7L;

        if (!Objects.equals(userId, currentUserId)) {
            throw new RuntimeException("Unauthorized: User does not own this salon.");
        }

        Optional<Salon> optionalSalon = salonRepository.findById(salonId);
        if (optionalSalon.isEmpty()) {
            throw new RuntimeException("Salon not found with ID: " + salonId);
        }

        Salon salonToUpdate = optionalSalon.get();

        salonToUpdate.setName(salonDTO.getName());
        salonToUpdate.setImages(salonDTO.getImages());
        salonToUpdate.setAddress(salonDTO.getAddress());
        salonToUpdate.setPhoneNumber(salonDTO.getPhoneNumber());
        salonToUpdate.setCity(salonDTO.getCity());
        salonToUpdate.setOpenTime(salonDTO.getOpenTime());
        salonToUpdate.setCloseTime(salonDTO.getCloseTime());

        return salonRepository.save(salonToUpdate);
    }

    public List<Salon> getAllSalons(){
        return salonRepository.findAll();
    }

    public Salon getSalonById(Long salonId) {
        return salonRepository.findById(salonId)
                .orElseThrow(() -> new RuntimeException("Salon not found with ID: " + salonId));
    }

    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new RuntimeException("No salon found for owner ID: " + ownerId));
    }

    public List<Salon> getSalonByCity(String city) {
        return salonRepository.searchSalons(city) ;
    }

}
