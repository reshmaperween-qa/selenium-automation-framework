package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        String path = System.getProperty("user.dir")
                + "/testData/opencart_LoginData.xlsx";

        ExcelUtility xl = new ExcelUtility(path);

        String sheet = "Sheet 1";   // ensure correct name

        int totalRows = xl.getRowCount(sheet);

        // ✅ FIX: ONLY 3 columns (email, password, result)
        int totalCols = 3;

        String[][] data = new String[totalRows - 1][totalCols];

        // skip header
        for (int i = 1; i < totalRows; i++) {

            data[i - 1][0] = xl.getCellData(sheet, i, 0).trim(); // email
            data[i - 1][1] = xl.getCellData(sheet, i, 1).trim(); // password
            data[i - 1][2] = xl.getCellData(sheet, i, 2).trim(); // result
        }

        return data;
    }
}