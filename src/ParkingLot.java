//Mohit Rajendra Bhole mrb170330 CS2336.006

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class ParkingLot {                                           //main parkinglot class includes all the levels and rows
    ParkingLevels [] parkingLevels;                 //the variables fractLarge, fractMotorcycle, spotsPerLevel, numLevels, spotsPerRow are used to make the parkingLot, and are remembered for the parking, printing and removal functions
    static double fractLarge;             //first fractLarge*totalspots are large
    static double fractMotorcycle;        //last fractMotorcycle*totalspots are motorcycle spots
    static int spotsPerLevel;
    static int numLevels;
    static int spotsPerRow;

    ParkingLot(){                                       //this class includes all the parkinglot data. this class creates 1D arrays of parkinglevels
        numLevels = 5;
        spotsPerRow = 10;
        spotsPerLevel = 30;
        fractLarge = 0.2;
        fractMotorcycle = 0.2;

        parkingLevels = new ParkingLevels[numLevels];

        for(int i=0;i<numLevels;i++){
            parkingLevels[i] = new ParkingLevels();
        }

        //THIS SETS THE SPOTS TO l and m
        int numLarge = (int)(fractLarge*spotsPerLevel);
        int numMotorcycle = (int)(fractMotorcycle*spotsPerLevel);
        for(int k=0;k<numLevels;k++){
            for(int i=0;i<spotsPerLevel/spotsPerRow;i++){
                for(int j=0;j<spotsPerRow;j++){

                    if(numLarge>0){
                        parkingLevels[k].parkingRows[i].parkingSpot[j].setSize(VehicleTypeSize.Large);
                        numLarge--;
                    }
                }
            }
            numLarge = (int)(fractLarge*spotsPerLevel);
        }

        for(int k=numLevels-1;k>=0;k--){
            for(int i=(spotsPerLevel/spotsPerRow)-1;i>=0;i--){
                for(int j=spotsPerRow-1;j>=0;j--){
                    if(numMotorcycle>0){
                        parkingLevels[k].parkingRows[i].parkingSpot[j].setSize(VehicleTypeSize.Motorcycle);
                        numMotorcycle--;
                    }
                }
            }
            numMotorcycle = (int)(fractMotorcycle*spotsPerLevel);
        }

    }

    ParkingLot(int a,int b,int c,double d,double e){                //overloaded constructor
        fractLarge = d;
        fractMotorcycle = e;
        numLevels = a;
        spotsPerLevel = b;
        spotsPerRow = c;

        parkingLevels = new ParkingLevels[numLevels];

        for(int i=0;i<numLevels;i++){
            parkingLevels[i] = new ParkingLevels();
        }

        //THIS SETS THE SPOTS TO l and m
        int numLarge = (int)(fractLarge*spotsPerLevel);
        int numMotorcycle = (int)(fractMotorcycle*spotsPerLevel);
        for(int k=0;k<numLevels;k++){
            for(int i=0;i<spotsPerLevel/spotsPerRow;i++){
                for(int j=0;j<spotsPerRow;j++){

                    if(numLarge>0){
                        parkingLevels[k].parkingRows[i].parkingSpot[j].setSize(VehicleTypeSize.Large);
                        numLarge--;
                    }
                }
            }
            numLarge = (int)(fractLarge*spotsPerLevel);
        }

        for(int k=numLevels-1;k>=0;k--){
            for(int i=(spotsPerLevel/spotsPerRow)-1;i>=0;i--){
                for(int j=spotsPerRow-1;j>=0;j--){
                    if(numMotorcycle>0){
                        parkingLevels[k].parkingRows[i].parkingSpot[j].setSize(VehicleTypeSize.Motorcycle);
                        numMotorcycle--;
                    }
                }
            }
            numMotorcycle = (int)(fractMotorcycle*spotsPerLevel);
        }

    }


    public void print(){                    //this loop prints the parking lot, and returns nothing
        for(int k=0;k<numLevels;k++) {
            System.out.print("Level" + (k+1) + ": ");
            for (int j = 0; j < spotsPerLevel / spotsPerRow; j++) {
                for (int i = 0; i < spotsPerRow; i++) {
                    parkingLevels[k].parkingRows[j].parkingSpot[i].print();;
                }
            }
            System.out.println("");
        }
    }

    //if its already taken
    public boolean park(Vehicle x,int lev,int a){           //this loop parks cars and motorcycles, the parameters are the vehicle object, the level and the spot number. it returns true
        int row = (int)((a)/(spotsPerRow));                 //if parking was successful, false if not
        int spotInRow = (a)%(spotsPerRow);
        if(parkingLevels[lev].parkingRows[row].parkingSpot[spotInRow].availability()) {
            if (parkingLevels[lev].parkingRows[row].parkingSpot[spotInRow].park(x)) {
                System.out.println(row + " " + spotInRow);
                return true;
            } else {
                System.out.println("You entered a motorcycle spot. Acceptable spots are between 0 and " + (int) (spotsPerLevel - fractMotorcycle * spotsPerLevel));
                System.out.println("Try Again\n");
                return false;
            }
        }
        else {
            System.out.println("This spot is already taken. Use the table to find an empty spot.");
            return false;
        }
    }

    public boolean park(Vehicle x,int lev,int a,int b,int c,int d,int e){       //this loop parks buses, and works the same way as the method above. the parameters are more, where a,b,c,d,e are the five different parking spost teh bus will occupy

        if(a>fractLarge*spotsPerLevel || b>fractLarge*spotsPerLevel ||c>fractLarge*spotsPerLevel ||d>fractLarge*spotsPerLevel ||e>fractLarge*spotsPerLevel){
            System.out.println("You entered " + a + ", " + b + ", " + c + ", " + d + ", " + e);
            System.out.println("At least one of the entered spots are not large parking spots. On each level, the acceptable spots are 0 to " + (int)(fractLarge*spotsPerLevel));
            System.out.println("Try Again\n");
            return false;
        }
        int spots[] = {a,b,c,d,e};

        for(int i =0;i<5;i++) {
            int row = (int) ((spots[i]) / (spotsPerRow));
            int spotInRow = (spots[i]) % (spotsPerRow);

            parkingLevels[lev].parkingRows[row].parkingSpot[spotInRow].park(x);
        }
        return true;
    }

    public void remove(int lev, int a){             //this loop removes vehicles from the parking spot. it takes in the parameters level and parkingspot number and returns nothing
        int row = (int)((a)/(spotsPerRow));
        int spotInRow = (a)%(spotsPerRow);

        if(parkingLevels[lev].parkingRows[row].parkingSpot[spotInRow].parked instanceof Bus) {
            parkingLevels[lev].parkingRows[row].parkingSpot[spotInRow].remove();
            parkingLevels[lev].parkingRows[row].parkingSpot[spotInRow+1].remove();
            parkingLevels[lev].parkingRows[row].parkingSpot[spotInRow+2].remove();
            parkingLevels[lev].parkingRows[row].parkingSpot[spotInRow+3].remove();
            parkingLevels[lev].parkingRows[row].parkingSpot[spotInRow+4].remove();
        }
        else
            parkingLevels[lev].parkingRows[row].parkingSpot[spotInRow].remove();
    }
}


class ParkingLevels{                                    //this class stores the different rows in the parking lot
    ParkingRows[] parkingRows;

    ParkingLevels(){
        parkingRows = new ParkingRows[ParkingLot.spotsPerLevel/ParkingLot.spotsPerRow];         //create the parking rows array which will contain the endividual parking spots
        for(int i=0;i<ParkingLot.spotsPerLevel/ParkingLot.spotsPerRow;i++){
            parkingRows[i] = new ParkingRows();
        }
    }
}

class ParkingRows{                                       //this class stores every spot in a particular row in the lot
    Spot[] parkingSpot;

    ParkingRows() {
        parkingSpot = new Spot[ParkingLot.spotsPerRow];                                         //create the spots array which are the individual parking spots
        for (int i = 0; i < ParkingLot.spotsPerRow; i++) {
            parkingSpot[i] = new Spot();
        }
    }
}
