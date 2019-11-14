package mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Publisher {

    public static final String BROKER_URL = "tcp://localhost:1883";
    protected String publisherId = MqttClient.generateClientId();
    protected MqttClient publisher;

    public Publisher() throws MqttException {
        System.out.println("== START PUBLISHER ==");
        publisher = new MqttClient(BROKER_URL, publisherId);
    }
}