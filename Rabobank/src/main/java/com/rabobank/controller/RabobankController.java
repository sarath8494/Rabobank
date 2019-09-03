package com.rabobank.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.bean.Record;
import com.rabobank.bean.ErrorResponse;
import com.rabobank.bean.IssuesBean;
import com.rabobank.constant.RabobankConstants;
import com.rabobank.service.RabobankService;

/**
 * @author Sarath
 * 
 * Statement Controller
 * 
 */
@RestController
@RequestMapping("/rabobank")
public class RabobankController {
	@Autowired
	private RabobankService rabobankService;

	/**
	 * 
	 * file upload for statement processor
	 * 
	 */
	@RequestMapping(value = "/statement", method = RequestMethod.POST)
	public  ErrorResponse fileUpload(@RequestParam("file") MultipartFile multipart) throws Exception {
		ErrorResponse response=new ErrorResponse();
		if(!multipart.isEmpty()) {
		File file = new File(multipart.getOriginalFilename());
		file.createNewFile();
		    FileOutputStream fos = new FileOutputStream(file);
		    fos.write(multipart.getBytes());
		    fos.close();
		List<Record> errorRecList = new ArrayList<Record>();
		if (multipart.getContentType().equalsIgnoreCase(RabobankConstants.CONTENT_TYPE_CSV)|| multipart.getContentType().equalsIgnoreCase(RabobankConstants.CONTENT_TYPE_EXCEL) ||multipart.getContentType().equalsIgnoreCase(RabobankConstants.CONTENT_TYPE_EXCEL_APP)) {	
			List<Record> recordList = rabobankService.readRecordsFromCSV(file);
			errorRecList = recordList.stream().collect(Collectors.groupingBy(Record::getReferenceNum)).entrySet()
					.stream().filter(e -> e.getValue().size() > 1).flatMap(e -> e.getValue().stream())
					.collect(Collectors.toList());
			errorRecList.addAll(rabobankService.validateEndBalance(recordList));
			System.out.println("size "+errorRecList.size());
			response.setErrorCode(RabobankConstants.HTTP_CODE_SUCCESS);
			response.setMessage(RabobankConstants.SUCESS_MSG);
			response.setRecordsList(errorRecList);
		} else if (multipart.getContentType().equalsIgnoreCase(RabobankConstants.CONTENT_TYPE_XML)||multipart.getContentType().equalsIgnoreCase(RabobankConstants.CONTENT_TYPE_XML_TEXT)) {
			List<Record> recordList = rabobankService.readRecordsFromXML(file);
			errorRecList = recordList.stream().collect(Collectors.groupingBy(Record::getReferenceNum)).entrySet()
					.stream().filter(e -> e.getValue().size() > 1).flatMap(e -> e.getValue().stream())
					.collect(Collectors.toList());
			errorRecList.addAll(rabobankService.validateEndBalance(recordList));
			System.out.println(errorRecList.size());
			response.setErrorCode(RabobankConstants.HTTP_CODE_SUCCESS);
			response.setMessage(RabobankConstants.SUCESS_MSG);
			response.setRecordsList(errorRecList);
		}else {
			response.setErrorCode(RabobankConstants.HTTP_CODE_ERROR);
			response.setMessage(RabobankConstants.UNSUPORTED_FILE_FORMAT);
		}
		}
		else {
			response.setErrorCode(RabobankConstants.HTTP_CODE_ERROR);
			response.setMessage(RabobankConstants.FILE_NOT_NULL);
		}
		
		return response;
	}
	
	
	/**
	 * Exception Handling
	 * 
	 */
	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorResponse handleException(HttpServletRequest request, Exception ex) {
		ErrorResponse response=new ErrorResponse();
		response.setErrorCode(RabobankConstants.HTTP_CODE_ERROR);
		response.setMessage(RabobankConstants.SERVER_ERROR);
		return response;
	}
	@RequestMapping(value = "/issueCount", method = RequestMethod.POST)
	public  ErrorResponse fileUploadUI(@RequestParam("file") MultipartFile multipart) throws Exception {
		ErrorResponse response=new ErrorResponse();
		if(!multipart.isEmpty()) {
		File file = new File(multipart.getOriginalFilename());
		file.createNewFile();
		    FileOutputStream fos = new FileOutputStream(file);
		    fos.write(multipart.getBytes());
		    fos.close();
		if (multipart.getContentType().equalsIgnoreCase(RabobankConstants.CONTENT_TYPE_CSV)|| multipart.getContentType().equalsIgnoreCase(RabobankConstants.CONTENT_TYPE_EXCEL) ||multipart.getContentType().equalsIgnoreCase(RabobankConstants.CONTENT_TYPE_EXCEL_APP)) {	
			List<IssuesBean> issuesList = rabobankService.readIssueCountFromCSV(file);
			
			System.out.println("size "+issuesList.size());
			response.setErrorCode(RabobankConstants.HTTP_CODE_SUCCESS);
			response.setMessage(RabobankConstants.SUCESS_MSG);
			response.setIssuesList(issuesList);
		}else {
			response.setErrorCode(RabobankConstants.HTTP_CODE_ERROR);
			response.setMessage(RabobankConstants.UNSUPORTED_FILE_FORMAT);
		}
		}
		else {
			response.setErrorCode(RabobankConstants.HTTP_CODE_ERROR);
			response.setMessage(RabobankConstants.FILE_NOT_NULL);
		}
		
		return response;
	}
}
