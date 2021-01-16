package ru.alexeysekatskiy.currencyconverter;

public class CurrencyBucket {
    private String charCode;
    private double value;
    private String name;

    @Override
    public String toString() {
        return "CurrencyBucket{" +
                "charCode='" + charCode + '\'' +
                ", value=" + value +
                ", name='" + name + '\'' +
                '}';
    }

    public CurrencyBucket(String charCode, double value, String name) {
        this.charCode = charCode;
        this.value = value;
        this.name = name;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
