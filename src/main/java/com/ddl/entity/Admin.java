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
@Table(name = "m_admin")
public class Admin extends Date {
    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;
    private String username;
    private String password;
    @Column(name = "admin_name", updatable = true)
    private String name;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
}
