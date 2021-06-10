package com.dutchit.splitbill.model;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private long id;

    @NotNull
    @NotBlank(message = "must not be null")
    @Column(name = "first_name", columnDefinition = "text", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "second_name", columnDefinition = "text", nullable = false)
    @NotBlank(message = "must not be null")
    private String lastName;

    @NotNull
    @NotBlank(message = "must not be null")
    @Column(name = "email", columnDefinition = "text", nullable = false)
    private String email;

    @NotNull
    @NotBlank(message = "must not be null")
    @Size(min = 10, max = 10)
    @Column(name = "mobile_number", columnDefinition = "text", nullable = false)
    private String mobileNumber;
}
