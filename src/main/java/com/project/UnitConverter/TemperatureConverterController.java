package com.project.UnitConverter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TemperatureConverterController {

    @GetMapping("/temperature")
    public String temperaturePage() {
        return "temperature";
    }

    @PostMapping("/convertTemperature")
    public String convertTemperature(@RequestParam double value,
                                     @RequestParam String fromUnit,
                                     @RequestParam String toUnit,
                                     Model model) {
        double convertedValue = convertTemperatureValue(value, fromUnit, toUnit);
        model.addAttribute("convertedValue", convertedValue);
        model.addAttribute("value", value);
        model.addAttribute("fromUnit", fromUnit);
        model.addAttribute("toUnit", toUnit);
        return "temperature";  // Renders temperature.html with result
    }

    private double convertTemperatureValue(double value, String fromUnit, String toUnit) {
        double celsius;

        // Convert from the original unit to Celsius
        switch (fromUnit) {
            case "C":
                celsius = value; // Already in Celsius
                break;
            case "F":
                celsius = (value - 32) * 5 / 9; // Convert Fahrenheit to Celsius
                break;
            case "K":
                celsius = value - 273.15; // Convert Kelvin to Celsius
                break;
            default:
                throw new IllegalArgumentException("Invalid fromUnit: " + fromUnit);
        }

        // Now convert from Celsius to the target unit
        switch (toUnit) {
            case "C":
                return celsius; // Already in Celsius
            case "F":
                return (celsius * 9 / 5) + 32; // Convert Celsius to Fahrenheit
            case "K":
                return celsius + 273.15; // Convert Celsius to Kelvin
            default:
                throw new IllegalArgumentException("Invalid toUnit: " + toUnit);
        }
    }
}
