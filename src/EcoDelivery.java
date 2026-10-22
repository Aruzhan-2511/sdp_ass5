public class EcoDelivery implements DeliveryStrategy{
    public int calculateCost(int distance, int weight){
        if(distance <=0 || weight <= 0){
            throw new IllegalArgumentException("Invalid distance or weight.");
        }
        int extraWeight = Math.max(0, weight - 2);
        return 300 + distance * 40 + extraWeight * 100;
    }
}
