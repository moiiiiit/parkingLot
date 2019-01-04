//Mohit Rajendra Bhole mrb170330 CS2336.006

public class Spot {                             //this class is used to represent each parkin spot in the lot
    Vehicle parked;                             //parked is a vehicle object that will save reference to the vehicle it occupies
    private VehicleTypeSize size;               //size is the size of the parking spot (compact/motorcycle/large)

    Spot(){
        size = VehicleTypeSize.Compact;
    }

    boolean canFit(Vehicle v){                  //this returns true if the spot can fir vehicle v, it takes in the vehicle object and checks if the spot is big enough for it. if yes, it returns true
        if(size.equals(v.getSize())){           //else it returns false
            return true;
        }
        else if(v.getSize().equals(VehicleTypeSize.Motorcycle)){
            return true;
        }
        else if(size.equals(VehicleTypeSize.Large) && v.getSize().equals(VehicleTypeSize.Compact)){
            return true;
        }
        else
            return false;
    }

    public boolean park(Vehicle v){             //this function parks a vehicle v in the spot. it takesn in the parameter of vehicle v, it parks it in the spot, and returns true if it was succesful
        if(canFit(v) && parked==null){
            parked = v;
            return true;
        }
        return false;
    }

    public void remove(){                       //this function removes it
        parked = null;
    }

    public boolean availability(){              //this function checks the availability of this spot. returns true if a vehicle is not parked in the spot, false otherwise
        if(parked==null){
            return true;
        }
        else
            return false;
    }

    public void setSize(VehicleTypeSize x){
        size = x;
    }       //this function sets the size of the parking spot to the enum values(large, compact, motorcycle)

    public void print(){                        //this function prints the parkinglot according to the given standards. does not return any value
        if(parked!=null){
            if(parked instanceof Bus){
                System.out.print("B");
            }
            if(parked instanceof Car){
                System.out.print("C");
            }
            if(parked instanceof Motorcycle){
                System.out.print("M");
            }
        }
        else {
            switch (size) {
                case Motorcycle:
                    System.out.print("m");
                    break;
                case Compact:
                    System.out.print("c");
                    break;
                case Large:
                    System.out.print("l");
                    break;
            }
        }
        return;
    }
}
