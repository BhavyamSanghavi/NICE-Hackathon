package com.nice.coday;
import org.json.JSONArray;
import org.json.JSONObject;

import org.apache.poi.ss.formula.eval.NotImplementedException;
// import MyCSVToJsonConverter

import java.io.IOException;

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
        System.out.println(chargeTime.getJSONObject(0).getString("TimeToChargePerUnit"));
        
        

        // ConsumptionResult ans = new ConsumptionResult();
        // return ans;
        throw new NotImplementedException("Not implemented yet.");
    }
}
