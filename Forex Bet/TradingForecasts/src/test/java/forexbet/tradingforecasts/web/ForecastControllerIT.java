package forexbet.tradingforecasts.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class ForecastControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetFreeForecastsUI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/forecasts/ui")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("free-forecasts"));
    }

    @Test
    @WithMockUser
    void testGetEurUsdForecasts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/forecasts/eur-usd-forecast")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("eur-usd-forecast"));
    }

    @Test
    @WithMockUser
    void testGetEurGbpForecasts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/forecasts/eur-gbp-forecast")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("eur-gbp-forecast"));
    }

    @Test
    @WithMockUser
    void testGetGoldForecasts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/forecasts/gold-forecast")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("gold-forecast"));
    }

    @Test
    @WithMockUser
    void testGetDaxForecasts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/forecasts/dax-forecast")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("dax-forecast"));
    }

    @Test
    @WithMockUser
    void testGetDowJonesForecasts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/forecasts/dow-jones-forecast")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("dow-jones-forecast"));
    }

    @Test
    @WithMockUser
    void testGetNasdaqForecasts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/forecasts/nasdaq-forecast")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("nasdaq-forecast"));
    }
}
