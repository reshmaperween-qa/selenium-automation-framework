package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*; 
// 👉 Apache POI classes to handle Excel (.xlsx file)

public class ExcelUtility {

    FileInputStream fi;   //  used to read Excel file from system
    XSSFWorkbook wb;      //  represents whole Excel workbook
    XSSFSheet ws;         // represents a single sheet in Excel

    String path;          // store Excel file path

    // =========================================================
    // constructor - set Excel file path
    // =========================================================
    public ExcelUtility(String path) {
        this.path = path;  //  store file path for later use
    }

    // =========================================================
    // load workbook only once (performance improvement)
    // =========================================================
    public void initWorkbook() throws IOException {

        if (wb == null) {  //  if workbook not loaded yet

            fi = new FileInputStream(path); //  open Excel file
            wb = new XSSFWorkbook(fi);      //  load Excel file into memory
        }
    }

    // =========================================================
    // load sheet safely (main fix for your error)
    // =========================================================
    public void loadSheet(String sheetName) throws IOException {

        initWorkbook();  // 👉 first load workbook

        ws = wb.getSheet(sheetName); //  get sheet by name

        //  if sheet not found then stop execution
        if (ws == null) {

            //  print all available sheet names (for debugging)
            System.out.println("Available sheets in Excel:");

            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                System.out.println(wb.getSheetName(i)); //  print each sheet name
            }

            //  throw error with message
            throw new RuntimeException(
                "Sheet not found: " + sheetName +
                " (check spelling, space, case)"
            );
        }
    }

    // =========================================================
    // get total rows in sheet
    // =========================================================
    public int getRowCount(String sheetName) throws IOException {

        loadSheet(sheetName); //  first load sheet

        return ws.getLastRowNum(); //  return last row index
    }

    // =========================================================
    // get total columns in a row
    // =========================================================
    public int getCellCount(String sheetName, int rownum) throws IOException {

        loadSheet(sheetName); //  load sheet first

        return ws.getRow(rownum).getLastCellNum(); //  return column count
    }

    // =========================================================
    // get data from Excel cell
    // =========================================================
    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {

        loadSheet(sheetName); //  load sheet first

        // 👉 get row → get cell → convert to string
        return ws.getRow(rownum).getCell(colnum).toString();
    }
}