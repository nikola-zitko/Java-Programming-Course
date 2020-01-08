package MQTT;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

class WaterMeasurer {
    private String brookerURL;
    private SensorData[] sensors;
    private MqttClient client;

    MqttClient ClientStart(){
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

    public class Dretva extends Thread{
            public void run(){
                while(true){
                        MqttMessage message = new MqttMessage(sensors[Integer.parseInt(getName())].createMessage());
                        try {
                            client.publish(sensors[Integer.parseInt(getName())].returnName(), message);
                        } catch (MqttException e) {
                            e.printStackTrace();
                        } catch (NullPointerException n) {
                            n.printStackTrace();
                            System.exit(1);
                        }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
    }

    void run() throws InterruptedException {
        client = ClientStart();
        Dretva[] Dretve = new Dretva[sensors.length];
        for (int i = 0; i < sensors.length; i++) {
            Dretve[i] = new Dretva();
            Dretve[i].setName(Integer.toString(i));
            Dretve[i].start();
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
