package by.nces.tyurin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Objects;

public class CurrencyDayRate {
    @JsonProperty("Cur_ID")
    private Integer id;
    @JsonProperty("Date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    @JsonProperty("Cur_OfficialRate")
    private Double rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyDayRate)) return false;
        CurrencyDayRate that = (CurrencyDayRate) o;
        return getId().equals(that.getId()) && getDate().equals(that.getDate()) && getRate().equals(that.getRate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getRate());
    }

    @Override
    public String toString() {
        return new StringBuilder("CurrencyDayRate{id=")
                .append(id)
                .append(", date=")
                .append(date)
                .append(", rate=")
                .append(rate)
                .append('}')
                .toString();
    }
}
