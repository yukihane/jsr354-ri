/**
 * Copyright (c) 2012, 2015, Credit Suisse (Anatole Tresch), Werner Keil and others by the @author tag.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.javamoney.moneta.internal.convert;

import static org.testng.Assert.assertEquals;

import java.time.Month;
import java.time.YearMonth;

import org.testng.Assert;
import org.testng.annotations.Test;

public class IMFHistoricalTypeTest {

	@Test
	public void shouldReturnSDRCurrencyType() {
		String result = IMFHistoricalType.SDR_Currency.getType();
		assertEquals(result, "SDRCV");
	}

	@Test
	public void shouldReturnCurrencySDR() {
		String result = IMFHistoricalType.Currency_SDR.getType();
		assertEquals(result, "CVSDR");
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void shouldReturnErrorWhenYearMonthIsNull() {
		IMFHistoricalType.Currency_SDR.getUrl(null);
	}

	@Test
	public void shouldReturnUrlSDRCurrency() {
		YearMonth yearMonth = YearMonth.of(2015, Month.APRIL);
		String url = IMFHistoricalType.Currency_SDR.getUrl(yearMonth);
		Assert.assertNotNull(url);
		Assert.assertEquals(url, "http://www.imf.org/external/np/fin/data/rms_mth.aspx?SelectDate=2015-04&reportType=CVSDR&tsvflag=Y");
	}

	@Test
	public void shouldReturnUrlCurrencySDR() {
		YearMonth yearMonth = YearMonth.of(2015, Month.APRIL);
		String url = IMFHistoricalType.SDR_Currency.getUrl(yearMonth);
		Assert.assertNotNull(url);
		Assert.assertEquals(url, "http://www.imf.org/external/np/fin/data/rms_mth.aspx?SelectDate=2015-04&reportType=SDRCV&tsvflag=Y");
	}
}
