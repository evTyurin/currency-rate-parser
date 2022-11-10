package by.nces.tyurin.controller;

import by.nces.tyurin.model.CurrencyDayRate;
import by.nces.tyurin.model.CurrencyTimeRange;
import by.nces.tyurin.validation.CurrencyValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CurrencyRateController {

    @Autowired
    CurrencyValidation currencyValidation;

    @GetMapping("/index")
    public String showStartPage(Model model) {
        model.addAttribute("currency", new CurrencyTimeRange());
        return "index";
    }

    @PostMapping("/index")
    public String showGraphPage(@ModelAttribute CurrencyTimeRange currency, Model model) throws Exception {
        currencyValidation.validateDate(currency);
        addModelAttributes(parse(currency), model);
        return "chart";
    }

    private List<CurrencyDayRate> parse(CurrencyTimeRange currency) throws IOException {
        StringBuilder url = new StringBuilder();
        url
                .append("https://www.nbrb.by/API/ExRates/Rates/Dynamics/")
                .append(currency.getName())
                .append("?startDate=")
                .append(currency.getStart())
                .append("&endDate=")
                .append(currency.getEnd());

        System.out.println(url.toString());
        URL obj = new URL(url.toString());
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(bufferedReader.readLine(), CurrencyDayRate[].class));
    }

    private void addModelAttributes(List<CurrencyDayRate> currencyDayRates, Model model) {
        double maxPrice = currencyDayRates.get(0).getRate();
        double minPrice = maxPrice;

        Map<LocalDate, Double> data = new LinkedHashMap<>();

        for (CurrencyDayRate currencyDayRate : currencyDayRates) {
            data.put(currencyDayRate.getDate(), currencyDayRate.getRate());
            if(maxPrice < currencyDayRate.getRate()) {
                maxPrice = currencyDayRate.getRate();
            } else if(minPrice > currencyDayRate.getRate()) {
                minPrice = currencyDayRate.getRate();
            }
        }

        model.addAttribute("keySet", data.keySet());
        model.addAttribute("values", data.values());
        model.addAttribute("maxRate", maxPrice);
        model.addAttribute("minRate", minPrice);
    }
}