package io.github.berdnaski.barbearia.dto;

public record BarberCreateDTO
        (Long id, String name, String address, String workSchedule, String phone) {
}
