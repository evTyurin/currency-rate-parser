package by.nces.tyurin.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

public class CurrencyTimeRange {

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyTimeRange)) return false;
        CurrencyTimeRange that = (CurrencyTimeRange) o;
        return getName().equals(that.getName()) && getStart().equals(that.getStart()) && getEnd().equals(that.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStart(), getEnd());
    }

    @Override
    public String toString() {
        return new StringBuilder("CurrencyTimeRange{name='")
                .append(name)
                .append('\'')
                .append(", start=")
                .append(start)
                .append(", end=")
                .append(end)
                .append('}')
                .toString();
    }
}
