package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Tariff {
    private String name;
    private String operator;
    private BigDecimal payroll;
    private BigDecimal smsPrice;
    private CallPrices callPrices = new CallPrices();
    private Parameters parameters = new Parameters();

    public Tariff() {
    }

    public Tariff(String name, String operator, BigDecimal payroll, BigDecimal smsPrice, CallPrices callPrices, Parameters parameters) {
        this.name = name;
        this.operator = operator;
        this.payroll = payroll;
        this.smsPrice = smsPrice;
        this.callPrices = callPrices;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public BigDecimal getPayroll() {
        return payroll;
    }

    public void setPayroll(BigDecimal payroll) {
        this.payroll = payroll;
    }

    public BigDecimal getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(BigDecimal smsPrice) {
        this.smsPrice = smsPrice;
    }

    public CallPrices getCallPrices() {
        return callPrices;
    }

    public void setCallPrices(CallPrices callPrices) {
        this.callPrices = callPrices;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {

        Tariff tariff = new Tariff();
        tariff = (Tariff) o;

        return this.name.equals(tariff.name) && this.operator.equals(tariff.operator) && this.payroll.equals(tariff.payroll);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, operator, payroll, smsPrice, callPrices, parameters);
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "name='" + name + '\'' +
                ", operator='" + operator + '\'' +
                ", payroll=" + payroll +
                ", smsPrice=" + smsPrice +
                ", callPrices=" + callPrices.toString() +
                ", parameters=" + parameters.toString() +
                '}';
    }


    public class Parameters {
        private int favNumbers;
        private Tariffication tariffication;
        private BigDecimal firstFee;

        public Parameters() {
        }

        public Parameters(int favNumbers, Tariffication tariffication, BigDecimal firstFee) {
            this.favNumbers = favNumbers;
            this.tariffication = tariffication;
            this.firstFee = firstFee;
        }

        public int getFavNumbers() {
            return favNumbers;
        }

        public void setFavNumbers(int favNumbers) {
            this.favNumbers = favNumbers;
        }

        public Tariffication getTariffication() {
            return tariffication;
        }

        public void setTariffication(Tariffication tariffication) {
            this.tariffication = tariffication;
        }

        public BigDecimal getFirstFee() {
            return firstFee;
        }

        public void setFirstFee(BigDecimal firstFee) {
            this.firstFee = firstFee;
        }

        @Override
        public String toString() {
            return "Parameters{" +
                    "favNumbers=" + favNumbers +
                    ", tariffication=" + tariffication +
                    ", firstFee=" + firstFee +
                    '}';
        }
    }


    public class CallPrices {
        private BigDecimal callPriceInternal;
        private BigDecimal callPriceExternal;
        private BigDecimal callPriceStationary;

        public CallPrices() {
        }

        public CallPrices(BigDecimal callPriceInternal, BigDecimal callPriceExternal, BigDecimal callPriceStationary) {
            this.callPriceInternal = callPriceInternal;
            this.callPriceExternal = callPriceExternal;
            this.callPriceStationary = callPriceStationary;
        }

        public BigDecimal getCallPriceInternal() {
            return callPriceInternal;
        }

        public void setCallPriceInternal(BigDecimal callPriceInternal) {
            this.callPriceInternal = callPriceInternal;
        }

        public BigDecimal getCallPriceExternal() {
            return callPriceExternal;
        }

        public void setCallPriceExternal(BigDecimal callPriceExternal) {
            this.callPriceExternal = callPriceExternal;
        }

        public BigDecimal getCallPriceStationary() {
            return callPriceStationary;
        }

        public void setCallPriceStationary(BigDecimal callPriceStationary) {
            this.callPriceStationary = callPriceStationary;
        }

        @Override
        public String toString() {
            return "CallPrices{" +
                    "callPriceInternal=" + callPriceInternal +
                    ", callPriceExternal=" + callPriceExternal +
                    ", callPriceStationary=" + callPriceStationary +
                    '}';
        }
    }

}
