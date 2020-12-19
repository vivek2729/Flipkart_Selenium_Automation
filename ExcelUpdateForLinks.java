package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class ExcelUpdateForLinks 
{
	public static void ExcelUpdate(int i, int j,String str) throws IOException {

        try {

            FileInputStream file = new FileInputStream(new File("D:\\Selenium\\WebDriverTestingTestNG1\\src\\test\\resources\\Excel\\Input_OutPut_File.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(1);
            Cell cell = null;

            
            
            XSSFRow sheetrow = sheet.getRow(i);
            if(sheetrow == null){
                sheetrow = sheet.createRow(i);
            }
            //Update the value of cell
            cell = sheetrow.getCell(j);
            if(cell == null){
                cell = sheetrow.createCell(j);
            }
            cell.setCellValue(str);
            
            
           
            file.close();

            FileOutputStream outFile =new FileOutputStream(new File("D:\\Selenium\\WebDriverTestingTestNG1\\src\\test\\resources\\Excel\\Input_OutPut_File.xlsx"));
            workbook.write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
