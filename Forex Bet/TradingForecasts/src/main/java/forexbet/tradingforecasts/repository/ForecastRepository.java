package forexbet.tradingforecasts.repository;

import forexbet.tradingforecasts.model.entity.Category;
import forexbet.tradingforecasts.model.entity.Forecast;
import forexbet.tradingforecasts.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    List<Forecast> findAllByBuyer_IdAndPriceNotNullOrderByIdDesc(Long id);

    List<Forecast> findByAdmin_IdAndClosedIsNullOrderByCreatedDesc(long ownerId);

    List<Forecast> findAllByClosedIsNullAndPriceIsNotNullOrderByCreatedDesc();

    List<Forecast> findAllByClosedIsNotNullOrderByClosedDesc();

    List<Forecast> findAllByClosedIsNullAndPriceIsNull();

    List<Forecast> findAllByClosedIsNullAndPriceIsNotNullAndCategoryIsOrderByCreatedDesc(Category category);

    List<Forecast> findAllByClosedIsNotNullAndPriceIsNotNullAndCategoryIsOrderByClosedDesc(Category category);
}
