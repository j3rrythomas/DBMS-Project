package EMeter;

public class MeterTable {
    String month;
    int meterNumber,year;
    double usage,dues;

    public MeterTable(String month, int meterNumber, double usage, int year, double dues) {
        this.month = month;
        this.meterNumber = meterNumber;
        this.usage = usage;
        this.year = year;
        this.dues = dues;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(int meterNumber) {
        this.meterNumber = meterNumber;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getDues() {
        return dues;
    }

    public void setDues(double dues) {
        this.dues = dues;
    }
}
