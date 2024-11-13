package Report.ReportWriters.Excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * A utility class for managing and applying styles to Excel cells using Apache POI.
 * This class provides methods to highlight text, set cell text color, and apply background colors to cells.
 */
public class ExcelStyleManager {

    /**
     * Highlights the text in a specified cell by making it bold.
     *
     * @param workbook The Excel workbook where the style is applied.
     * @param row      The row containing the target cell.
     * @param colIndex The column index of the target cell.
     * @param text     The text to be set in the cell.
     */
    public static void highlightText(Workbook workbook, Row row, int colIndex, String text) {

        Cell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex);
        }

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        cell.setCellValue(text);

        cell.setCellStyle(style);
    }

    /**
     * Writes text in a specified cell with a given font color.
     *
     * @param workbook The Excel workbook where the style is applied.
     * @param row      The row containing the target cell.
     * @param colIndex The column index of the target cell.
     * @param text     The text to be written in the cell.
     * @param color    The color to apply to the text.
     */
    public static void writeTextWithColor(Workbook workbook, Row row, int colIndex, String text, IndexedColors color) {

        Cell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex);
        }

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(color.getIndex());
        style.setFont(font);

        cell.setCellValue(text);
        cell.setCellStyle(style);
    }

    /**
     * Sets a background color for a specified cell.
     *
     * @param workbook The Excel workbook where the style is applied.
     * @param row      The row containing the target cell.
     * @param colIndex The column index of the target cell.
     * @param color    The background color to apply to the cell.
     */
    public static void setBackgroundColor(Workbook workbook, Row row, int colIndex, IndexedColors color) {

        Cell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex);
        }

        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(color.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
    }
}

