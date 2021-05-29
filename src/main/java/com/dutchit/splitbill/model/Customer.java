package com.dutchit.splitbill.model;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@EqualsAndHashCode(callSuper = true)
//@Data
//@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private long id;

    @NotNull
    @NotBlank(message = "please enter your firstName")
    @Column(name = "first_name", columnDefinition = "text", nullable = false)
    private String firstName;


    @Column(name = "second_name", columnDefinition = "text", nullable = false)
//    @NonNull
    @NotBlank(message = "please enter your secondName")
    private String lastName;


    @NotBlank(message = "please enter your emailId")
    @NotNull
    @Column(name = "email", columnDefinition = "text", nullable = false)
    private String email;


    @NotBlank(message = "please enter your mobileNumber")
//    @NonNull
    @Size(min = 9, max = 9)
    @Column(name = "mobile_number", columnDefinition = "text", nullable = false)
    private String mobileNumber;
}
