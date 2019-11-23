package mqmt;


public class App {
    public static void main(String[] args) throws InterruptedException {
        WaterMeasure WaterData = new WaterMeasure();
        WaterData.run();
        //Serijalizirati u JSON i onda obrnuto
    }
}
