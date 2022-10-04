package com.sample.carmarket.entity.car;

import com.sample.carmarket.entity.CarStatus;
import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@UiController("Car.browse")
@UiDescriptor("car-browse.xml")
@LookupComponent("carsTable")
public class CarBrowse extends StandardLookup<Car> {
    @Autowired
    private GroupTable<Car> carsTable;
    @Autowired
    private Notifications notifications;
    @Autowired
    private DataManager dataManager;

    @Subscribe("markBtn")
    public void onMarkBtnClick(Button.ClickEvent event) {
        // Логику можно перенести в сервис
        Car car = carsTable.getSingleSelected();
        if (car != null) {
            if (car.getStatus() == CarStatus.SOLD) {
                notifications.create().withCaption("Already Sold").withType(Notifications.NotificationType.WARNING).show();
                return;
            }

            if (car.getStatus() == CarStatus.STOCK) {
                car.setStatus(CarStatus.SOLD);
                car.setDateOfSale(LocalDate.now());
                dataManager.save(car);
                notifications.create().withCaption("Done").withType(Notifications.NotificationType.WARNING).show();
                carsTable.loadPresentations();
            }
        }

    }


}