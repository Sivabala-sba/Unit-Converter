package com.project.UnitConverter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WeightConverterController {

    @GetMapping("/weight")
    public String weightPage() {
        return "weight";
    }

    @PostMapping("/convertWeight")
    public String convertWeight(@RequestParam double value,
                                @RequestParam String fromUnit,
                                @RequestParam String toUnit,
                                Model model) {
        double convertedValue = convertWeightValue(value, fromUnit, toUnit);
        model.addAttribute("convertedValue", convertedValue);
        model.addAttribute("value", value);
        model.addAttribute("fromUnit", fromUnit);
        model.addAttribute("toUnit", toUnit);
        return "weight";  // Renders weight.html with result
    }

    private double convertWeightValue(double value, String fromUnit, String toUnit) {
        // Convert from the original unit to grams
        double grams = 0;

        switch (fromUnit) {
            case "mg":
                grams = value / 1000; // Convert milligrams to grams
                break;
            case "g":
                grams = value; // Already in grams
                break;
            case "kg":
                grams = value * 1000; // Convert kilograms to grams
                break;
            case "oz":
                grams = value * 28.3495; // Convert ounces to grams
                break;
            case "lb":
                grams = value * 453.592; // Convert pounds to grams
                break;
            default:
                throw new IllegalArgumentException("Invalid fromUnit: " + fromUnit);
        }

        // Now convert from grams to the target unit
        switch (toUnit) {
            case "mg":
                return grams * 1000; // Convert grams to milligrams
            case "g":
                return grams; // Already in grams
            case "kg":
                return grams / 1000; // Convert grams to kilograms
            case "oz":
                return grams / 28.3495; // Convert grams to ounces
            case "lb":
                return grams / 453.592; // Convert grams to pounds
            default:
                throw new IllegalArgumentException("Invalid toUnit: " + toUnit);
        }
    }
}

