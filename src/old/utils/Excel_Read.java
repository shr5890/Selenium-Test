package old.utils;
//package com.wms.manhattan.utils;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.List;
//import org.junit.Assert;
//import org.eobjects.metamodel.DataContextFactory;
//import org.eobjects.metamodel.UpdateableDataContext;
//import org.eobjects.metamodel.data.DataSet;
//import org.eobjects.metamodel.data.Row;
//import org.eobjects.metamodel.excel.ExcelConfiguration;
//import org.eobjects.metamodel.schema.Table;
//
//public class Excel_Read extends com.wms.manhattan.utils.BasePage {
//	
//	public HashMap<String,String> readExcel(String fileName, String sheetName, String colName1, String colValue1)
//	{
//		DataSet excelDataSet = null;
//		String colNameStr1 = null;
//		HashMap<String,String> colFieldMap = new HashMap<String,String>();
//		String filePath = getDataTablePath(fileName);		
//		try
//		{
//			ExcelConfiguration excelConf = new ExcelConfiguration();
//			UpdateableDataContext excelContext = null;
//			excelContext = DataContextFactory.createExcelDataContext(
//					new File(filePath),excelConf);
//			Table excelTable = excelContext.getDefaultSchema().getTableByName(sheetName);
//			String[] columnNames = excelTable.getColumnNames();
//			
//			if(checkColName(columnNames,colName1))
//			{
//				colNameStr1 =  sheetName+"."+colName1;
//			}
//
//			if(colNameStr1!=null && colValue1!=null)
//			{
//				excelDataSet = excelContext.query().from(excelTable).selectAll().where(colNameStr1).eq(colValue1).execute();
//
//			}
//			if(excelDataSet!=null)
//			{
//				List<Row> rows = excelDataSet.toRows();
//
//				if(rows.size()>0)
//				{
//					for(Row row : rows)
//					{
//						Object[] values = row.getValues();
//						if(values.length == columnNames.length)
//						{
//							for(int intInd=0;intInd<values.length;intInd++)
//							{
//								if(values[intInd]!=null)
//									colFieldMap.put(columnNames[intInd],values[intInd].toString());
//								else if(values[intInd]==null)
//									colFieldMap.put(columnNames[intInd],"");
//							}
//						}
//					}
//				}
//			}
//		}
//		catch(Exception e)
//		{
//			getLogger().info("................ Error in reading the Excel.......... "+e.getMessage());
//		}
//		return colFieldMap;
//	}
//	
//	private boolean checkColName(String[] columnNames,String colName)
//	{
//		boolean flag = false;
//		for(int intInd=0;intInd<columnNames.length;intInd++)
//		{
//			if(colName!=null && colName.equals(columnNames[intInd]))
//			{
//				flag = true;
//			}
//		}
//
//		return flag;
//	}
//	
//	public static String getDataTablePath(String strExcelName) {
//		try {
//			String strCurrDir = System.getProperty("user.dir");
//			String strDirectory = strCurrDir + "//testdata//" + strExcelName + ".xls";
//			getLogger().info("Datasheet path: " + strDirectory);
//			return strDirectory;
//		}
//		catch (Exception e) {
//			getLogger().info(e);
//			Assert.fail("Error in finding datasheet");
//			return null;
//		}
//		
//	}
//}