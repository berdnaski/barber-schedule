package io.github.berdnaski.barbearia.domain.service;

import io.github.berdnaski.barbearia.domain.entity.Barber;
import io.github.berdnaski.barbearia.domain.entity.ServiceOffered;
import io.github.berdnaski.barbearia.domain.repository.ServiceOfferedRepository;
import io.github.berdnaski.barbearia.ifnra.exception.ServiceOfferedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceOfferedService {
    private final ServiceOfferedRepository serviceOfferedRepository;
    private final BarberService barberService;

    public ServiceOffered createServiceOffered(String price, String description, Long barberId) {
        Barber barber = barberService.findBarberById(barberId);
        ServiceOffered serviceOffered = new ServiceOffered(price, description, barber);
        return serviceOfferedRepository.save(serviceOffered);
    }

    public List<ServiceOffered> listAllServicesOfferedByBarber(Long id) {
        List<ServiceOffered> allServices = serviceOfferedRepository.findAll();
        return allServices.stream()
                .filter(service -> service.getBarber().getId().equals(id))
                .collect(Collectors.toList());
    }

    public ServiceOffered findServiceOfferedById(Long id) {
        return serviceOfferedRepository.findById(id)
                .orElseThrow(() -> new ServiceOfferedException("Service offered not found"));
    }

    public ServiceOffered updateServiceOffered(Long id, String price, String description, Long barberId) {
        Barber barber = barberService.findBarberById(barberId);
        ServiceOffered serviceOffered = findServiceOfferedById(id);
        serviceOffered.setPrice(Double.valueOf(price));
        serviceOffered.setDescription(description);
        serviceOffered.setBarber(barber);
        return serviceOfferedRepository.save(serviceOffered);
    }

    public void deleteServiceOffered(Long id) {
        serviceOfferedRepository.deleteById(id);
    }
}
