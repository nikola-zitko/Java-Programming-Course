package mqmt;


import mqmt.classes.WaterStatisticsPublisher;
import mqmt.enums.UnitType;

import org.eclipse.paho.client.mqttv3.MqttClient;

class  WaterMeasure{

    void run() throws InterruptedException {

        SensorData temperature = new SensorData(
            -32668,
            32668,
             UnitType.Celsius,
            10
        );
        SensorData pressure = new SensorData(
            0,
            65336,
            UnitType.Bar,
            1000
        );
        SensorData consumption = new SensorData(
            0,
            65336,
            UnitType.Liter,
            1
        );
        SensorData consumptionCubic = new SensorData(
            0,
            65336,
            UnitType.CubicMeter,
            10
        );


        WaterStatisticsPublisher publisher = new WaterStatisticsPublisher();
        MqttClient client = publisher.ClientStart();

        while(true){
            String payload = temperature.Payload() + "\n";
            publisher.Publish(client, payload);
            payload = pressure.Payload() + "\n";
            publisher.Publish(client, payload);
            payload = consumption.Payload() + "\n";
            publisher.Publish(client, payload);
            payload = consumptionCubic.Payload() + "\n";
            publisher.Publish(client, payload);

            Thread.sleep(2000);
        }

    }

}
