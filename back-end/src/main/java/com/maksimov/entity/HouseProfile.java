package com.maksimov.entity;

import com.maksimov.entity.house.ElectricityConfig;
import com.maksimov.entity.house.GasConfig;
import com.maksimov.entity.house.WasteWaterConfig;
import com.maksimov.entity.house.WaterSupplyConfig;

import javax.persistence.Entity;

/**
 * Created on 11.04.2016.
 */

@Entity
public class HouseProfile extends Base {

    private WaterSupplyConfig waterSupplyConfig;
    private WasteWaterConfig wasteWaterConfig;
    private GasConfig gasConfig;
    private ElectricityConfig electricityConfig;

    public WaterSupplyConfig getWaterSupplyConfig() {
        return waterSupplyConfig;
    }

    public void setWaterSupplyConfig(WaterSupplyConfig waterSupplyConfig) {
        this.waterSupplyConfig = waterSupplyConfig;
    }

    public WasteWaterConfig getWasteWaterConfig() {
        return wasteWaterConfig;
    }

    public void setWasteWaterConfig(WasteWaterConfig wasteWaterConfig) {
        this.wasteWaterConfig = wasteWaterConfig;
    }

    public GasConfig getGasConfig() {
        return gasConfig;
    }

    public void setGasConfig(GasConfig gasConfig) {
        this.gasConfig = gasConfig;
    }

    public ElectricityConfig getElectricityConfig() {
        return electricityConfig;
    }

    public void setElectricityConfig(ElectricityConfig electricityConfig) {
        this.electricityConfig = electricityConfig;
    }
}
