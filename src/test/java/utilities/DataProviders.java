package utilities;

// used for handling input/output exceptions
import java.io.IOException;

// TestNG annotation for DataProvider
import org.testng.annotations.DataProvider;

public class DataProviders {

    // =========================================================
    // DataProvider method
    // This method sends login data to test case
    // =========================================================
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        // =========================================================
        // Excel file path
        // System.getProperty("user.dir") gives current project path
        // =========================================================
        String path = System.getProperty("user.dir")
                + "/testData/opencart_LoginData.xlsx";

        // create object of ExcelUtility class
        // used to perform Excel operations
        ExcelUtility xl = new ExcelUtility(path);

        // Excel sheet name
        String sheet = "Sheet 1";

        // =========================================================
        // Get total used rows from Excel
        // Example:
        // Header + 4 rows = 5 rows
        // =========================================================
        int totalRows = xl.getRowCount(sheet);

        // total columns in Excel
        // 0 = email
        // 1 = password
        // 2 = expected result
        int totalCols = 3;


        // =========================================================
        // Count only NON-BLANK rows
        // This avoids running blank Excel rows as test data
        // =========================================================

        // counter variable
        // stores how many valid rows exist
        int validRowCount = 0;

        // loop through Excel rows
        // start from 1 because 0 = header row
        for (int i = 1; i < totalRows; i++) {

            // read email column data
            // column 0 = email column
            String email = xl.getCellData(sheet, i, 0);

            // =====================================================
            // check if email is NOT empty
            // email != null
            // means cell exists
            //
            // !email.trim().isEmpty()
            // means remove spaces and check not empty
            // =====================================================
            if (email != null && !email.trim().isEmpty()) {

                // increase valid row counter
                validRowCount++;
            }
        }


        // =========================================================
        // Create 2D array only for valid rows
        // Example:
        // validRowCount = 4
        // array size becomes [4][3]
        // =========================================================
        String[][] data = new String[validRowCount][totalCols];


        // =========================================================
        // index variable used for storing data in array
        // =========================================================
        int index = 0;


        // =========================================================
        // Read Excel data again
        // Store only NON-BLANK rows into array
        // =========================================================
        for (int i = 1; i < totalRows; i++) {

            // read email column again
            String email = xl.getCellData(sheet, i, 0);

            // skip blank rows
            if (email != null && !email.trim().isEmpty()) {

                // =================================================
                // Store email into array
                // index = row number in array
                // [0] = email column
                // trim() removes extra spaces
                // =================================================
                data[index][0] =
                        xl.getCellData(sheet, i, 0).trim();

                // store password
                data[index][1] =
                        xl.getCellData(sheet, i, 1).trim();

                // store expected result
                // Valid / Invalid
                data[index][2] =
                        xl.getCellData(sheet, i, 2).trim();

                // move to next array row
                index++;
            }
        }

        // return final data to test case
        return data;
    }
}