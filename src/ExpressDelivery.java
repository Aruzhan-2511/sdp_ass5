public class ExpressDelivery implements DeliveryStrategy{
    public int calculateCost(int distance, int weight){
        if(distance <= 0 || weight <= 0){
            throw new IllegalArgumentException("Invalid distance or weight");
        }
        return 1000 + distance * 100 + weight * 200;
    }
}
