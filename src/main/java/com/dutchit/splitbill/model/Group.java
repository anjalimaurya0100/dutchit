package com.dutchit.splitbill.model;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "groups")
public class Group extends AuditModel {

    @Column(name = "simplify_group_debts")
    boolean simplifyGroupDebts = false;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private long id;


    @Column(name = "group_name", nullable = false, columnDefinition = "text")
    private String groupName;
}
