package com.example.selenium.utils;

import com.example.selenium.pages.InventoryPage.Product;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {
    private static final Logger logger = LoggerFactory.getLogger(ReportGenerator.class);

    public static void generateProductReport(List<Product> products, String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Write header
            String[] header = {"Name", "Description", "Price"};
            writer.writeNext(header);

            // Write product data
            for (Product product : products) {
                String[] row = {
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()
                };
                writer.writeNext(row);
                logger.debug("Wrote product to CSV: {}", product.getName());
            }
            logger.info("Product report generated at: {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to generate CSV report", e);
            throw new RuntimeException("Could not write to CSV file", e);
        }
    }
}