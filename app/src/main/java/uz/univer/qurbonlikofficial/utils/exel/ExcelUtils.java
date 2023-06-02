package uz.univer.qurbonlikofficial.utils.exel;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uz.univer.qurbonlikofficial.data.entity.SheepByKgDataEntity;
import uz.univer.qurbonlikofficial.data.model.SheepParametrs;

public class ExcelUtils {
    public static final String TAG = "ExcelUtil";
    private static Cell cell;
    private static Sheet sheet;
    private static Workbook workbook;
    private static CellStyle headerCellStyle;

    private static List<String> importedExcelData;


    public static boolean exportDataIntoWorkbook(Context context, String fileName, ArrayList<SheepByKgDataEntity> listSheepDataEntity) {
        boolean isWorkbookWrittenIntoStorage;

        // Check if available and not read only
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Log.e(TAG, "Storage not available or read only");
            return false;
        }

        // Creating a New HSSF Workbook (.xls format)
        workbook = new HSSFWorkbook();

        setHeaderCellStyle();

        // Creating a New Sheet and Setting width for each column
        sheet = workbook.createSheet(Constants.EXCEL_SHEET_NAME);
        sheet.setColumnWidth(0, (15 * 400));
        sheet.setColumnWidth(1, (15 * 400));

        setHeaderRow();
        fillDataIntoExcel(listSheepDataEntity);
        isWorkbookWrittenIntoStorage = storeExcelInStorage(context, fileName);

        return isWorkbookWrittenIntoStorage;
    }

    private static boolean isExternalStorageReadOnly() {
        String externalStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(externalStorageState);
    }

    private static boolean isExternalStorageAvailable() {
        String externalStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(externalStorageState);
    }

    private static void setHeaderCellStyle() {
        headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
        headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
    }


    private static void setHeaderRow() {
        Row headerRow = sheet.createRow(0);
        SheepParametrs parametrs = new SheepParametrs();
        for (int i = 0; i < parametrs.getParemetrsSheepByKg().size(); i++) {
            cell = headerRow.createCell(i);
            cell.setCellValue(parametrs.getParemetrsSheepByKg().get(i));
            cell.setCellStyle(headerCellStyle);
        }
    }

    private static void fillDataIntoExcel(ArrayList<SheepByKgDataEntity> listSheepDataEntity) {
        for (int i = 0; i < listSheepDataEntity.size(); i++) {
            // Create a New Row for every new entry in list
            // Create a New Row for every new entry in list

            Row rowData = sheet.createRow(i + 1);
            cell = rowData.createCell(0);
//                cell = rowData.createCell(1);
            cell.setCellValue(listSheepDataEntity.get(i).getSheepNumber());
            cell = rowData.createCell(1);
            cell.setCellValue(listSheepDataEntity.get(i).getName() + " " + listSheepDataEntity.get(i).getSurname());
            cell = rowData.createCell(2);
            cell.setCellValue(listSheepDataEntity.get(i).getPhoneNumber());
            cell = rowData.createCell(3);
            cell.setCellValue(listSheepDataEntity.get(i).getWeight());
            cell = rowData.createCell(4);
            cell.setCellValue(listSheepDataEntity.get(i).getPaidAmount());
            cell = rowData.createCell(5);
            cell.setCellValue(listSheepDataEntity.get(i).getRemainingAmount());
        }
    }

    private static boolean storeExcelInStorage(Context context, String fileName) {
        boolean isSuccess;
        File file = new File(context.getExternalFilesDir(null), fileName);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            Log.e(TAG, "Writing file" + file);
            isSuccess = true;
        } catch (IOException e) {
            Log.e(TAG, "Error writing Exception: ", e);
            isSuccess = false;
        } catch (Exception e) {
            Log.e(TAG, "Failed to save file due to Exception: ", e);
            isSuccess = false;
        } finally {
            try {
                if (null != fileOutputStream) {
                    fileOutputStream.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return isSuccess;
    }


}