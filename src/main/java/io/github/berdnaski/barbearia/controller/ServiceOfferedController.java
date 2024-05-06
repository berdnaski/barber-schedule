package io.github.berdnaski.barbearia.controller;

import io.github.berdnaski.barbearia.domain.entity.ServiceOffered;
import io.github.berdnaski.barbearia.domain.service.ServiceOfferedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbers/{id}/services")
public class ServiceOfferedController {
    private final ServiceOfferedService serviceOfferedService;

    public ServiceOfferedController(ServiceOfferedService serviceOfferedService) {
        this.serviceOfferedService = serviceOfferedService;
    }

    @PostMapping
    public ResponseEntity<ServiceOffered> createServiceOffered(@PathVariable Long barberId, @RequestBody ServiceOffered serviceOffered) {
        ServiceOffered createdServiceOffered = serviceOfferedService.createServiceOffered(String.valueOf(serviceOffered.getPrice()), serviceOffered.getDescription(), barberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdServiceOffered);
    }

    @GetMapping
    public ResponseEntity<List<ServiceOffered>> getAllServicesOffered(@PathVariable Long id) {
        List<ServiceOffered> servicesOffered = serviceOfferedService.listAllServicesOfferedByBarber(id);
        return ResponseEntity.ok(servicesOffered);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceOffered> getServiceOfferedById(@PathVariable Long barberId, @PathVariable Long serviceId) {
        ServiceOffered serviceOffered = serviceOfferedService.findServiceOfferedById(serviceId);
        return ResponseEntity.ok(serviceOffered);
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<ServiceOffered> updateServiceOffered(@PathVariable Long barberId, @PathVariable Long serviceId, @RequestBody ServiceOffered serviceOffered) {
        ServiceOffered updatedServiceOffered = serviceOfferedService.updateServiceOffered(serviceId, String.valueOf(serviceOffered.getPrice()), serviceOffered.getDescription(), barberId);
        return ResponseEntity.ok(updatedServiceOffered);
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> deleteServiceOffered(@PathVariable Long barberId, @PathVariable Long serviceId) {
        serviceOfferedService.deleteServiceOffered(serviceId);
        return ResponseEntity.noContent().build();
    }
}
