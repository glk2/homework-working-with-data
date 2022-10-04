package com.sample.carmarket.app;

import com.sample.carmarket.entity.Car;
import com.sample.carmarket.entity.EngineType;
import com.sample.carmarket.entity.Manufacturer;
import io.jmix.core.DataManager;
import io.jmix.core.entity.KeyValueEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarService {

    @Autowired
    private DataManager dataManager;

    public String calculateCar(Manufacturer manufacturer) {


        List<KeyValueEntity> kvEntities = dataManager.loadValues(
                        "select c.modelId.engineType, COUNT(c.modelId.engineType) " +
                                "from Car c " +
                                "where c.modelId.manufacturer = :modelIdManufacturer1 " +
                                "group by c.modelId.engineType")
                .parameter("modelIdManufacturer1", manufacturer)
                .properties("engineType","cnt")
                .list();

        StringBuilder sb = new StringBuilder();
        for (KeyValueEntity kvEntity : kvEntities) {

            String engineType = kvEntity.getValue("engineType");
            EngineType engineType1 = EngineType.fromId(engineType);
            String s1 = engineType1.toString();
            Long cnt = kvEntity.getValue("cnt");
            sb.append(s1).append(" : ").append(String.valueOf(cnt)).append("\n");
        }

        String cntStr = sb.toString();
        return cntStr;
    }
}