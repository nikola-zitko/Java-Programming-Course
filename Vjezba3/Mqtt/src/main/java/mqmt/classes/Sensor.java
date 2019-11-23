package mqtt.classes;

import mqtt.Publisher;
import mqtt.enums.UnitType;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Sensor extends Publisher {
    public static final String TOPIC = "sensors";

    private String SensorName = "";
    private int Factor = 0;
    private int RangeMin = 0;
    private int RangeMax = 0;
    private UnitType UnitType;

    public Sensor(String sensorName, int factor, double rangeMin, double rangeMax, UnitType unitType) throws MqttException {
        SensorName = sensorName;
        Factor = factor;
        if (factor != 0) {
            RangeMin = (int)(rangeMin * factor);
            RangeMax = (int)(rangeMax * factor);
        } else {
            RangeMin = (int)rangeMin;
            RangeMax = (int)rangeMax;
        }
        UnitType = unitType;
    }

    public double getMeasure() {
        return Math.round(Math.random() * RangeMax + RangeMin);
    }

    public void publish() {
        String payload = "";

        if (Factor != 0)
        {
            payload = String.format("%s\nMeasurement: %s %s\n", SensorName, getMeasure() / Factor, UnitType.toString());
        }
        else
        {
            payload = String.format("%s\nMeasurement: %s %s\n", SensorName, getMeasure(), UnitType.toString());
        }

        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(payload.getBytes());

        try {
            publisher.connect();
            publisher.publish(TOPIC + "/" + SensorName, mqttMessage);
            publisher.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }

        System.out.println("== END PUBLISHER ==");
    }
}