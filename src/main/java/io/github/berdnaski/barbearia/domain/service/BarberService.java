package io.github.berdnaski.barbearia.domain.service;

import io.github.berdnaski.barbearia.domain.entity.Barber;
import io.github.berdnaski.barbearia.domain.repository.BarberRepository;
import io.github.berdnaski.barbearia.dto.BarberCreateDTO;
import io.github.berdnaski.barbearia.dto.BarberDetailDTO;
import io.github.berdnaski.barbearia.dto.BarberUpdateDTO;
import io.github.berdnaski.barbearia.ifnra.exception.BarberException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable_;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class BarberService {
    private final BarberRepository barberRepository;
    public BarberService(BarberRepository barberRepository) {
        this.barberRepository = barberRepository;
    }

    public Barber createBarbers(BarberCreateDTO barberCreateDTO) {
        Barber barber = new Barber();
        barber.setId(barberCreateDTO.id());
        barber.setName(barberCreateDTO.name());
        barber.setAddress(barberCreateDTO.address());
        barber.setWorkSchedule(barberCreateDTO.workSchedule());
        barber.setPhone(barberCreateDTO.phone());

        return barberRepository.save(barber);
    }

    public List<BarberDetailDTO> listAllBarbers() {
        return barberRepository.findAll().stream()
                .map(barbeiro -> new BarberDetailDTO(
                        barbeiro.getName(),
                        barbeiro.getAddress(),
                        barbeiro.getWorkSchedule(),
                        barbeiro.getPhone())
                ).collect(Collectors.toList());
    }

    public Barber findBarberById(Long id) {
        return barberRepository
                .findById(id)
                .orElseThrow(() -> new BarberException("Barber not found"));
    }

    public Barber updateBarber(BarberUpdateDTO barberUpdateDTO) {
        Long id = barberUpdateDTO.id();

        Barber existsBarber = barberRepository.findById(id)
                .orElseThrow(() -> new BarberException("Barber not found"));

        if (barberUpdateDTO.name() != null) {
            existsBarber.setName(barberUpdateDTO.name());
        }
        if (barberUpdateDTO.address()!= null) {
            existsBarber.setAddress(barberUpdateDTO.address());
        }
        if (barberUpdateDTO.workSchedule()!= null) {
            existsBarber.setWorkSchedule(barberUpdateDTO.workSchedule());
        }
        if (barberUpdateDTO.phone()!= null) {
            existsBarber.setPhone(barberUpdateDTO.phone());
        }
        return barberRepository.save(existsBarber);
    }
    public void deleteBarberById(Long id) {
        Barber existsBarber = barberRepository.findById(id)
                .orElseThrow(() -> new BarberException("Barber not found"));
        barberRepository.delete(existsBarber);
    }
}
