package forexbet.tradingforecasts.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import forexbet.tradingforecasts.model.entity.enums.CategoryNameEnum;
import forexbet.tradingforecasts.model.entity.enums.ForecastTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ForecastAddDTO {

    @NotBlank
    @Size(min = 5, max = 100)
    private String description;

    @NotNull
    private MultipartFile pictureUrl;

    @Positive
    private BigDecimal price;

    @NotNull
    private boolean isActive;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum category;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ForecastTypeEnum type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;

    public ForecastAddDTO() {
        this.created = LocalDateTime.of(LocalDate.now(),
                LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond()));
    }

    public String getDescription() {
        return description;
    }

    public ForecastAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getPictureUrl() {
        return pictureUrl;
    }

    public ForecastAddDTO setPictureUrl(MultipartFile pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ForecastAddDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public ForecastAddDTO setActive(boolean active) {
        isActive = active;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public ForecastAddDTO setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public ForecastTypeEnum getType() {
        return type;
    }

    public ForecastAddDTO setType(ForecastTypeEnum type) {
        this.type = type;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public ForecastAddDTO setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }
}
