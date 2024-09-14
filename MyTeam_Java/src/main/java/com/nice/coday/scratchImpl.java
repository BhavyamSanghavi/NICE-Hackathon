class VehicleClass
{
    public:
    string type;
    int currentPos, exit, remainingBattery, mileage, time=0, unitsConsumed=0, capacity;

    VehicleClass(string vehicleType, string entryPoint, int batteryPercentage, string exitPoint)
    {
        this.type=vehicleType;
        this.capacity=getCapacity(this.type);
        this.mileage=getMileage(this.type);

        this.remainingBattery=batteryPercentage*capacity/100;
        this.currentPos=positionOfPoint[entryPoint];
        this.exit=positionOfPoint[exitPoint];
    }
};

class StationClass
{
    public:
    string name;
    int pos,time=0;
};

class TripClass
{
    public:
    string vehicleType;
    int remainingBatteryPercentage, entry, exit;
};

// -------------------------- class declarations over ------------------

vector<vector<int>> timeToChargeMap;
map<string,int>positionOfPoint;
vector<TripClass>trips;
vector<StationClass>stations;

// ------------------------- data structures used declared ------------------




int getTime(VehicleClass vehicle, StationClass station, int unitsToCharge)
{
    return timeToChargeMap[vehicle.type][station.name]*unitsToCharge;
}

// private List<ConsumptionDetails> consumptionDetails = new ArrayList<>();
// public Map<String, Long> totalChargingStationTime = new HashMap<>();

// List<StationClass> stations;
// List<TripClass>trips;

string getNextStation(VehicleClass vehicle)
{   
    int maxDist=vehicle.currentPos + vehicle.mileage * vehicle.remainingBattery;
    if(maxDist>=vehicle.exit) return "end";
    int i=0;
    for(i=0;i<stations.size();i++)
    {
        if(stations[i].pos > maxDist)
        {
            break;
        }
    }

    if(i==0) return "invalid";
    StationClass station = stations[i-1];

    int distanceTravelled=station.pos - vehicle.currentPos;
    int unitsToCharge = vehicle.capacity - (vehicle.remainingBattery - distanceTravelled/vehicle.mileage);
    int time=getTime(vehicle,station,unitsToCharge);

    vehicle.time+=time;
    vehicle.remainingBattery=vehicle.capacity;
    vehicle.currentPos=stations[i-1].pos;
    vehicle.unitsConsumed+=vehicle.capacity;
    stations[i-1].time += time;

    return station.name;
}

void processTrip()
{
    for(int i=0;i<trips.size();i++)
    {
        TripClass trip=trips[i];
        VehicleClass vehicle(trip.vehicleType , trip.entry, trip.remainingBattery, trip.exit);
        string nextStation=trip.entry;
        while(nextStation!="end" and nextStation!="invalid")
        {
            nextStation=getNextStation(vehicle);
        }
        if(nextStation=="invalid") 
        {
            vehicleMap[vehicle.type].totalUnitsConsumed+=vehicle.unitsConsumed;
            vehicleMap[vehicle.type].totalTimeRequired +=vehicle.time;
        }
        else if(nextStation=="end") 
        {
            vehicleMap[vehicle.type].totalUnitsConsumed+=vehicle.unitsConsumed;
            vehicleMap[vehicle.type].totalTimeRequired +=vehicle.time;
            vehicleMap[vehicle.type].numberOfTripsFinished +=1;
        }
    }   
    
}

void reachedEnd(VehicleClass vehicle)
{
    vehicleMap[vehicle.type].totalUnitsConsumed+=vehicle.unitsConsumed;
    vehicleMap[vehicle.type].totalTimeRequired +=vehicle.time;
    vehicleMap[vehicle.type].numberOfTripsFinished +=1;
}
