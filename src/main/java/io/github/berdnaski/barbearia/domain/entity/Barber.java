package io.github.berdnaski.barbearia.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "barber")
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_barber")
    private Long id;

    @Column(name = "name")

    private String name;

    @Column(name = "address")

    private String address;

    @Column(name = "work_schedule")

    private String workSchedule;

    @Column(name = "phone")

    private String phone;
}
