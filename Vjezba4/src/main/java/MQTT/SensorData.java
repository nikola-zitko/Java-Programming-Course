package MQTT;

import java.util.Random;

class SensorData {
    public String name;
    public int factor;
    public double rangeStart;
    public double rangeEnd;
    public String unit;

    public byte[] createMessage() {
        Random random = new Random();
        double value = random.nextInt((int)(rangeEnd - rangeStart)) + ((int) rangeStart);
        String payload = (value / factor) + " " + unit + "\n";
        return payload.getBytes();
    }

    public String returnName() {
        return name;
    }
}
