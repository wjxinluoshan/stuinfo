package com.yzt.manager.stuinfo.tool;

import com.yzt.manager.stuinfo.pojo.UploadFileInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件处理类
 */
@Component
public class FileTool {

  private enum Excel {
    COLLEGE_AND_DEPART, STUDENT_OR_TEACHER
  }

  private String PRIZE = "prize", INTRODUCE = "introduce", GRADE = "grade", PRACTICE = "practice",
      TEACHING_PROGRAMMING = "tPlan", ANNUAL_SUMMARY = "yReport";


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

  /**
   * 学生文件上传
   */
  public boolean uploadStuFile(UploadFileInfo uploadFileInfo) throws IOException {
    /**
     * 生成文件跟文件
     */
    File rootFile = getRootStuOrUploadFile(uploadFileInfo.getId(), uploadFileInfo.getIsTeacher(),
        uploadFileInfo.getStuUploadType());

    return uploadStuOrTeacherFile(uploadFileInfo.getMultipartFiles(), rootFile,
        uploadFileInfo.getIsTeacher(),
        uploadFileInfo.getId(), uploadFileInfo.getStuUploadType());
  }

  /**
   * 教师文件上传
   */
  public boolean uploadTeacherFile(UploadFileInfo uploadFileInfo) throws IOException {
    MultipartFile[] multipartFiles = uploadFileInfo.getMultipartFiles();
    /**
     * 生成文件跟文件
     */
    File rootFile = getRootStuOrUploadFile(uploadFileInfo.getId(), uploadFileInfo.getIsTeacher(),
        uploadFileInfo.getTeacherUploadType());
    return uploadStuOrTeacherFile(uploadFileInfo.getMultipartFiles(), rootFile,
        uploadFileInfo.getIsTeacher(),
        uploadFileInfo.getId(), uploadFileInfo.getTeacherUploadType());
  }


  private String getStuFileType(Integer fileType) {
    switch (fileType) {
      case 0:
        return PRIZE;
      case 1:
        return INTRODUCE;
      case 2:
        return GRADE;
      case 3:
        return PRACTICE;
    }
    return "";
  }

  private String getTeacherFileType(Integer fileType) {
    switch (fileType) {
      case 0:
        return PRIZE;
      case 1:
        return INTRODUCE;
      //教学计划
      case 2:
        return TEACHING_PROGRAMMING;
      //年度总接
      case 3:
        return ANNUAL_SUMMARY;
    }
    return "";
  }


  /**
   * 文件url的存储使用对象的序列化和反序列化存储
   *
   * @param multipartFiles 接收的前端文件
   * @param rootFile       存放文件的根目录
   * @param isTeacher      是否存放的是老师的文件
   * @param id             上传文件的用户id
   * @param fileType       上传文件属于哪一类文件
   * @return
   */
  private boolean uploadStuOrTeacherFile(MultipartFile[] multipartFiles, File rootFile,
      Integer isTeacher, Integer id, Integer fileType) throws IOException {
    /**
     * 读取存放url文件,反序列对象
     */
    List<String> urlList = readFileOfUrl(rootFile);

    Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    for (int i = 0; i < multipartFiles.length; i++) {
      //生成文件的url
      String url = null;
      //学生
      if (isTeacher == 1) {
        url = "/stuinfo/student/uploadFile/" + id + "/" + getStuFileType(fileType) + "/"
            + date.getTime() + multipartFiles[i].getOriginalFilename() + "?data=" + simpleDateFormat
            .format(date) + "&long=" + date.getTime() + "&filename=" + multipartFiles[i]
            .getOriginalFilename();
      }
      if (isTeacher == 0) {
        url = "/stuinfo/teacher/uploadFile/" + id + "/" + getTeacherFileType(fileType) + "/"
            + date.getTime() + multipartFiles[i].getOriginalFilename() + "?data=" + simpleDateFormat
            .format(date) + "&long=" + date.getTime() + "&filename=" + multipartFiles[i]
            .getOriginalFilename();
      }

      try (FileOutputStream fileOutputStream = new FileOutputStream(
          new File(rootFile, date.getTime() + multipartFiles[i].getOriginalFilename()))) {
        fileOutputStream.write(multipartFiles[i].getBytes());
        urlList.add(url);
      } catch (IOException e) {
        e.printStackTrace();
        return false;
      }
    }

    /**
     * 将文件url集合序列化存放在文件中
     */
    return inputOutFilesUrl(rootFile, urlList);
  }

  /**
   * url文件的反序列化对象
   *
   * @param rootFile
   * @return
   */
  private List<String> readFileOfUrl(File rootFile) throws IOException {
    List<String> urlList = new ArrayList<>();
    File file = new File(rootFile, "url.obj");
    if (!file.exists()) {
      file.createNewFile();
    }
    try (FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
      urlList = (List<String>) objectInputStream.readObject();
    } catch (Exception e) {
    }
    return urlList;
  }

  /**
   * url对象集合的序列化
   */
  private boolean inputOutFilesUrl(File rootFile, Object object) {
    try (FileOutputStream fileOutputStream = new FileOutputStream(
        new File(rootFile, "/url.obj"));
        ObjectOutputStream objectOutputStream =
            new ObjectOutputStream(fileOutputStream)
    ) {
      objectOutputStream.writeObject(object);
      return true;
    } catch (Exception e) {

    }
    return false;
  }


  private File getRootStuOrUploadFile(Integer id, Integer isTeacher, Integer fileType)
      throws FileNotFoundException {
    File rootFile = null;
    //学生
    if (isTeacher == 1) {
      rootFile = new File(ResourceUtils.getFile("classpath:"),
          "static/student/uploadFile/" + id + "/" + getStuFileType(fileType));
    }
    //老师
    if (isTeacher == 0) {
      rootFile = new File(ResourceUtils.getFile("classpath:"),
          "static/teacher/uploadFile/" + id + "/" + getTeacherFileType(fileType));
    }
    if (!rootFile.exists()) {
      synchronized (this) {
        if (!rootFile.exists()) {
          rootFile.mkdirs();
        }
      }
    }
    return rootFile;
  }

  /**
   * 得到文件的url
   *
   * @return
   */
  public List<String> readFilesUrl(UploadFileInfo uploadFileInfo)
      throws IOException {
    if (uploadFileInfo.getIsTeacher() == 0) {
      return readFileOfUrl(
          getRootStuOrUploadFile(uploadFileInfo.getId(), uploadFileInfo.getIsTeacher(),
              uploadFileInfo.getTeacherUploadType()));
    }
    List<String> list = readFileOfUrl(
        getRootStuOrUploadFile(uploadFileInfo.getId(), uploadFileInfo.getIsTeacher(),
            uploadFileInfo.getStuUploadType()));
    Collections.reverse(list);
    return list;

  }

  /**
   * 针对于删除
   */
  public boolean delFilesUrl(Integer id, Integer isTeacher, Integer fileType, String url)
      throws IOException {
    if (fileType == null) {
      //学生
      if (isTeacher == 1) {
        recursiveDelDir(new File(ResourceUtils.getFile("classpath:"),
            "static/student/uploadFile/" + id));
      } else {
        recursiveDelDir(new File(ResourceUtils.getFile("classpath:"),
            "static/teacher/uploadFile/" + id));
      }
      return false;
    }
    File rootFile = getRootStuOrUploadFile(id, isTeacher, fileType);
    List<String> urlList = readFileOfUrl(rootFile);
    urlList.remove(url);
    //得到url包含的文件名信息
    String fileName = url.split("filename=")[1];
    //得到上传时间文件前缀
    String time = url.split("filename=")[0].split("long=")[1];
    new File(rootFile, time + fileName).delete();
    return inputOutFilesUrl(rootFile, urlList);
  }

  /**
   * 递归删除所有文件
   *
   * @param file
   */
  private void recursiveDelDir(File file) {
    System.out.println("wjx");
    if (file == null) {
      return;
    }
    if (file.isFile()) {
      System.out.println("file");

      file.delete();
      return;
    }
    if (file.isDirectory() && file.listFiles().length == 0) {
      System.out.println("dir");

      file.delete();
      return;
    }
    /**
     * 删除文件
     */
    for (File listFile : file.listFiles()) {
      recursiveDelDir(listFile);
    }
    /**
     * 删除文件夹
     */
    for (File listFile : file.listFiles()) {
      recursiveDelDir(listFile);
    }
    //删除跟文件夹
    file.delete();
  }

}
