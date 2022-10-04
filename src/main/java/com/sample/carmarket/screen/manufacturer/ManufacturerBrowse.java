package com.sample.carmarket.screen.manufacturer;

import com.sample.carmarket.app.CarService;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Manufacturer;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Manufacturer.browse")
@UiDescriptor("manufacturer-browse.xml")
@LookupComponent("table")
public class ManufacturerBrowse extends MasterDetailScreen<Manufacturer> {
    @Autowired
    private Notifications notifications;
    @Autowired
    private InstanceContainer<Manufacturer> manufacturerDc;
    @Autowired
    private Table<Manufacturer> table;
    @Autowired
    private CarService carService;

    @Subscribe("calculateCarBtn")
    public void onCalculateCarBtnClick(Button.ClickEvent event) {
        String cnt = " not selected";
            Manufacturer manufacturer = table.getSingleSelected();
            if (manufacturer != null) {
                //num = manufacturer.getId().toString();
                cnt = carService.calculateCar(manufacturer);
            }

        notifications.create().withCaption(cnt).withType(Notifications.NotificationType.TRAY).show();

    }


}