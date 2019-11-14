package mqtt;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber {

    public static final String BROKER_URL = "tcp://localhost:1883";
    protected static String subscriberId = MqttClient.generateClientId();
    protected static MqttClient subscriber;

    public static void main(String[] args) throws MqttException {

        System.out.println("== START SUBSCRIBER ==");

        subscriber = new MqttClient(BROKER_URL, subscriberId);
        subscriber.connect();

        subscriber.subscribe("#");
    }
}