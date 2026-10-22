public class StandardDelivery implements DeliveryStrategy{
    public int calculateCost(int distance, int weight){
        if(distance <= 0 || weight <= 0){
            throw new IllegalArgumentException("Invalid distance or weight.");
        }
        return 500 + distance * 50 + weight * 100;
    }
}
