package mqtt;

import mqtt.classes.Sensor;
import mqtt.enums.UnitType;
import org.eclipse.paho.client.mqttv3.MqttException;

public class WaterFlowSensor {
    private WaterFlowSensor() {

    }

    public static void main(String[] args) throws MqttException {
        Sensor temperature = new Sensor(
                "temperature",
                10,
                -3266.8,
                3266.8,
                UnitType.Celsius
        );

        Sensor pressure = new Sensor(
                "pressure",
                1000,
                0,
                65.336,
                UnitType.Bar
        );

        Sensor recentConsumption = new Sensor(
                "recentConsumption",
                0,
                0,
                65336,
                UnitType.Liter
        );

        Sensor formerConsumption = new Sensor(
                "formerConsumption",
                10,
                0,
                6533.6,
                UnitType.CubicMetre
        );

        temperature.publish();
        pressure.publish();
        recentConsumption.publish();
        formerConsumption.publish();;
    }
}