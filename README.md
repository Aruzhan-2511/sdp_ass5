# Assignment 5 – Strategy Design Pattern

## Delivery Cost System

This project demonstrates the **Strategy Design Pattern** using a Delivery Cost System.

The main purpose of the project is to calculate delivery costs using different algorithms.

The system supports three delivery methods:

- Standard Delivery
- Express Delivery
- Eco Delivery

Each delivery method uses its own algorithm to calculate the cost based on distance and package weight.

The Strategy Pattern allows the system to switch between these algorithms at runtime without changing the Context class.

## Strategy Pattern Structure

### Strategy Interface – DeliveryStrategy

`DeliveryStrategy` is the common interface for all delivery strategies.

It declares the method:

```java
public interface DeliveryStrategy {
    int calculateCost(int distance, int weight);
}
```

This method defines a common contract for calculating delivery costs.

All Concrete Strategies implement this interface but use different algorithms.

### Concrete Strategy 1 – StandardDelivery

`StandardDelivery` implements the `DeliveryStrategy` interface.

It calculates the cost of regular delivery.

```java
public class StandardDelivery implements DeliveryStrategy {

    @Override
    public int calculateCost(int distance, int weight) {

        if (distance <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Invalid distance or weight.");
        }

        return 500 + distance * 50 + weight * 100;
    }
}
```

The calculation includes:

- Base price – 500 KZT
- Distance – 50 KZT per kilometer
- Weight – 100 KZT per kilogram

For example, 10 km and 3 kg:

500 + (10 × 50) + (3 × 100) = **1300 KZT**

### Concrete Strategy 2 – ExpressDelivery

`ExpressDelivery` also implements the `DeliveryStrategy` interface.

It uses a different algorithm for faster delivery.

The calculation includes:

- Base price – 1000 KZT
- Distance – 100 KZT per kilometer
- Weight – 200 KZT per kilogram

The main calculation is:

```java
return 1000 + distance * 100 + weight * 200;
```

For example, 10 km and 3 kg:

1000 + (10 × 100) + (3 × 200) = **2600 KZT**

### Concrete Strategy 3 – EcoDelivery

`EcoDelivery` implements the same interface but uses an economical delivery algorithm.

The calculation includes:

- Base price – 300 KZT
- Distance – 40 KZT per kilometer
- The first 2 kg are included in the base price
- Each additional kilogram costs 100 KZT

The main calculation is:

```java
int extraWeight = Math.max(0, weight - 2);

return 300 + distance * 40 + extraWeight * 100;
```

For example, 10 km and 3 kg:

300 + (10 × 40) + (1 × 100) = **800 KZT**

All three Concrete Strategies have the same method, `calculateCost()`, but each uses a different algorithm.

## Context – DeliveryCalculator

`DeliveryCalculator` is the Context class of the Strategy Pattern.

It stores a reference to the Strategy interface:

```java
private DeliveryStrategy strategy;
```

The initial strategy is passed through the constructor:

```java
public DeliveryCalculator(DeliveryStrategy strategy) {
    setStrategy(strategy);
}
```

The `setStrategy()` method allows the Context to change its strategy at runtime.

```java
public void setStrategy(DeliveryStrategy strategy) {
    if (strategy == null) {
        throw new IllegalArgumentException("Strategy cannot be null.");
    }

    this.strategy = strategy;
}
```

The Context delegates the calculation to the selected strategy:

```java
public int calculate(int distance, int weight) {
    return strategy.calculateCost(distance, weight);
}
```

`DeliveryCalculator` does not know the internal algorithms of `StandardDelivery`, `ExpressDelivery`, or `EcoDelivery`.

It only works through the `DeliveryStrategy` interface.

There is no `if-else` or `switch` statement for selecting the strategy inside the Context.

## Client – Main

`Main` is the Client class.

It creates the Context and selects the delivery strategies.

First, the client creates `DeliveryCalculator` with `StandardDelivery`:

```java
DeliveryCalculator calculator =
        new DeliveryCalculator(new StandardDelivery());
```

Then the client can change the strategy:

```java
calculator.setStrategy(new ExpressDelivery());
```

And later:

```java
calculator.setStrategy(new EcoDelivery());
```

The same Context object is used for all three strategies.

The client calculates the cost using:

```java
calculator.calculate(distance, weight);
```

This demonstrates runtime strategy switching without modifying the Context class.

## Project Structure

```text
src/
├── DeliveryStrategy.java
├── StandardDelivery.java
├── ExpressDelivery.java
├── EcoDelivery.java
├── DeliveryCalculator.java
└── Main.java
```

## Clean Code Requirements

### 1. Elimination of Conditional Logic Through Polymorphism

The project uses polymorphism instead of `if-else` or `switch` statements to select delivery algorithms.

All Concrete Strategies implement the same `DeliveryStrategy` interface.

The Context calls:

```java
strategy.calculateCost(distance, weight);
```

Java automatically executes the method of the selected Concrete Strategy.

This makes the code simpler and avoids unnecessary conditional logic.

### 2. Meaningful Names

The project uses clear and descriptive names.

For example:

- `DeliveryStrategy` defines the common delivery algorithm.
- `StandardDelivery` represents regular delivery.
- `ExpressDelivery` represents fast delivery.
- `EcoDelivery` represents economical delivery.
- `DeliveryCalculator` calculates delivery costs using the selected strategy.

Methods such as `calculateCost()`, `setStrategy()`, and `calculate()` clearly describe their purposes.

This makes the code easier to understand.

### 3. Single Responsibility Principle

Each class has one main responsibility.

- `StandardDelivery` calculates standard delivery costs.
- `ExpressDelivery` calculates express delivery costs.
- `EcoDelivery` calculates economical delivery costs.
- `DeliveryCalculator` manages and uses the selected strategy.
- `Main` selects and configures strategies.

Each algorithm is implemented in a separate class.

This keeps responsibilities separated and makes the system easier to maintain.

### 4. Context Independence

The Context depends on the `DeliveryStrategy` interface instead of specific delivery classes.

For example:

```java
private DeliveryStrategy strategy;
```

`DeliveryCalculator` does not contain the formulas for Standard, Express, or Eco Delivery.

It delegates the calculation to the selected strategy.

As a result, changes to one delivery algorithm do not require changes to the Context.

### 5. Easy Extensibility

The system allows new delivery strategies to be added without modifying the existing Context or Concrete Strategies.

For example, a new strategy called `PremiumDelivery` could be created:

```java
public class PremiumDelivery implements DeliveryStrategy {

    @Override
    public int calculateCost(int distance, int weight) {
        return 1500 + distance * 150 + weight * 250;
    }
}
```

The client could then use it:

```java
calculator.setStrategy(new PremiumDelivery());
```

The `DeliveryCalculator` class would not need any changes.

This follows the Open/Closed Principle: the system is open for extension but closed for modification.

## Why Strategy Pattern Is Used

Without the Strategy Pattern, the delivery calculator might contain conditional statements for different delivery methods:

```java
if (type.equals("standard")) {
    // Standard calculation
} else if (type.equals("express")) {
    // Express calculation
} else if (type.equals("eco")) {
    // Eco calculation
}
```

This approach makes the Context responsible for multiple algorithms.

Adding a new delivery method would require changing the existing conditional logic.

**With the Strategy Pattern**, each algorithm is placed in its own class.

The structure is:

```text
                  Main
                   |
                   v
           DeliveryCalculator
                (Context)
                   |
                   v
            DeliveryStrategy
               (Interface)
                   ^
             implements
          /        |        \
         /         |         \
 StandardDelivery  |     EcoDelivery
              ExpressDelivery
```

The Context uses composition to hold a reference to the Strategy interface:

```java
private DeliveryStrategy strategy;
```

The same Context can work with different Concrete Strategies.

The client selects the strategy, and the Context delegates the calculation to it.

As a result, delivery algorithms can be changed independently without changing the Context class.

## Example Output

For a delivery distance of 10 km and package weight of 3 kg:

```text
Standard Delivery: 1300 KZT
Express Delivery: 2600 KZT
Eco Delivery: 800 KZT
```

## How to Run

1. Open the project in IntelliJ IDEA.
2. Open `Main.java`.
3. Run the `main()` method.
4. The console displays the costs for all three delivery strategies.

## Conclusion

The Strategy Design Pattern allows different algorithms to be defined in separate classes and changed at runtime.

In this project, `DeliveryStrategy` defines a common interface, while `StandardDelivery`, `ExpressDelivery`, and `EcoDelivery` provide different calculation algorithms.

`DeliveryCalculator` acts as the Context and delegates calculations to the selected strategy.

This design reduces conditional logic, separates responsibilities, and makes it easier to add new delivery methods without modifying existing classes.
