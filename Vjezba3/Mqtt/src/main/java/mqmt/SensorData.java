package mqmt;


import mqmt.enums.UnitType;
import java.util.Random;
import mqmt.helpers.RandNumber;

import static mqmt.helpers.RandNumber.GenerateNumberInRange;

public class SensorData {
    int Factor;
    double RangeStart;
    double RangeEnd;
    UnitType TypeOfUnit;
    int value;

    public SensorData(double rangeStart, double rangeEnd, UnitType typeOfUnit, int factor) {
        this.RangeStart = rangeStart;
        this.RangeEnd = rangeEnd;
        this.TypeOfUnit = typeOfUnit;
        this.Factor = factor;
    }

    public String Payload() {
        Random random = new Random();
        double value = random.nextInt((int)(RangeEnd - RangeStart))+(int)RangeStart;
        value = value/Factor;

        String payload = value + " " + TypeOfUnit + "\n";
        return payload;
    }
}
