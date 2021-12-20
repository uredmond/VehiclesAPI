package com.udacity.pricing;

import com.udacity.pricing.entity.Price;
import com.udacity.pricing.repository.PriceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {

	@Autowired
	PriceRepository priceRepository;
	@Autowired
	MockMvc mockMvc;

	@Test
	public void findAll_returnsExpected(){
		List<Price> prices = (List<Price>) priceRepository.findAll();
		assertEquals(20, prices.size());
		assertEquals(1, prices.get(0).getVehicleId().intValue());
		assertEquals(20, prices.get(19).getVehicleId().intValue());
	}

	@Test
	public void findById_idPresent_returnsId(){
		Optional<Price> optionalPrice = priceRepository.findById(1L);
		assertTrue(optionalPrice.isPresent());
		Price price = optionalPrice.get();
		assertEquals(price.getPrice(), new BigDecimal("24367.11"));
	}

	@Test
	public void findById_idAbsent_priceAbsent(){
		Optional<Price> optionalPrice = priceRepository.findById(21L);
		assertFalse(optionalPrice.isPresent());
	}

	@Test
	public void getPrice_idPresent_returnsOk() throws Exception {
		mockMvc.perform(get("/prices/1")
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	}
	@Test
	public void getPrice_idAbsent_returnsError() throws Exception {
		mockMvc.perform(get("/prices/21")
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is4xxClientError());
	}

}
