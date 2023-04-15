package com.collidacube.prison.prisoncore.data.stats;

public record StatisticSettings(String statisticName, String valueSingular, String valuePlural, String format, int initialAmount) {

    public static final StatisticSettings None = new Builder().build();

    public static class Builder {

        protected String statisticName = null;
        protected String valueSingular = null;
        protected String valuePlural = null;
        protected String format = null;
        protected int initialAmount = 0;

        public Builder setStatisticName(String statisticName) {
            this.statisticName = statisticName;
            return this;
        }

        public Builder setValueSingular(String valueSingular) {
            this.valueSingular = valueSingular;
            return this;
        }

        public Builder setValuePlural(String valuePlural) {
            this.valuePlural = valuePlural;
            return this;
        }

        public Builder setFormat(String format) {
            this.format = format;
            return this;
        }

        public Builder setInitialAmount(int initialAmount) {
            this.initialAmount = initialAmount;
            return this;
        }

        public StatisticSettings build() {
            return new StatisticSettings(
                    statisticName,
                    valueSingular,
                    valuePlural,
                    format,
                    initialAmount
            );
        }

    }

}
