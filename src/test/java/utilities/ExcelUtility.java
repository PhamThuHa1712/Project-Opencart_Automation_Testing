package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
    private String path;

    public ExcelUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        // Dùng try-with-resources: Tự động đóng luồng (close) dù chạy thành công hay văng lỗi
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
             
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
            		throw new RuntimeException("LỖI DATA: Không tìm thấy sheet có tên là [" + sheetName + "] trong file Excel!");
            }
        		return sheet.getLastRowNum();
        }
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
             
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
            		throw new RuntimeException("LỖI DATA: Không tìm thấy sheet có tên là [" + sheetName + "] trong file Excel!");
            }
            XSSFRow row = sheet.getRow(rownum);
            if (row == null) return 0;
            
            return row.getLastCellNum();
        }
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
             
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return "";
            
            XSSFRow row = sheet.getRow(rownum);
            if (row == null) return "";
            
            XSSFCell cell = row.getCell(colnum);
            
            // DataFormatter, cell null nó tự trả về chuỗi rỗng, không cần try-catch
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell); 
        }
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(path);

        // Tạo file mới nếu chưa có
        if (!xlfile.exists()) {
            try (XSSFWorkbook workbook = new XSSFWorkbook();
                 FileOutputStream fo = new FileOutputStream(path)) {
                workbook.write(fo);
            }
        }

        // Mở file ra để thao tác
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) sheet = workbook.createSheet(sheetName);

            XSSFRow row = sheet.getRow(rownum);
            if (row == null) row = sheet.createRow(rownum);

            XSSFCell cell = row.createCell(colnum);
            cell.setCellValue(data);

            // Ghi lại xuống file
            try (FileOutputStream fo = new FileOutputStream(path)) {
                workbook.write(fo);
            }
        }
    }

    public void fillCellColor(String sheetName, int rownum, int column, IndexedColors color) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
             
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return;
            XSSFRow row = sheet.getRow(rownum);
            if (row == null) return;
            XSSFCell cell = row.getCell(column);
            if (cell == null) return;

            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(color.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);

            try (FileOutputStream fo = new FileOutputStream(path)) {
                workbook.write(fo);
            }
        }
    }
}