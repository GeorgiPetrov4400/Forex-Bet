package forexbet.tradingforecasts.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRole> roles;

    @ManyToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    private List<Forecast> forecasts;

    public User() {
        this.forecasts = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public User setRoles(List<UserRole> roles) {
        this.roles = roles;
        return this;
    }

    public List<Forecast> getForecasts() {
        return Collections.unmodifiableList(forecasts);
    }

    public User setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
        return this;
    }

    public void addForecast(User buyer, Forecast forecast) {
        if (buyer != null && buyer.getRoles().size() == 1 && forecast != null && forecast.getPrice() != null) {
            if (buyer.getForecasts().contains(forecast)) {
                return;
            }
            forecasts.add(forecast);
            forecast.getBuyer().add(buyer);
        }
    }
}
