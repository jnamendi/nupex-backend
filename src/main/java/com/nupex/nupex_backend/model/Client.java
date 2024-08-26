package com.nupex.nupex_backend.model;

import com.nupex.nupex_backend.enums.ClientStatus;
import com.nupex.nupex_backend.enums.ClientType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;


import org.hibernate.type.SqlTypes;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "client_id_seq")
    private Long id;
    @Column(name = "business_unit_id")
    private Long businessUnitId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "phone")
    private Integer phone;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "commercial_activity")
    private String commercialActivity;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "client_type")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private ClientType clientType;

    @Column(name = "client_code")
    private String clientCode;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "client_status")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private ClientStatus status;

    @Column(name = "national_id")
    private String nationalId;
    @Column(name = "relationship_start_date")
    private ZonedDateTime relationshipStartDate;     
}
