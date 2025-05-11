package com.salon.controller;

import com.salon.dto.SalonDTO;
import com.salon.domain.Salon;
import com.salon.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salon")
public class SalonController {

    @Autowired
    SalonService salonService ;

    @PostMapping("/create")
    public ResponseEntity<Salon> createSalon(@RequestBody SalonDTO salonDTO){
        Long currentUserId = 3L;
        Salon createdSalon = salonService.createSalon(salonDTO , currentUserId) ;
        return new ResponseEntity<>(createdSalon, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{salonId}")
    public ResponseEntity<Salon> updateSalon(@RequestBody SalonDTO salonDTO , @PathVariable Long salonId){
        Long currentUserId = 3L;
        Salon createdSalon = salonService.updateSalon(salonDTO , currentUserId ,salonId) ;
        return new ResponseEntity<>(createdSalon, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Salon>> getAllSalon() {
        return ResponseEntity.ok(salonService.getAllSalons());
    }

    @GetMapping("/get/{salonId}")
    public ResponseEntity<Salon> getById(@PathVariable Long salonId) {
        return ResponseEntity.ok(salonService.getSalonById(salonId));
    }

    @GetMapping("/get/owner")
    public ResponseEntity<Salon> getByOwnerId() {
        Long currentUserId = 3L ;
        return ResponseEntity.ok(salonService.getSalonByOwnerId(currentUserId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Salon>> getByCity(@RequestParam("city") String city) {
        return ResponseEntity.ok(salonService.getSalonByCity(city));
    }




}
