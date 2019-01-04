//Mohit Rajendra Bhole mrb170330 CS2336.006

import java.util.*;

public class Vehicle {      //this class is inherited by each of the vehicle types class. these classes are used for identification of vehicles
    int spotsRequired;
    VehicleTypeSize size;

    public VehicleTypeSize getSize()
    {
        return size;
    }
    
}

class Bus extends Vehicle
{
    public Bus()
    {
        spotsRequired = 5;
        size = VehicleTypeSize.Large;
    }

}

class Car extends Vehicle
{
    public Car()
    {
        spotsRequired = 1;
        size = VehicleTypeSize.Compact;
    }

}

class Motorcycle extends Vehicle
{
    public Motorcycle()
    {
        spotsRequired = 1;
        size = VehicleTypeSize.Motorcycle;
    }

}
