package com.nice.coday;


import org.apache.poi.ss.formula.eval.NotImplementedException;
// import MyCSVToJsonConverter

import java.io.IOException;

public class ElectricityConsumptionCalculatorImpl implements ElectricityConsumptionCalculator {
    @Override
    public ConsumptionResult calculateElectricityAndTimeConsumption(ResourceInfo resourceInfo) throws IOException {
        // Your implementation will go here
        System.out.println("HIIIIIII KIRAN KANKARIYA");
        System.out.println(resourceInfo.vehicleTypeInfoPath);
        MyCSVToJsonConverter obj = new MyCSVToJsonConverter();
        obj.Converter(resourceInfo.vehicleTypeInfoPath.normalize().toString());
        ConsumptionResult ans = new ConsumptionResult();
        throw new NotImplementedException("Not implemented yet.");
    }
}
