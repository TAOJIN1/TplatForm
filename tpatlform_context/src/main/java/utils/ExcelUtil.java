/*
 * Copyright (C), 2013-2015, RichFit.
 * 系统名称: rfcc-dataplat
 * 文件名称: com.rf.sys.utils --> ExcelUtil.java
 * 功能描述:excel工具类，目前只有解析excel方法
 * 
 * 作者: hushouquan
 * 版本：1.0.0
 * 创建时间：2013-06-18 17:18:41
 */

package utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 该类提供excel解析方法，支持.xls和.xlsx格式。支持读取excel中图片
 * @author hushouquan
 */
public class ExcelUtil {

	/** 构造方法 */
	public ExcelUtil() {
	}

	/**
	 * <ul>
	 * <li>Description:[返回Excel中图片的集合]</li>
	 * <li>Created by [hushouquan] [Dec 5, 2013]</li>
	 * <li>Midified by [] [modified time]</li>
	 * <ul>
	 * 
	 * @param fileName 文件的物理路径
	 * @return 图片集合
	 */
	public static List getExcelPictures(String fileName) {
		List pictures = new ArrayList();
		Workbook workbook = ExcelUtil.getWorkbook(fileName);
		pictures = workbook.getAllPictures();
		return pictures;
	}

	/**
	 * <ul>
	 * <li>Description:[返回excel数据集合 支持.xls和xlsx格式]</li>
	 * <li>Created by [hushouquan] [Dec 5, 2013]</li>
	 * <li>Midified by [] [modified time]</li>
	 * <ul>
	 * 
	 * @param fileName 文件物理路径
	 * @param sheetNo sheet号
	 * @return excel中第sheetNo个sheet的数据集合,sheetNo从0开始
	 */
	public static List<ArrayList<String>> excelOneSheetToObjList(
			String fileName, int sheetNo) {

		List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();
		Workbook workbook = ExcelUtil.getWorkbook(fileName);

		try {
			// 调用本类提供的根据流读取的方法
			dataLst = read(workbook, sheetNo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return dataLst;
	}
	
	/**
	 * <ul>
	 * <li>Description:[返回excel数据集合 支持.xls和xlsx格式]</li>
	 * <li>Created by [hushouquan] [Oct 5, 2015]</li>
	 * <li>Midified by [] [modified time]</li>
	 * <ul>
	 * @param fileName 不带路径的文件名
	 * @param sheetNo sheet编号,sheetNo从0开始
	 * @param stream 输入流
	 * @return excel中第sheetNo个sheet的数据集合,sheetNo从0开始
	 */
	public static List<ArrayList<String>> excelOneSheetToObjList(
			String fileName, int sheetNo, InputStream stream) {

		List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();

		try {
			Workbook workbook = ExcelUtil.getWorkbook(fileName, stream);
			// 调用本类提供的根据流读取的方法
			dataLst = read(workbook, sheetNo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return dataLst;
	}
	
	/**
	 * 返回excel数据集合 支持.xls和xlsx格式
	 * @param file 文件
	 * @param sheetNo sheet编号,sheetNo从0开始
	 * @return excel中第sheetNo个sheet的数据集合,sheetNo从0开始
	 */
	public static List<ArrayList<String>> excelOneSheetToObjList(
			File file, int sheetNo) {

		List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();
		Workbook workbook = ExcelUtil.getWorkbook(file);

		try {
			// 调用本类提供的根据流读取的方法
			dataLst = read(workbook, sheetNo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return dataLst;
	}

	/**
	 * <ul>
	 * <li>Description:[读取excel中数据]</li>
	 * <li>Created by [hushouquan] [Dec 5, 2013]</li>
	 * <li>Midified by [] [modified time]</li>
	 * <ul>
	 * 
	 * @param workbook 
	 * @param sheetNo sheetNo sheet编号,sheetNo从0开始
	 * @return excel中第sheetNo个sheet的数据集合,sheetNo从0开始
	 */
	public static List<ArrayList<String>> read(Workbook workbook, int sheetNo) {
		List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();

		// 总行数
		int totalRows = 0;
		// 总列数
		int totalCells = 0;

		if(workbook == null){//没有excel数据时直接返回空集合
			return dataLst;
		}
//		if (sheetNo > workbook.getActiveSheetIndex()) {
//			return dataLst;
//		}
		// 得到第sheetNo个shell
		Sheet sheet = workbook.getSheetAt(sheetNo);
		totalRows = sheet.getPhysicalNumberOfRows();
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			totalCells = sheet.getRow(0).getLastCellNum();
		}

		// 循环Excel的行
		for (int i = 0; i <= totalRows; i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}

			ArrayList<String> rowLst = new ArrayList<String>();
			if(row.getLastCellNum() > totalCells){
				totalCells = row.getLastCellNum();
			}
			// 循环Excel的列
			for (short k = 0; k < totalCells; k++) {
				Cell cell = row.getCell(k);
				String cellValue = "";
				if (cell == null) {
					rowLst.add(cellValue);
					continue;
				}

				// 处理数字型的,自动去零
				if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
					/** 在excel里,日期也是数字,在此要进行判断 */
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat simpleDateFormate = new SimpleDateFormat(
								"yyyyMMdd hh:mm:ss");
						String dateStr = simpleDateFormate.format(cell
								.getDateCellValue());
						cellValue = dateStr;
					} else {
						cellValue = getRightStr(cell.getNumericCellValue() + "");
					}
				}
				// 处理字符串型
				else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
					cellValue = cell.getStringCellValue();
				} else if (Cell.CELL_TYPE_BOOLEAN == cell.getCellType()) {// 处理布尔型
					cellValue = cell.getBooleanCellValue() + "";
				} else {// 其它的,非以上几种数据类型
					cellValue = cell.toString() + "";
				}

				rowLst.add(cellValue);
			}
			dataLst.add(rowLst);
		}
		return dataLst;
	}

	/**
	 * <ul>
	 * <li>Description:[正确地处理整数后自动加零的情况]</li>
	 * <li>Created by [hushouquan] [Jan 20, 2010]</li>
	 * <li>Midified by [modifier] [modified time]</li>
	 * <ul>
	 * 
	 * @param sNum
	 * @return
	 */
	private static String getRightStr(String sNum) {
		DecimalFormat decimalFormat = new DecimalFormat("#.000000");
		String resultStr = decimalFormat.format(new Double(sNum));
		if (resultStr.matches("^[-+]?\\d+\\.[0]+$")) {
			resultStr = resultStr.substring(0, resultStr.indexOf("."));
		}
		return resultStr;
	}

	/**
	 * <ul>
	 * <li>Description:[获取workbook]</li>
	 * <li>Created by [hushouquan] [Dec 5, 2013]</li>
	 * <li>Midified by [] [modified time]</li>
	 * <ul>
	 * 
	 * @param fileName 文件物理路径
	 * @return workbook for excel
	 */
	public static Workbook getWorkbook(String fileName) {

		// 检查文件名是否为空或者是否是Excel格式的文件
		if (fileName == null || !fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
			return null;
		}
		boolean isExcel2003 = true;
		// 对文件的合法性进行验证
		if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
			isExcel2003 = false;
		}

		// 检查文件是否存在
		File file = new File(fileName);
		if (file == null || !file.exists()) {
			return null;
		}

		try {
			InputStream inputStream = new FileInputStream(fileName);
			// 根据版本选择创建Workbook的方式
			Workbook workbook = isExcel2003 ? new HSSFWorkbook(inputStream)
					: new XSSFWorkbook(inputStream);
			inputStream.close();
			return workbook;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param file 
	 * @return workbook for excel
	 */
	private static Workbook getWorkbook(File file) {
		// 检查文件名是否为空或者是否是Excel格式的文件
		if (file == null || !file.getName().matches("^.+\\.(?i)((xls)|(xlsx))$")) {
			return null;
		}
		
		boolean isExcel2003 = true;
		// 对文件的合法性进行验证
		if (file.getName().matches("^.+\\.(?i)(xlsx)$")) {
			isExcel2003 = false;
		}
		
		try {
			InputStream inputStream = new FileInputStream(file);
			// 根据版本选择创建Workbook的方式
			Workbook workbook = isExcel2003 ? new HSSFWorkbook(inputStream)
					: new XSSFWorkbook(inputStream);
			inputStream.close();
			return workbook;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * <ul>
	 * <li>Description:[获取workbook]</li>
	 * <li>Created by [sunjianhua] [20151028]</li>
	 * <li>Midified by [] [modified time]</li>
	 * <ul>
	 * @param fileName 文件名，不带路径
	 * @param inputStream 输入流
	 * @return workbook for excel
	 */
	public static Workbook getWorkbook(String fileName,InputStream inputStream) throws Exception{
		try {
			boolean isExcel2003 = true;
			// 对文件的合法性进行验证
			if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
				isExcel2003 = false;
			}
			// 根据版本选择创建Workbook的方式
			Workbook workbook = isExcel2003 ? new HSSFWorkbook(inputStream)
					: new XSSFWorkbook(inputStream);
			inputStream.close();
			return workbook;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//return null;
		}
	}
}
