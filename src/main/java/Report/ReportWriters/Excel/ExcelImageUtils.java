package Report.ReportWriters.Excel;

import Report.ReportWriters.ImageUtils.Image;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * A utility class for handling image insertion into Excel sheets using Apache POI.
 */
public class ExcelImageUtils {


    /**
     * Adds an image to a sheet at a specified row and column, adjusting the height of the image based on the number of columns.
     *
     * @param sheet    The sheet where the image will be added.
     * @param workbook The workbook containing the sheet.
     * @param image    The image object containing the image data.
     * @param numCols  The number of columns the image should span.
     * @param row1     The starting row for the image.
     * @return The number of rows the image occupies.
     */
    public static int addImageToSheet(Sheet sheet, Workbook workbook, Image image, int numCols, int row1) {
        int numRow = 0;
        try (FileInputStream fis = new FileInputStream(image.getPath())) {
            byte[] bytes = IOUtils.toByteArray(fis);
            int pictureIndex = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);

            Drawing<?> drawing = sheet.createDrawingPatriarch();
            CreationHelper helper = workbook.getCreationHelper();
            ClientAnchor anchor = helper.createClientAnchor();

            anchor.setCol1(0);
            anchor.setCol2(numCols);
            anchor.setRow1(row1);
            numRow = image.getRatioByConcreteDenominator(numCols) * 3;
            anchor.setRow2(row1 + numRow);

            Picture picture = drawing.createPicture(anchor, pictureIndex);
            picture.resize(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numRow;
    }
}
