package com.nice.coday;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import org.apache.poi.ss.formula.eval.NotImplementedException;
// import MyCSVToJsonConverter

import java.io.IOException;
import java.util.List;
import java.util.Map;

class TripClass {
    private String vehicleType;
    private int remainingBatteryPercentage;
    private String entryPoint;
    private String exitPoint;

    // Constructor
    public TripClass(JSONObject jsonObject) {
        this.vehicleType = jsonObject.getString("VehicleType");
        this.remainingBatteryPercentage = jsonObject.getInt("RemainingBatteryPercentage");
        this.entryPoint = jsonObject.getString("EntryPoint");
        this.exitPoint = jsonObject.getString("ExitPoint");
    }
}

public class ElectricityConsumptionCalculatorImpl implements ElectricityConsumptionCalculator {
    @Override
    string getNextStation(VehicleClass vehicle) {
        int maxDist = vehicle.currentPos + vehicle.mileage * vehicle.remainingBattery;
        if (maxDist >= vehicle.exit)
            return "end";
        int i = 0;
        for (i = 0; i < stations.size(); i++) {
            if (stations[i].pos > maxDist) {
                break;
            }
        }

        if (i == 0)
            return "invalid";
        StationClass station = stations[i - 1];

        int distanceTravelled = station.pos - vehicle.currentPos;
        int unitsToCharge = vehicle.capacity - (vehicle.remainingBattery - distanceTravelled / vehicle.mileage);
        int time = getTime(vehicle, station, unitsToCharge);

        vehicle.time += time;
        vehicle.remainingBattery = vehicle.capacity;
        vehicle.currentPos = stations[i - 1].pos;
        vehicle.unitsConsumed += vehicle.capacity;
        stations[i - 1].time += time;

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

    void reachedEnd(VehicleClass vehicle) {
        vehicleMap[vehicle.type].totalUnitsConsumed += vehicle.unitsConsumed;
        vehicleMap[vehicle.type].totalTimeRequired += vehicle.time;
        vehicleMap[vehicle.type].numberOfTripsFinished += 1;
    }

    public ConsumptionResult calculateElectricityAndTimeConsumption(ResourceInfo resourceInfo) throws IOException {
        // Your implementation will go here

        System.out.println(resourceInfo.vehicleTypeInfoPath);

        MyCSVToJsonConverter obj = new MyCSVToJsonConverter();
        JSONArray vehicle = obj.Converter(resourceInfo.vehicleTypeInfoPath.normalize().toString());

        JSONArray tripDetails = obj.Converter(resourceInfo.tripDetailsPath.normalize().toString());

        JSONArray entryExit = obj.Converter(resourceInfo.entryExitPointInfoPath.normalize().toString());
        JSONArray chargingStation = obj.Converter(resourceInfo.chargingStationInfoPath.normalize().toString());
        JSONArray chargeTime = obj.Converter(resourceInfo.timeToChargeVehicleInfoPath.normalize().toString());
        // System.out.println(chargeTime.getJSONObject(0).getString("TimeToChargePerUnit"));

        List<TripClass> trips = new ArrayList<>();
        for (int i = 0; i < tripDetails.length(); i++) {
            JSONObject jsonObject = tripDetails.getJSONObject(i);
            trips.add(new TripClass(jsonObject));
        }

        Map<String, Pair<Integer, Integer>> vehicleMap = new HashMap<>();
        for (int i = 0; i < vehicle.length(); i++) {
            String vehicleType = jsonObject.getString("VehicleType");
            int numberOfUnitsForFullyCharge = jsonObject.getInt("NumberOfUnitsForFullyCharge");
            int mileage = jsonObject.getInt("Mileage");
            Pair<Integer, Integer> pair = new Pair<>(numberOfUnitsForFullyCharge, mileage);
            vehicleMap.put(vehicleType, pair);
        }

        Map<Pair<Integer, Integer>, String> timeToChargeMap = new HashMap<>();
        for (int i = 0; i < chargeTime.length(); i++) {
            String vehicleType = jsonObject.getString("VehicleType");
            int chargeStation = jsonObject.getString("ChargingStation");
            int time = jsonObject.getInt("TimeToChargePerUnit");
            Pair<Integer, Integer> pair = new Pair<>(vehicleType, chargeStation);
            timeToChargeMap.put(pair, time);
        }

        Map<String, Integer> entryExitMap = new HashMap<>();
        for (int i = 0; i < entryExit.length(); i++) {
            String point = jsonObject.getString("EntryExitPoint");
            int distance = jsonObject.getInt("DistanceFromStart");
            entryExitMap.put(point, distance);
        }

        List<ConsumptionDetails> consumptionDetails = new ArrayList<>();
        Map<String, Long> totalChargingStationTime = new HashMap<>();

        

        throw new NotImplementedException("Not implemented yet.");
    }
}
