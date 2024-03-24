package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.CarService;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String getCars(ModelMap model, @RequestParam(value = "count", defaultValue = "5") int count) {
        switch (count) {
            case 1:
                model.addAttribute("cars", carService.showCars().get(0));
                break;
            case 2:
                model.addAttribute("cars", carService.showCars().subList(0, 2));
                break;
            case 3:
                model.addAttribute("cars", carService.showCars().subList(0, 3));
                break;
            case 4:
                model.addAttribute("cars", carService.showCars().subList(0, 4));
                break;
            default:
                model.addAttribute("cars", carService.showCars());
                break;
        }
        return "cars";
    }


}
