package com.project.UnitConverter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LengthConverterController {

    @RequestMapping("/length")
    public String lengthPage() {
        return "length"; // returns length.html
    }

    @PostMapping("/convertLength")
    public String convertLength(double value, String fromUnit, String toUnit, Model model) {
        double convertedValue = convertLengthValue(value, fromUnit, toUnit);
        model.addAttribute("value", value);
        model.addAttribute("fromUnit", fromUnit);
        model.addAttribute("toUnit", toUnit);
        model.addAttribute("convertedValue", convertedValue);
        return "length"; // returns length.html
    }

    private double convertLengthValue(double value, String fromUnit, String toUnit) {
        // Convert from the original unit to meters
        double meters = 0;

        switch (fromUnit) {
            case "mm":
                meters = value / 1000; // Convert mm to meters
                break;
            case "cm":
                meters = value / 100; // Convert cm to meters
                break;
            case "m":
                meters = value; // Already in meters
                break;
            case "km":
                meters = value * 1000; // Convert km to meters
                break;
            case "in":
                meters = value * 0.0254; // Convert inches to meters
                break;
            case "ft":
                meters = value * 0.3048; // Convert feet to meters
                break;
            case "yd":
                meters = value * 0.9144; // Convert yards to meters
                break;
            case "mi":
                meters = value * 1609.34; // Convert miles to meters
                break;
            default:
                throw new IllegalArgumentException("Invalid fromUnit: " + fromUnit);
        }

        // Now convert from meters to the target unit
        switch (toUnit) {
            case "mm":
                return meters * 1000; // Convert meters to mm
            case "cm":
                return meters * 100; // Convert meters to cm
            case "m":
                return meters; // Already in meters
            case "km":
                return meters / 1000; // Convert meters to km
            case "in":
                return meters / 0.0254; // Convert meters to inches
            case "ft":
                return meters / 0.3048; // Convert meters to feet
            case "yd":
                return meters / 0.9144; // Convert meters to yards
            case "mi":
                return meters / 1609.34; // Convert meters to miles
            default:
                throw new IllegalArgumentException("Invalid toUnit: " + toUnit);
        }
    }
}
