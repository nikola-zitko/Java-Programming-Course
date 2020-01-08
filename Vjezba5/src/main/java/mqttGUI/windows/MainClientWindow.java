package mqttGUI.windows;

import mqttGUI.mqtt.MqttSubscriber;
import org.eclipse.paho.client.mqttv3.*;
import mqttGUI.models.MqttConfigModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

public class MainClientWindow extends JFrame implements MqttCallback {

    JPanel panel;

    MqttSubscriber subscriber;
    MqttClient client;

    private String[] topics;
    ObjectMapper mapper = new ObjectMapper();
    MqttConfigModel model;

    public MainClientWindow(MqttSubscriber subscriber) throws Exception {


        model = mapper.readValue(new File("src/main/resources/appConfig.json"), MqttConfigModel.class);
        topics = model.getTopics();
        //Initialize MqttSubscriber instance

        this.subscriber = subscriber;
        this.client = subscriber.call();
        client.setCallback(this);
        client.connect();
        for(String topic : topics){
            client.subscribe(topic);
        }


        //Set layout, dimensions and padding for GUI
        panel = new JPanel( new GridLayout(8,2 ));
        panel.setBackground(Color.gray);
        Border padding = BorderFactory.createEmptyBorder(0, 10, 10, 10);
        panel.setBorder(padding);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel, BorderLayout.CENTER);
        setTitle("Mqtt Client GUI");
        setSize(300, 400);
        setVisible(true);

    }

    private void checkIfMessageExists(String s, MqttMessage message){

        boolean exists = false;

        //Check if topic already exists
        for(Component c : panel.getComponents()){
            if(c instanceof JLabel && ((JLabel) c).getText().equals(s)){
                exists = true;
            }
        }

        //Create new set of GUI elements for new Topic
        if(!exists){
            JLabel label = new JLabel(s);
            JTextField textField = new JTextField(message.toString());
            textField.setName(s);
            this.panel.add(label);
            this.panel.add(textField);

        } else {
            //Change values of existing Topic elements
            for(Component c : panel.getComponents()){
                if(c instanceof JLabel && ((JLabel) c).getText().equals(s)){
                    ((JLabel) c).setText(s);
                }
                if(c instanceof JTextField && c.getName().equals(s)) {
                    ((JTextField) c).setText(message.toString());
                }
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to Broker lost");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) {
        checkIfMessageExists(s, mqttMessage);
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        try {
            System.out.println("Message successfully delivered" + iMqttDeliveryToken.getMessage());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}
