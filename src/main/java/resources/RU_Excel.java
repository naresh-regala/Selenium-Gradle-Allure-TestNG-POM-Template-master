package resources;

import java.util.ArrayList;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class RU_Excel {

	public static Object[][] readExcel() throws Exception {
		Fillo fillo = new Fillo();
		Connection connection = fillo
				.getConnection(System.getProperty("user.dir") + "/qa.xlsx");
		String strQuery = "Select * from Sheet1";
		Recordset recordset = connection.executeQuery(strQuery);

		String[][] arrayExcelData = null;
		int totalNoOfCols = recordset.getFieldNames().size();
		int totalNoOfRows = recordset.getCount();

		arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols];
		int i = 1;

		while (recordset.next()) {
			for (int j = 0; j < totalNoOfCols; j++) {
				ArrayList<String> fieldNames = recordset.getFieldNames();
				String fieldName = fieldNames.get(j);
				arrayExcelData[i - 1][j] = recordset.getField(fieldName);
			}
			i += 1;
			if (i == totalNoOfRows) {
				break;
			}
		}

		recordset.close();
		connection.close();
		return arrayExcelData;
	}
}
