//package Report.ImageReport;
//
//import Report.ReportWriters.ExcelWriter;
//import Readers.Image;
//import org.apache.commons.compress.utils.IOUtils;
//import org.apache.poi.ss.usermodel.ClientAnchor;
//import org.apache.poi.ss.usermodel.Drawing;
//import org.apache.poi.ss.usermodel.Workbook;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//public class ImageToXlsx implements ImageReportManager{
//    ExcelWriter excelWriter;
//
//    public ImageToXlsx() {
//        excelWriter=ExcelWriter.getInstance();
//    }
//
//    @Override
//    public void addImageToReport(Image image) {
//
//        try (InputStream is = new FileInputStream(image.getPath())) {
//            byte[] bytes = IOUtils.toByteArray(is);
//            int pictureIdx = excelWriter.getWorkbook().addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
//
//            Drawing<?> drawing = excelWriter.getSheet().createDrawingPatriarch();
//            ClientAnchor anchor = excelWriter.getWorkbook().getCreationHelper().createClientAnchor();
//
//            anchor.setCol1(0);
//            anchor.setRow1(excelWriter.getPosition() + 1);
//            int rows = image.getRatioByConcreteDenominator(4) * 3;
//            anchor.setRow2(excelWriter.getPosition() + 1 + rows);
//
//            excelWriter.setImagesSumRows(1 + rows);
//            drawing.createPicture(anchor, pictureIdx).resize(1);
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//
//
//
//
//    }
//}
