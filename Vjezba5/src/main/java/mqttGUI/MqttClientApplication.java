package mqttGUI;

import mqttGUI.mqtt.MqttSubscriber;
import mqttGUI.windows.MainClientWindow;

public class MqttClientApplication {


    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainClientWindow(new MqttSubscriber());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
