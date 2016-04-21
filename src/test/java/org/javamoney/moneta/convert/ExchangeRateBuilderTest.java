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
package org.javamoney.moneta.convert;

import static org.testng.Assert.assertEquals;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.NumberValue;
import javax.money.convert.ConversionContext;
import javax.money.convert.ExchangeRate;

import org.javamoney.moneta.spi.DefaultNumberValue;
import org.testng.annotations.Test;

public class ExchangeRateBuilderTest {


	private static final NumberValue NUMBER_FACTOR = DefaultNumberValue.of(10);
	private static final CurrencyUnit CURRENCY = Monetary.getCurrency("BRL");

	@Test(expectedExceptions  = NullPointerException.class)
	public void shouldReturnNPEWhenConversionContextIsNull() {
		ConversionContext context = null;
		new ExchangeRateBuilder(context).setBase(CURRENCY)
				.setTerm(CURRENCY).setFactor(NUMBER_FACTOR).build();
	}

	@Test(expectedExceptions  = NullPointerException.class)
	public void shouldReturnNPEWhenTermIsNull() {
		new ExchangeRateBuilder(ConversionContext.ANY_CONVERSION).setBase(CURRENCY)
				.setTerm(null).setFactor(NUMBER_FACTOR).build();
	}

	@Test(expectedExceptions  = NullPointerException.class)
	public void shouldReturnNPEWhenBaseIsNull() {
		new ExchangeRateBuilder(ConversionContext.ANY_CONVERSION).setBase(null)
				.setTerm(CURRENCY).setFactor(NUMBER_FACTOR).build();
	}

	@Test(expectedExceptions  = NullPointerException.class)
	public void shouldReturnNPEWhenFactorIsNull() {
		new ExchangeRateBuilder(ConversionContext.ANY_CONVERSION).setBase(CURRENCY)
				.setTerm(CURRENCY).setFactor(null).build();
	}

	@Test
	public void shouldCreateExchangeRate() {
		ExchangeRate rate = new ExchangeRateBuilder(ConversionContext.ANY_CONVERSION).setBase(CURRENCY)
				.setTerm(CURRENCY).setFactor(NUMBER_FACTOR).build();

		assertEquals(rate.getContext(), ConversionContext.ANY_CONVERSION);
		assertEquals(rate.getBaseCurrency(), CURRENCY);
		assertEquals(rate.getFactor(), NUMBER_FACTOR);
	}

	@Test
	public void shouldCreateExchangeRateFromExchangeRate() {
		ExchangeRate rate = new ExchangeRateBuilder(ConversionContext.ANY_CONVERSION).setBase(CURRENCY)
				.setTerm(CURRENCY).setFactor(NUMBER_FACTOR).build();

		ExchangeRate rate2 = new ExchangeRateBuilder(rate).build();

		assertEquals(rate.getContext(), rate2.getContext());
		assertEquals(rate.getBaseCurrency(), rate2.getBaseCurrency());
		assertEquals(rate.getFactor(), rate2.getFactor());
	}
}

