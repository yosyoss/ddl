package com.ddl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "m_parking")
public class Parking extends Date {
    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Column(name = "entry_time")
    private LocalDateTime entryTime;

    @Column(name = "exit_time")
    private LocalDateTime exitTime;

    @Column(name = "parking_duration")
    private Duration parkingDuration;

    public Duration getParkingDuration() {
        return parkingDuration;
    }
    public void setParkingDuration(Duration parkingDuration) {
        this.parkingDuration = parkingDuration;
    }
}

