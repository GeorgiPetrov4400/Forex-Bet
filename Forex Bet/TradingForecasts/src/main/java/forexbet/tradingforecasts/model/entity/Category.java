package forexbet.tradingforecasts.model.entity;

import forexbet.tradingforecasts.model.entity.enums.CategoryNameEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum category;

    public Category() {
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public Category setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(category);
    }
}
