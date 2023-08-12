package forexbet.tradingforecasts.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    private String publicId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Forecast forecast;

    public Picture() {
    }

    public String getTitle() {
        return title;
    }

    public Picture setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Picture setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public Picture setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public Picture setForecast(Forecast forecast) {
        this.forecast = forecast;
        return this;
    }
}
