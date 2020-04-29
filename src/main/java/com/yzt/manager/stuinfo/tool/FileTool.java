package com.yzt.manager.stuinfo.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件处理类
 */
@Component
public class FileTool {

  private enum Excel {
    COLLEGE_AND_DEPART, STUDENT_OR_TEACHER
  }

  /*
   *读学院和系的excel数据
   *
   * excel 1.WorkBook:电子表格的高级表示
   *        2.Sheet:一个WorkBook可能包含多个Sheets
   *        3.Row:电子表格中的一行 4.Cell:电子表格中的一列
   * excel:
   *      college1     college2
   *       depart0      depart0
   *       depart1      depart1
   *       depart2
   */
  public List<List<List<String>>> readCollegeAndDepartExcel(MultipartFile multipartFile)
      throws IOException {
    return readExcel(multipartFile, Excel.COLLEGE_AND_DEPART);
  }


  /*
   *读学生和老师的数据
   *
   * excel:
   *     title1  title2 ...
   *      filed1  filed2 ...
   *      filed1  filed2 ...
   */
  public List<List<List<String>>> readStudentOrTeacherExcel(MultipartFile multipartFile)
      throws IOException {
    return readExcel(multipartFile, Excel.STUDENT_OR_TEACHER);
  }


  private List<List<List<String>>> readExcel(MultipartFile multipartFile, Excel type)
      throws IOException {
    Workbook workbook = WorkbookFactory.create(multipartFile.getInputStream());
    //一个workbook包含多个sheet，listlist存放的就是各个sheet的总数据集合
    List<List<List<String>>> listList = new ArrayList<>();
    try {
      for (Sheet sheet : workbook) {
        //一个sheet可包含多列，lists存放的就是各个列的数据集合
        List<List<String>> lists = new ArrayList<>();
        switch (type) {
          case COLLEGE_AND_DEPART:
            readSingleUniqueCellInCollageAndDepartExcel(sheet, lists);
            break;
          case STUDENT_OR_TEACHER:
            readSingleUniqueCellInStudentOrTeacherExcel(sheet, lists);
            break;
        }
        listList.add(lists);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return listList;
  }

  private void readSingleUniqueCellInStudentOrTeacherExcel(Sheet sheet, List<List<String>> lists) {
    //该类将cell value转换成string
    DataFormatter dataFormatter = new DataFormatter();
    boolean firstRow = true;
    for (Row cells : sheet) {
      if (firstRow) {
        firstRow = false;
        continue;
      }
      List<String> list = new ArrayList<>();
      for (Cell cell : cells) {
        String cellValue = dataFormatter.formatCellValue(cell).trim();
        //一行一行的将数据插入集合中去
        if (!cellValue.isEmpty()) {
          list.add(cellValue);
        } else {
          list.add(null);
        }
      }
      lists.add(list);
    }

  }

  private void readSingleUniqueCellInCollageAndDepartExcel(Sheet sheet, List<List<String>> lists) {
    //该类将cell value转换成string
    DataFormatter dataFormatter = new DataFormatter();
    int rowNum = 0;
    int columnNum = 0;
    for (Row row : sheet) {
      rowNum++;
      for (Cell cell : row) {
        String cellValue = dataFormatter.formatCellValue(cell).trim();
        //第一行就是学院，每一列创建一个集合
        if (rowNum == 1) {
          List<String> cells = new ArrayList<>();
          lists.add(cells);
        }
        if (!cellValue.isEmpty() && !lists.get(columnNum).contains(cellValue)) {
          lists.get(columnNum).add(cellValue);
        }
        columnNum++;
      }
      columnNum = 0;
    }
  }

}
