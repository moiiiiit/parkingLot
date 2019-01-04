//Mohit Rajendra Bhole mrb170330 CS2336.006

import java.util.*;

public class Main {

    public static void main(String[] args) {        //DRIVER FUNCTION
        Scanner input = new Scanner(System.in);
        ParkingLot parkingLot;
        System.out.println("Use default values? (Y) ");         //ask user to use default values
        if(input.next().equals("Y")){                           //creates the default parking lot
            parkingLot = new ParkingLot();
        }
        else {                                                  //else ask user for inputand create the parking lot acccordingly
            int numLevels;
            int spotsPerLevel;
            int spotsPerRow;
            double fractionLarge;
            double fractionMotorcycle;

            System.out.println("Enter the number of levels: ");
            numLevels = input.nextInt();
            System.out.println("Enter the number of parking spots per level: ");
            spotsPerLevel = input.nextInt();
            System.out.println("Enter the number of parking spots per row: ");
            spotsPerRow = input.nextInt();
            System.out.println("Enter the percentage of large spots per level: ");
            fractionLarge = input.nextFloat() / 100;
            System.out.println("Enter the percentage of motorcycle spots per level: ");
            fractionMotorcycle = input.nextFloat() / 100;

            parkingLot = new ParkingLot(numLevels,spotsPerLevel,spotsPerRow,fractionLarge,fractionMotorcycle);
        }

        parkingLot.print();

        exit:                                                       //PARKING LOT MENU
        while(true) {
            System.out.println("Press 1 to add vehicle: ");
            System.out.println("Press 2 to remove vehicle: ");
            System.out.println("Press 3 to exit: ");
            switch (input.nextInt()) {
                case 1:
                    System.out.println("Enter type of vehicle(C/M/B): ");
                    switch (input.next()) {
                        case "C":
                            Car car = new Car();
                            System.out.println("Enter parking spot (enter the level and spot number): ");
                            parkingLot.park(car, input.nextInt()-1, input.nextInt()-1);
                            break;
                        case "M":
                            Motorcycle motorcycle = new Motorcycle();
                            System.out.println("Enter parking spot (enter the level and spot number): ");
                            parkingLot.park(motorcycle, input.nextInt()-1, input.nextInt()-1);
                            break;
                        case "B":
                            Bus bus = new Bus();
                            System.out.println("Enter parking spot (enter the level and the spot numbers one by one): ");
                            if(!parkingLot.park(bus, input.nextInt()-1, input.nextInt()-1, input.nextInt()-1, input.nextInt()-1, input.nextInt()-1, input.nextInt()-1)){
                                System.out.println("At least one of the spots entered was already taken. Use the table to find empty spots.");
                            }
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                    parkingLot.print();
                    break;
                case 2:
                    System.out.println("Enter the parking spot to remove (enter the level and then parking spot. For buses, just enter the first parking spot occupied): ");
                    parkingLot.remove(input.nextInt()-1, input.nextInt()-1);
                    parkingLot.print();
                    break;
                case 3:
                    parkingLot.print();
                    break exit;
            }
        }

    }
}
