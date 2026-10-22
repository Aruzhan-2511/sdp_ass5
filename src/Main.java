public class Main {
    public static void main(String[] args){
        int distance = 100;
        int weight = 3;
        DeliveryCalculator calculator = new DeliveryCalculator(new StandardDelivery());
        System.out.println("Standard Delivery: "+ calculator.calculate(distance,weight)+" KZT");
        calculator.setStrategy(new ExpressDelivery());
        System.out.println("Express Delivery: "+calculator.calculate(distance,weight)+" KZT");
        calculator.setStrategy(new EcoDelivery());
        System.out.println("Eco Delivery: "+calculator.calculate(distance,weight)+" KZT");
    }
}
