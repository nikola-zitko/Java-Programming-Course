package MQTT;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

class WaterMeasurer {
    private String brookerURL;
    private SensorData[] sensors;
    private MqttClient client;

    private MqttClient ClientStart(){
        MqttClient client = null;
        String clientId = "Client";
        try
        {
            client = new MqttClient(brookerURL, clientId);
            client.connect();
        }
        catch (MqttException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        return client;
    }

    void run() throws InterruptedException {
        client = ClientStart();
        while(true){
            for (int i = 0; i < sensors.length; i++) {
                MqttMessage message = new MqttMessage(sensors[i].createMessage());

                try {
                    client.publish(sensors[i].returnName(), message);
                } catch (MqttException e) {
                    e.printStackTrace();
                } catch (NullPointerException n) {
                    n.printStackTrace();
                    System.exit(1);
                }
            }
            Thread.sleep(5000);
        }
    }

    //Setter functions
    void setBrookerURL(String str) {
        brookerURL = str;
    }

    void setSensors(SensorData[] sen) {
        sensors = sen;
    }
}
