package org.utiles;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
public class excelReader1 {
        public String[][] readExcel(String filePath, String sheetName) throws IOException {
            try (FileInputStream file = new FileInputStream("src/test/resources/saucedemo.xlsx");
                 Workbook workbook = new XSSFWorkbook(file)) {

                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    System.out.println("Sheet not found: " + sheetName);
                    return new String[1][1]; // return empty but not null
                }

                int rowCount = sheet.getPhysicalNumberOfRows();
                int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

                String[][] data = new String[rowCount - 1][colCount];

                for (int i = 1; i < rowCount; i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) continue;
                    for (int j = 0; j < colCount; j++) {
                        Cell cell = row.getCell(j);
                        if (cell != null) {
                            data[i - 1][j] = cell.toString();
                        } else {
                            data[i - 1][j] = "";
                        }
                    }
                }

                return data;
            } catch (IOException e) {
                e.printStackTrace();
                return new String[0][0]; // return empty array on error, not null
            }
        }
    }
