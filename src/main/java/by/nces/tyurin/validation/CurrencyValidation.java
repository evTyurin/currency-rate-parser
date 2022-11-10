package by.nces.tyurin.validation;

import by.nces.tyurin.model.CurrencyTimeRange;
import org.springframework.stereotype.Component;

@Component
public class CurrencyValidation {
    public void validateDate(CurrencyTimeRange currency) throws Exception {
        if (currency.getStart() == null) {
            throw new Exception("Start date is empty");
        }
        if (currency.getEnd() == null) {
            throw new Exception("End date is empty");
        }
        if (currency.getStart().isAfter(currency.getEnd())) {
            throw new Exception("Start date is after end date");
        }
    }
}
