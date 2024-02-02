package com.example.assetfix.mobile.assets.data

import com.example.assetfix.mobile.assets.model.AssetCards

class Datasource {

    private val assetCardsList: List<AssetCards> = listOf(
        AssetCards("Laptop Dell XPS 13", "Screen flickering issue", "Dell Inc", "Tech Solutions Ltd", "SoftEdge IT Services", "IT", "Laptops", "Thinkbooks", "XPS 13", "Dell", "ABC123XYZ456", "05/15/2020", "120,000", "2021009876", "Slow performance", "1032010321", "Screen hasn't been properly color corrected", "No files were uploaded"),

        AssetCards("Desktop HP Pavilion", "Random system crashes", "HP Inc", "TechMaster Solutions", "DigitalWorks IT Services", "IT", "Desktops", "Pavilion", "Not Applicable", "HP", "DEF456GHI789", "08/20/2019", "80,000", "3021098765", "Blue screen of death (BSOD)", "2045030111", "Hardware upgrade needed", "No files attached"),

        AssetCards("Smartphone Samsung Galaxy S21", "Battery draining quickly", "Samsung Electronics", "MobilePros", "TechHub Mobile Solutions", "Mobile Devices", "Smartphones", "Galaxy", "S21", "Samsung", "JKL789MNO012", "03/05/2021", "100,000", "4078901234", "Overheating issue", "1012030456", "Software update required", "No files uploaded"),

        AssetCards("Tablet Apple iPad Pro", "Touchscreen not responsive", "Apple Inc", "PadMasters", "TechSavvy Tablets", "Mobile Devices", "Tablets", "iPad", "Pro", "Apple", "PQR345STU678", "11/10/2020", "85,000", "5067890123", "Cracked screen", "2045030111", "Screen replacement needed", "No files attached"),

        AssetCards("Server Dell PowerEdge R640", "Network connectivity issues", "Dell Inc", "ServerSolutions", "DataCenter Tech Services", "IT", "Servers", "PowerEdge", "R640", "Dell", "XYZ123ABC456", "06/30/2018", "150,000", "3021098765", "Slow data transfer", "1032010321", "Network card replacement required", "No files uploaded"),

        AssetCards("Printer Brother HL-L2380DW", "Paper jamming frequently", "Brother Industries", "PrintPro Solutions", "SwiftPrint Services", "Printing", "Printers", "HL-L2380DW", "Not Applicable", "Brother", "MNO789PQR012", "02/15/2019", "45,000", "3056701234", "Print quality issues", "1012030456", "Toner replacement needed", "No files attached"),

        AssetCards("Camera Nikon D850", "Auto-focus not working", "Nikon Corporation", "PhotoTech Solutions", "VisualArt Photography", "Photography", "Cameras", "D850", "Not Applicable", "Nikon", "ABC456DEF789", "09/25/2020", "120,000", "6078901234", "Blurry images", "2045030111", "Lens replacement needed", "No files uploaded"),

        AssetCards("External SSD Samsung T5", "Not detected by the system", "Samsung Electronics", "DataVault Storage", "TechSafe Backup Solutions", "Storage", "External SSDs", "T5", "Not Applicable", "Samsung", "GHI789JKL012", "04/12/2019", "2 TB", "2087654321", "Data loss", "1012030456", "Drive replacement needed", "No files attached"),

        AssetCards("Projector Epson Home Cinema 5050UB", "Noisy fan during operation", "Epson Corporation", "VisualTech Displays", "HomeTheater Projections", "Projection", "Projectors", "Home Cinema", "5050UB", "Epson", "PQS234RST567", "01/08/2021", "95,000", "5067890123", "Image flickering", "2045030111", "Lamp replacement needed", "No files uploaded"),

        AssetCards("Monitor ASUS ROG Swift PG279Q", "Dead pixels on the screen", "ASUSTeK Computer Inc", "VisualTech Displays", "Gamer's Haven Tech", "Displays", "Monitors", "ROG Swift", "PG279Q", "ASUS", "STU678VWX901", "07/15/2020", "75,000", "4087654321", "Screen tearing", "1012030456", "Panel replacement needed", "No files attached"),

        AssetCards("Network Switch Cisco Catalyst 2960", "Ports not functioning", "Cisco Systems", "SwitchMaster Networks", "DataWave Networking", "Networking", "Switches", "Catalyst 2960", "Not Applicable", "Cisco", "UVW567XYZ890", "05/05/2017", "40,000", "4056789123", "Power supply failure", "1032010321", "Switch replacement needed", "No files uploaded")


    )

    fun loadAssetCards(): List<AssetCards> {
        return assetCardsList
    }

    // Retrieve a specific WorkOrderCards by work order number
//    fun getWorkOrderCardByNumber(workOrderNumber: String): AssetCards? {
//        return workOrderCardsList.find { it.workOrderNumber == workOrderNumber }
//    }
}
