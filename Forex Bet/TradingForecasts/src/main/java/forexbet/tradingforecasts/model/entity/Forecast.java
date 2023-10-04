package forexbet.tradingforecasts.model.entity;

import forexbet.tradingforecasts.model.entity.enums.ForecastTypeEnum;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "forecasts")
public class Forecast extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    private BigDecimal price;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column
    private LocalDateTime closed;

    @Column(name = "forecast_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ForecastTypeEnum forecastType;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "forecast", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Picture> picturesUrl;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User admin;

    @ManyToMany
    private List<User> buyer;

    public Forecast() {
        this.created = LocalDateTime.now();
        this.closed = null;
        this.buyer = new ArrayList<>();
        this.picturesUrl = new HashSet<>();
    }

    public String getDescription() {
        return description;
    }

    public Forecast setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Forecast setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Forecast setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public LocalDateTime getClosed() {
        return closed;
    }

    public Forecast setClosed(LocalDateTime closed) {
        this.closed = closed;
        return this;
    }

    public ForecastTypeEnum getForecastType() {
        return forecastType;
    }

    public Forecast setForecastType(ForecastTypeEnum forecastType) {
        this.forecastType = forecastType;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public Forecast setActive(boolean active) {
        isActive = active;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Forecast setCategory(Category category) {
        this.category = category;
        return this;
    }

    public User getAdmin() {
        return admin;
    }

    public Forecast setAdmin(User admin) {
        this.admin = admin;
        return this;
    }

    public List<User> getBuyer() {
        return buyer;
    }

    public Forecast setBuyer(List<User> buyer) {
        this.buyer = buyer;
        return this;
    }

    public Set<Picture> getPicturesUrl() {
        return picturesUrl;
    }

    public Forecast setPicturesUrl(Set<Picture> picturesUrl) {
        this.picturesUrl = picturesUrl;
        return this;
    }
}
