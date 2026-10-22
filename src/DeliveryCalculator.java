public class DeliveryCalculator {
    private DeliveryStrategy strategy;
    public DeliveryCalculator(DeliveryStrategy strategy){
        setStrategy(strategy);
    }
    public void setStrategy(DeliveryStrategy strategy){
        if(strategy == null){
            throw new IllegalArgumentException("Strategy cannot be null.");
        }
        this.strategy = strategy;
    }
    public int calculate(int distance, int weight){
        return strategy.calculateCost(distance, weight);
    }
}
