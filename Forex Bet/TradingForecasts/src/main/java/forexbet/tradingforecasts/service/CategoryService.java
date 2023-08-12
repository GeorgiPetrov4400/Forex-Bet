package forexbet.tradingforecasts.service;

import forexbet.tradingforecasts.model.entity.Category;
import forexbet.tradingforecasts.model.entity.enums.CategoryNameEnum;

public interface CategoryService {

    void seedCategories();

    Category findByCategoryNameEnum(CategoryNameEnum categoryNameEnum);
}
