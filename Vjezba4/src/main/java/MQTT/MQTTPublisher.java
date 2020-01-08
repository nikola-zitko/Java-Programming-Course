package MQTT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;


public class MQTTPublisher {
    public static void main(String[] args) throws InterruptedException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            WaterMeasurer waterData = mapper.readValue(new File(".\\config.json"), WaterMeasurer.class);
            waterData.run();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

