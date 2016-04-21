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
package org.javamoney.moneta.function;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.math.RoundingMode;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryOperator;

import org.javamoney.moneta.Money;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RoudingMonetaryAmountOperatorTest {

	private MonetaryOperator operator;

	@BeforeMethod
	public void setup() {
		operator = new RoudingMonetaryAmountOperator();
	}

	@Test
	public void shouldReturnPositiveValue() {
		CurrencyUnit currency = Monetary.getCurrency("EUR");
		MonetaryAmount money = Money.parse("EUR 2.3523");
		MonetaryAmount result = operator.apply(money);
		assertEquals(result.getCurrency(), currency);
		assertEquals(result.getNumber().doubleValue(), 2.35);

	}


	@Test
	public void shouldReturnNegativeValue() {
		CurrencyUnit currency = Monetary.getCurrency("BHD");
		MonetaryAmount money = Money.parse("BHD -1.34534432");
		MonetaryAmount result = operator.apply(money);
		assertEquals(result.getCurrency(), currency);
		assertEquals(result.getNumber().doubleValue(), -1.345);
	}


	@Test
	public void shouldReturnPositiveValueUsingRoudingType() {
		operator = new RoudingMonetaryAmountOperator(RoundingMode.HALF_EVEN);
		CurrencyUnit currency = Monetary.getCurrency("EUR");
		MonetaryAmount money = Money.parse("EUR 2.3523");
		MonetaryAmount result = operator.apply(money);
		assertEquals(result.getCurrency(), currency);
		assertEquals(result.getNumber().doubleValue(), 2.35);

	}

	@Test
	public void shouldReturnNegativeValueUsingRoudingType() {
		operator = new RoudingMonetaryAmountOperator(RoundingMode.HALF_EVEN);
		CurrencyUnit currency = Monetary.getCurrency("BHD");
		MonetaryAmount money = Money.parse("BHD -1.34534432");
		MonetaryAmount result = operator.apply(money);
		assertEquals(result.getCurrency(), currency);
		assertEquals(result.getNumber().doubleValue(), -1.345);
	}


	@Test
	public void shouldReturnPositiveValueUsingRoudingTypeAndScale() {
		operator = new RoudingMonetaryAmountOperator(RoundingMode.HALF_EVEN, 3);
		CurrencyUnit currency = Monetary.getCurrency("EUR");
		MonetaryAmount money = Money.parse("EUR 2.3523");
		MonetaryAmount result = operator.apply(money);
		assertEquals(result.getCurrency(), currency);
		assertEquals(result.getNumber().doubleValue(), 2.352);

	}

	@Test
	public void shouldReturnNegativeValueUsingRoudingTypeAndScale() {
		operator = new RoudingMonetaryAmountOperator(RoundingMode.HALF_EVEN, 4);
		CurrencyUnit currency = Monetary.getCurrency("BHD");
		MonetaryAmount money = Money.parse("BHD -1.34534432");
		MonetaryAmount result = operator.apply(money);
		assertEquals(result.getCurrency(), currency);
		assertEquals(result.getNumber().doubleValue(), -1.3453);
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void shouldReturnErroWhenIsNull() {
		operator.apply(null);
		fail();
	}
}
