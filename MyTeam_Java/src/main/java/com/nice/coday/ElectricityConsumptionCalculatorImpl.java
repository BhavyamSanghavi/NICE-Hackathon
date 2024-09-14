package com.nice.coday;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import org.apache.poi.ss.formula.eval.NotImplementedException;
// import MyCSVToJsonConverter

import java.io.IOException;
import java.util.List;


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
    public ConsumptionResult calculateElectricityAndTimeConsumption(ResourceInfo resourceInfo) throws IOException {
        // Your implementation will go here
        
        System.out.println(resourceInfo.vehicleTypeInfoPath);

        MyCSVToJsonConverter obj = new MyCSVToJsonConverter();
        JSONArray vehicle =obj.Converter(resourceInfo.vehicleTypeInfoPath.normalize().toString());
        
        JSONArray tripDetails = obj.Converter(resourceInfo.tripDetailsPath.normalize().toString());
        
        JSONArray entryExit = obj.Converter(resourceInfo.entryExitPointInfoPath.normalize().toString());
        JSONArray chargingStation = obj.Converter(resourceInfo.chargingStationInfoPath.normalize().toString());
        JSONArray chargeTime = obj.Converter(resourceInfo.timeToChargeVehicleInfoPath.normalize().toString());
        // System.out.println(chargeTime.getJSONObject(0).getString("TimeToChargePerUnit"));
        
        
        List<TripClass> vehicles = new ArrayList<>();
        for (int i = 0; i < tripDetails.length(); i++) {
            JSONObject jsonObject = tripDetails.getJSONObject(i);
            vehicles.add(new TripClass(jsonObject));
        }
        
        Map<String, Pair<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < vehicle.length(); i++) {
            String vehicleType = jsonObject.getString("VehicleType");
            int numberOfUnitsForFullyCharge = jsonObject.getInt("NumberOfUnitsForFullyCharge");
            int mileage = jsonObject.getInt("Mileage");
            Pair<Integer, Integer> pair = new Pair<>(numberOfUnitsForFullyCharge, mileage);
            map.put(vehicleType, pair);
        }
        
        throw new NotImplementedException("Not implemented yet.");
    }
}
