package com.collidacube.prison.prisoncore.data.economy;

public record EconomySettings(String economyName, String currencySingular, String currencyPlural, int fractionalDigits, String format, double initialAmount) {

    public static final EconomySettings None = new Builder().build();

    @Override
    public String toString() {
        return "EconomySettings{" +
                "economyName='" + economyName + '\'' +
                ", currencySingular='" + currencySingular + '\'' +
                ", currencyPlural='" + currencyPlural + '\'' +
                ", fractionalDigits=" + fractionalDigits +
                ", format='" + format + '\'' +
                ", initialAmount=" + initialAmount +
                '}';
    }

    public static class Builder {

        protected String economyName = null;
        protected String currencySingular = null;
        protected String currencyPlural = null;
        protected int fractionalDigits = 2;
        protected String format = null;
        protected double initialAmount = 0;

        public Builder setEconomyName(String economyName) {
            this.economyName = economyName;
            return this;
        }

        public Builder setCurrencySingular(String currencySingular) {
            this.currencySingular = currencySingular;
            return this;
        }

        public Builder setCurrencyPlural(String currencyPlural) {
            this.currencyPlural = currencyPlural;
            return this;
        }

        public Builder setFractionalDigits(int fractionalDigits) {
            this.fractionalDigits = fractionalDigits;
            return this;
        }

        public Builder setFormat(String format) {
            this.format = format;
            return this;
        }

        public Builder setInitialAmount(double initialAmount) {
            this.initialAmount = initialAmount;
            return this;
        }

        public EconomySettings build() {
            return new EconomySettings(
                    economyName,
                    currencySingular,
                    currencyPlural,
                    fractionalDigits,
                    format == null ? "%." + fractionalDigits + "f" : format,
                    initialAmount
            );
        }

    }

}
