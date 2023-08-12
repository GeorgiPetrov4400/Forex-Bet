package forexbet.tradingforecasts.model.view;

import forexbet.tradingforecasts.model.entity.Category;
import forexbet.tradingforecasts.model.entity.enums.ForecastTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ForecastViewModel {

    private Long id;

    private Category category;

    private ForecastTypeEnum forecastType;

    private String description;

    private String pictureUrl;

    private BigDecimal price;

    private LocalDateTime created;

    private LocalDateTime closed;

    private boolean isActive;

    public ForecastViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ForecastViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public ForecastViewModel setCategory(Category category) {
        this.category = category;
        return this;
    }

    public ForecastTypeEnum getForecastType() {
        return forecastType;
    }

    public ForecastViewModel setForecastType(ForecastTypeEnum forecastType) {
        this.forecastType = forecastType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ForecastViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public ForecastViewModel setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ForecastViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public ForecastViewModel setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public LocalDateTime getClosed() {
        return closed;
    }

    public ForecastViewModel setClosed(LocalDateTime closed) {
        this.closed = closed;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public ForecastViewModel setActive(boolean active) {
        isActive = active;
        return this;
    }
}
