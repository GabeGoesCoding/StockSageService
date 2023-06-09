package stocksageservice.alphavantageservice;

import java.util.Objects;

public class StockModel {

    private final String date;
    private final String open;
    private final String high;
    private final String low;
    private final String close;
    private final String volume;

    public StockModel(String date, String open, String high, String low, String close, String volume) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public String getDate() {
        return date;
    }

    public String getOpen() {
        return open;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getClose() {
        return close;
    }

    public String getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "StockModel{" +
                "date='" + date + '\'' +
                ", open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", close='" + close + '\'' +
                ", volume='" + volume + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockModel that = (StockModel) o;
        return Objects.equals(date, that.date) && Objects.equals(open, that.open) && Objects.equals(high, that.high) && Objects.equals(low, that.low) && Objects.equals(close, that.close) && Objects.equals(volume, that.volume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, open, high, low, close, volume);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String date;
        private String open;
        private String high;
        private String low;
        private String close;
        private String volume;

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public Builder withOpen(String open) {
            this.open = open;
            return this;
        }

        public Builder withHigh(String high) {
            this.high = high;
            return this;
        }

        public Builder withLow(String low) {
            this.low = low;
            return this;
        }

        public Builder withClose(String close) {
            this.close = close;
            return this;
        }

        public Builder withVolume(String volume) {
            this.volume = volume;
            return this;
        }

        public StockModel build() {
            return new StockModel(date, open, high, low, close, volume);
        }
    }
}
