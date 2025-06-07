package com.service.controller;

import com.service.domain.ServiceOffering;
import com.service.dto.CategoryDTO;
import com.service.dto.SalonDTO;
import com.service.dto.ServiceDTO;
import com.service.service.ServiceOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/service-offering")
public class ServiceOfferingController {

    @Autowired
    ServiceOfferingService offeringService;

    @PostMapping
    public ResponseEntity<ServiceOffering> createService(
            @RequestHeader("Authorization") String jwt,
            @RequestBody ServiceDTO service) throws Exception {

        /*SalonDTO salon=salonService.getSalonByOwner(jwt).getBody();

        CategoryDTO category=categoryService
                .getCategoryById(service.getCategory()).getBody();*/

        SalonDTO salon = new SalonDTO();
        CategoryDTO category = new CategoryDTO();

        ServiceOffering createdService = offeringService
                .createService(service,salon,category);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }

    @PatchMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> updateService(
            @PathVariable Long serviceId,
            @RequestBody ServiceOffering service) throws Exception {
        // handle a check , so that only authorize user can use this endpoint 

        ServiceOffering updatedService = offeringService
                .updateService(serviceId, service);
        if (updatedService != null) {
            return new ResponseEntity<>(updatedService, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServicesBySalonId(
            @PathVariable Long salonId,
            @RequestParam(required = false) Long categoryId) {
        Set<ServiceOffering> services =  offeringService
                .getAllServicesBySalonId(salonId,categoryId);

        return ResponseEntity.ok(services);

    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> getServiceById(@PathVariable Long serviceId) throws Exception {
        ServiceOffering service = offeringService
                .getServiceById(serviceId);
        if (service == null) {
            throw new Exception("service not found with id "+serviceId);
        }
        return ResponseEntity.ok(service);

    }

    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds(
            @PathVariable Set<Long> ids) {
        Set<ServiceOffering> services =  offeringService
                .getServicesByIds(ids);
        return ResponseEntity.ok(services);
    }
}
