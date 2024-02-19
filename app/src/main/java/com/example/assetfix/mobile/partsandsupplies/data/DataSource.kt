package com.example.assetfix.mobile.partsandsupplies.data

import com.example.assetfix.mobile.partsandsupplies.model.PartCards

class Datasource {

    private val partList: List<PartCards> = listOf(
        PartCards("24 Samsung OLED Panel", "Mbugua Tech LTD", "13J", "21", "313", "Samsung", "FD4343FD", "Samsung", "100341231232", "n/a", "20,000", "121"),
        PartCards("32 LG LCD Panel", "ElectroTech", "2KX", "45", "200", "LG", "AB12345", "ElectroTech", "123456789", "1 year", "15,000", "90"),
        PartCards("42 Sony LED Backlight", "TechSolutions Inc.", "X9W", "12", "50", "Sony", "CD67890", "TechSolutions Inc.", "S987654321", "n/a", "25,000", "60"),
        PartCards("27 Dell Monitor Stand", "ComputerHardware Co.", "L34", "100", "10", "Dell", "EF98765", "ComputerHardware Co.", "D123456789", "n/a", "5,000", "30"),
        PartCards("19 HP Touchscreen", "TechWorld", "PQR", "75", "25", "HP", "GH54321", "TechWorld", "987654321", "2 years", "12,000", "45"),
        PartCards("23 Asus HDMI Port", "ITSupplies Ltd.", "7M2", "30", "20", "Asus", "IJ34567", "ITSupplies Ltd.", "246813579", "n/a", "8,000", "75"),
        PartCards("15 Acer Power Adapter", "ElectronicEmpire", "W3R", "50", "30", "Acer", "KL45678", "ElectronicEmpire", "369258147", "1 year", "3,000", "25"),
        PartCards("13 Lenovo Webcam", "TechWarehouse", "T5Y", "20", "15", "Lenovo", "MN78901", "TechWarehouse", "951753852", "n/a", "6,500", "40"),
        PartCards("18 Apple MacBook Battery", "GadgetGuru", "B8N", "40", "10", "Apple", "OP12345", "GadgetGuru", "753951456", "1 year", "9,500", "35"),
        PartCards("29 Microsoft Surface Charger", "TechSupplies", "Z1X", "60", "25", "Microsoft", "QR67890", "TechSupplies", "852147963", "n/a", "4,500", "50")

    )

    fun loadPartCards(): List<PartCards> {
        return partList
    }
}
