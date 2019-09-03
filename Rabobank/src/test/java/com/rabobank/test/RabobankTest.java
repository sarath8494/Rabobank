package com.rabobank.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.rabobank.bean.Record;
import com.rabobank.service.RabobankService;

/**
 * @author Sarath
 *
 */
@SpringBootTest
public class RabobankTest {
	/**
	 * 
	 * validate test case for End balance with wrong value
	 * 
	 */
	@Test
	public void validateEndBalanceNegative() {
		List<Record> inputList = new ArrayList<>();
		Record r1 = new Record(130498, "NL69ABNA0433647324", 26.9, -18.78, "Tickets for Peter Theuß", 8.12);
		Record r2 = new Record(167875, "NL93ABNA0585619023", 5429, 939, "Tickets from Erik de Vries", 6368);
		inputList.add(r1);
		inputList.add(r2);
		RabobankService rabobankService = new RabobankService();
		List<Record> endBalanceErrorRecords = rabobankService.validateEndBalance(inputList);
		assertEquals(0, endBalanceErrorRecords.size());

	}

	/**
	 * 
	 * validate test case for End balance with correct value
	 * 
	 */
	@Test
	public void validateEndBalancePositive() {
		List<Record> inputList = new ArrayList<>();
		Record r1 = new Record(130498, "NL69ABNA0433647324", 26.9, -18.78, "Tickets for Peter Theuß", 82.12);
		Record r2 = new Record(167875, "NL93ABNA0585619023", 5429, 939, "Tickets from Erik de Vries", 4368);
		inputList.add(r1);
		inputList.add(r2);
		RabobankService rabobankService = new RabobankService();
		List<Record> endBalanceErrorRecords = rabobankService.validateEndBalance(inputList);
		assertEquals(inputList.size(), endBalanceErrorRecords.size());
	}

}
