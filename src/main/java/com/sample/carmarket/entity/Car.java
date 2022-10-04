package com.sample.carmarket.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@JmixEntity
@Table(name = "CAR", indexes = {
        @Index(name = "IDX_CAR_MODEL_ID", columnList = "MODEL_ID"),
        @Index(name = "IDX_CAR_UNQ", columnList = "REGISTRATION_NUMBER", unique = true)
})
@Entity
public class Car {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Size(min = 6, max = 6)
    @Column(name = "REGISTRATION_NUMBER", nullable = false, length = 6)
    @NotNull
    private String registrationNumber;

    @JoinColumn(name = "MODEL_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Model modelId;

    @Max(message = "max value 2030", value = 2030)
    @Min(message = "min value 1990", value = 1990)
    @Column(name = "PRODUCTION_YEAR")
    private Integer productionYear;

    @Column(name = "STATUS", nullable = false)
    @NotNull
    private Integer status;

    @Column(name = "DATE_OF_SALE")
    private LocalDate dateOfSale;

    public LocalDate getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(LocalDate dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public CarStatus getStatus() {
        return status == null ? null : CarStatus.fromId(status);
    }

    public void setStatus(CarStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Model getModelId() {
        return modelId;
    }

    public void setModelId(Model modelId) {
        this.modelId = modelId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"registrationNumber"})
    public String getInstanceName() {
        return String.format("%s", registrationNumber);
    }
}