package com.ddl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "m_vehicle")
public class Vehicle extends Date {
    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;
    @Column(name = "NoPol", unique = true)
    private String noPol;
    @OneToOne
    @JoinColumn(name = "categori_id")
    private Category category;
    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private Parking parking;
}
