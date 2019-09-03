package com.rabobank.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sarath
 *
 */
@XmlRootElement(name = "records")
public class RecordXML {

	private List<Record> record;

	@XmlElement(name = "record")
	public List<Record> getRecord() {
		return record;
	}

	public void setRecord(List<Record> record) {
		this.record = record;
	}

}
