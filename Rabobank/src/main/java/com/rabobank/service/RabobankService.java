package com.rabobank.service;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.rabobank.bean.IssuesBean;
import com.rabobank.bean.Record;
import com.rabobank.bean.RecordXML;

/**
 * @author Sarath
 *
 */
@Service
public class RabobankService {
	/**
	 * 
	 * read records From CSV File and return list of records
	 * 
	 */
	public List<Record> readRecordsFromCSV(File csvFile) throws Exception {
		Map<String, String> mapping = new HashMap<String, String>();
		mapping.put("Reference", "referenceNum");
		mapping.put("AccountNumber", "accountNum");
		mapping.put("Description", "description");
		mapping.put("Start Balance", "startBalance");
		mapping.put("Mutation", "mutation");
		mapping.put("End Balance", "endBalance");

		HeaderColumnNameTranslateMappingStrategy<Record> strategy = new HeaderColumnNameTranslateMappingStrategy<Record>();
		strategy.setType(Record.class);
		strategy.setColumnMapping(mapping);
		CSVReader csvReader = new CSVReader(new FileReader(csvFile));
		CsvToBean<Record> csvToBean = new CsvToBean<Record>();

		List<Record> recordsList = csvToBean.parse(strategy, csvReader);
		return recordsList;
	}
	/**
	 * 
	 * validate End Balance Records and return list of error records
	 * 
	 */
	public List<Record> validateEndBalance(List<Record> recordList) {
		List<Record> errorRecList = new ArrayList<Record>();
		for (Record record : recordList) {
			if (Math.round(record.getStartBalance() + record.getMutation() - record.getEndBalance()) != 0) {
				errorRecList.add(record);
			}
		}
		return errorRecList;

	}
	/**
	 * 
	 *read records From XML File and return list of records
	 * 
	 */
	public List<Record> readRecordsFromXML(File csvFile) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(RecordXML.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		RecordXML recList = (RecordXML) jaxbUnmarshaller.unmarshal(csvFile);

		return recList.getRecord();

	}
	/**
	 * 
	 * read records From CSV File and return list of records
	 * 
	 */
	public List<IssuesBean> readIssueCountFromCSV(File csvFile) throws Exception {
		Map<String, String> mapping = new HashMap<String, String>();
		mapping.put("First name", "firstName");
		mapping.put("Sur name", "surName");
		mapping.put("Issue count", "issueCount");
		mapping.put("Date of birth", "dob");
	
		HeaderColumnNameTranslateMappingStrategy<IssuesBean> strategy = new HeaderColumnNameTranslateMappingStrategy<IssuesBean>();
		strategy.setType(IssuesBean.class);
		strategy.setColumnMapping(mapping);
		CSVReader csvReader = new CSVReader(new FileReader(csvFile));
		CsvToBean<IssuesBean> csvToBean = new CsvToBean<IssuesBean>();

		List<IssuesBean> issueList = csvToBean.parse(strategy, csvReader);
		return issueList;
	}
}
