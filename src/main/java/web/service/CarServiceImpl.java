package web.service;

import org.springframework.stereotype.Component;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
@Component
public class CarServiceImpl implements CarService {
    private List<Car> listCar;

    {
        listCar = new ArrayList<>();
        listCar.add(new Car("BMW", 2010, "black"));
        listCar.add(new Car("Reno", 2020, "white"));
        listCar.add(new Car("Lexus", 2023, "grey"));
        listCar.add(new Car("Ford", 2009, "yellow"));
        listCar.add(new Car("Tesla_plaid", 2024, "red"));
    }

    public List<Car> showCars(int count) {
        if (count == 1) {
            return listCar.subList(0, count);
        } else if (count == 2) {
            return listCar.subList(0, count);
        } else if (count == 3) {
            return listCar.subList(0, count);
        } else if (count == 4) {
            return listCar.subList(0, count);
        } else {
            return listCar;
        }
    }

}
