package com.udacity.pricing.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Represents the price of a given vehicle, including currency.
 */
@Entity
public class Price {

    @Id
    private Long id;

    private String currency;
    private BigDecimal price;

    public Price() {
    }

    public Price(Long id, String currency, BigDecimal price) {
        this.currency = currency;
        this.price = price;
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getVehicleId() {
        return id;
    }

    public void setVehicleId(Long id) {
        this.id = id;
    }
}
