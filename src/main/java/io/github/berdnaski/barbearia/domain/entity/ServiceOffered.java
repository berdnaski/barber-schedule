package io.github.berdnaski.barbearia.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service_offered")
public class ServiceOffered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service_offered")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "barber_id", referencedColumnName = "id_barber")
    private Barber barber;

    public ServiceOffered(String price, String description, Barber barber) {
        this.price = Double.valueOf(price);
        this.description = description;
        this.barber = barber;
    }
}
