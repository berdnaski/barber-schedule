package io.github.berdnaski.barbearia.controller;

import io.github.berdnaski.barbearia.domain.entity.Barber;
import io.github.berdnaski.barbearia.domain.service.BarberService;
import io.github.berdnaski.barbearia.dto.BarberCreateDTO;
import io.github.berdnaski.barbearia.dto.BarberDetailDTO;
import io.github.berdnaski.barbearia.dto.BarberUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/barbers")
@RequiredArgsConstructor
public class BarberController {
    private final BarberService barberService;

    @PostMapping
    public ResponseEntity<Barber> createBarbers(@RequestBody BarberCreateDTO barberCreateDTO) {

        Barber barber = barberService.createBarbers(barberCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(barber);
    }

    @GetMapping
    public ResponseEntity<List<BarberDetailDTO>> listAllBarbers() {
        List<BarberDetailDTO> barbers = barberService.listAllBarbers();
        return ResponseEntity.ok(barbers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barber> findBarberById(@PathVariable Long id) {
        Barber barber = barberService.findBarberById(id);
        return ResponseEntity.ok(barber);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barber> updateBarber(@PathVariable Long id, @RequestBody BarberUpdateDTO barberUpdateDTO) {
        barberUpdateDTO = new BarberUpdateDTO(id, barberUpdateDTO.name(), barberUpdateDTO.address(), barberUpdateDTO.workSchedule(), barberUpdateDTO.phone());
        Barber barber = barberService.updateBarber(barberUpdateDTO);
        return ResponseEntity.ok(barber);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarberById(@PathVariable Long id) {
        barberService.deleteBarberById(id);
        return ResponseEntity.noContent().build();
    }
}
