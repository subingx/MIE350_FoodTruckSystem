package com.example.cms;

import com.example.cms.model.repository.FoodTruckOwnerRepository;
import com.example.cms.model.entity.Customer;
import com.example.cms.model.entity.FoodTruck;
import com.example.cms.model.entity.Favorite;
import com.example.cms.model.entity.FoodTruckOwner;
import com.example.cms.model.repository.CustomerRepository;
import com.example.cms.model.repository.FoodTruckRepository;
import com.example.cms.model.repository.FavoriteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class FavoriteTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private FavoriteRepository favoriteRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private FoodTruckRepository foodTruckRepository;

	@Autowired
	private FoodTruckOwnerRepository foodTruckOwnerRepository;

	private FoodTruckOwner createTestOwner(Long id) {
		FoodTruckOwner owner = new FoodTruckOwner();
		owner.setId(id);
		owner.setFirstName("Owner");
		owner.setLastName("McOwnerson");
		owner.setEmail("owner" + id + "@test.com");
		return foodTruckOwnerRepository.save(owner);
	}

	@Test
	void getFavoritesByCustomer() throws Exception {
		FoodTruckOwner owner = createTestOwner(100L);

		Customer customer = new Customer();
		customer.setId(1002L);
		customer.setFirstName("Test2");
		customer.setLastName("User2");
		customer.setEmail("test2@user.com");
		customerRepository.save(customer);

		FoodTruck truck = new FoodTruck();
		truck.setCode("TEST2");
		truck.setName("Test Truck 2");
		truck.setOwner(owner);
		foodTruckRepository.save(truck);

		Favorite favorite = new Favorite();
		favorite.setCustomer(customer);
		favorite.setFoodTruck(truck);
		favoriteRepository.save(favorite);

		MockHttpServletResponse response = mockMvc.perform(get("/favorites/customer/1002"))
				.andReturn().getResponse();

		assertEquals(200, response.getStatus());
		assertTrue(response.getContentAsString().contains("TEST2"));
	}

	@Test
	void createFavorite() throws Exception {
		Customer customer = new Customer();
		customer.setId(1003L);
		customer.setFirstName("Test3");
		customer.setLastName("User3");
		customer.setEmail("test3@user.com");
		customerRepository.save(customer);

		FoodTruckOwner owner = createTestOwner(103L);
		FoodTruck truck = new FoodTruck();
		truck.setCode("TEST3");
		truck.setName("Test Truck 3");
		truck.setOwner(owner);
		foodTruckRepository.save(truck);

		MockHttpServletResponse response = mockMvc.perform(
						post("/favorites")
								.param("customerId", "1003")
								.param("truckCode", "TEST3"))
				.andReturn().getResponse();

		assertEquals(200, response.getStatus());

		Favorite responseFavorite = objectMapper.readValue(
				response.getContentAsString(),
				Favorite.class
		);

		assertEquals(1003L, responseFavorite.getCustomer().getId());
		assertEquals("TEST3", responseFavorite.getFoodTruck().getCode());
	}

	@Test
	void deleteFavorite() throws Exception {
		Customer customer = new Customer();
		customer.setId(1004L);
		customer.setFirstName("Test4");
		customer.setLastName("User4");
		customer.setEmail("test4@user.com");
		customerRepository.save(customer);

		FoodTruckOwner owner = createTestOwner(104L);
		FoodTruck truck = new FoodTruck();
		truck.setCode("TEST4");
		truck.setName("Test Truck 4");
		truck.setOwner(owner);
		foodTruckRepository.save(truck);

		Favorite favorite = new Favorite();
		favorite.setCustomer(customer);
		favorite.setFoodTruck(truck);
		Favorite savedFavorite = favoriteRepository.save(favorite);

		MockHttpServletResponse response = mockMvc.perform(
						delete("/favorites/" + customer.getId() + "/" + truck.getCode()))
				.andReturn().getResponse();

		assertEquals(200, response.getStatus());
		assertTrue(favoriteRepository.findByCustomerIdAndFoodTruckCode(
				customer.getId(), truck.getCode()).isEmpty());
	}

	@Test
	void updateFavorite() throws Exception {
		Customer customer1 = new Customer();
		customer1.setId(1005L);
		customer1.setFirstName("Test5");
		customer1.setLastName("User5");
		customer1.setEmail("test5@user.com");
		customerRepository.save(customer1);

		Customer customer2 = new Customer();
		customer2.setId(1006L);
		customer2.setFirstName("Test6");
		customer2.setLastName("User6");
		customer2.setEmail("test6@user.com");
		customerRepository.save(customer2);

		FoodTruckOwner owner1 = createTestOwner(105L);
		FoodTruck truck1 = new FoodTruck();
		truck1.setCode("TEST5");
		truck1.setName("Test Truck 5");
		truck1.setOwner(owner1);
		foodTruckRepository.save(truck1);

		FoodTruckOwner owner2 = createTestOwner(106L);
		FoodTruck truck2 = new FoodTruck();
		truck2.setCode("TEST6");
		truck2.setName("Test Truck 6");
		truck2.setOwner(owner2);
		foodTruckRepository.save(truck2);

		Favorite favorite = new Favorite();
		favorite.setCustomer(customer1);
		favorite.setFoodTruck(truck1);
		Favorite savedFavorite = favoriteRepository.save(favorite);

		MockHttpServletResponse response = mockMvc.perform(
						put("/favorites/" + savedFavorite.getId())
								.param("customerId", "1006")
								.param("truckCode", "TEST6"))
				.andReturn().getResponse();

		assertEquals(200, response.getStatus());

		Favorite updatedFavorite = favoriteRepository.findById(savedFavorite.getId()).orElse(null);
		assertNotNull(updatedFavorite);
		assertEquals(1006L, updatedFavorite.getCustomer().getId());
		assertEquals("TEST6", updatedFavorite.getFoodTruck().getCode());
	}

	@Test
	void getTopFavoriteFoodTrucks() throws Exception {
		Customer customer = new Customer();
		customer.setId(1007L);
		customer.setFirstName("Test7");
		customer.setLastName("User7");
		customer.setEmail("test7@user.com");
		customerRepository.save(customer);

		FoodTruckOwner owner1 = createTestOwner(107L);
		FoodTruck popularTruck = new FoodTruck();
		popularTruck.setCode("POPULAR");
		popularTruck.setName("Popular Truck");
		popularTruck.setOwner(owner1);
		foodTruckRepository.save(popularTruck);

		FoodTruckOwner owner2 = createTestOwner(108L);
		FoodTruck lessPopularTruck = new FoodTruck();
		lessPopularTruck.setCode("LESS");
		lessPopularTruck.setName("Less Popular Truck");
		lessPopularTruck.setOwner(owner2);
		foodTruckRepository.save(lessPopularTruck);

		for (int i = 0; i < 5; i++) {
			Customer c = new Customer();
			c.setId(2000L + i);
			c.setFirstName("Temp" + i);
			c.setLastName("User" + i);
			c.setEmail("temp" + i + "@user.com");
			customerRepository.save(c);

			Favorite favorite = new Favorite();
			favorite.setCustomer(c);
			favorite.setFoodTruck(popularTruck);
			favoriteRepository.save(favorite);
		}

		for (int i = 0; i < 2; i++) {
			Customer c = new Customer();
			c.setId(3000L + i);
			c.setFirstName("OtherFan-" + i);
			c.setLastName("User");
			c.setEmail("other" + i + "@user.com");
			customerRepository.save(c);

			Favorite favorite = new Favorite();
			favorite.setCustomer(c);
			favorite.setFoodTruck(lessPopularTruck);
			favoriteRepository.save(favorite);
		}

		MockHttpServletResponse response = mockMvc.perform(get("/favorites/top"))
				.andReturn().getResponse();

		assertEquals(200, response.getStatus());
		assertTrue(
				response.getContentAsString().contains("\"code\":\"POPULAR\""),
				"Top truck should be POPULAR (5 favorites), not LESS (2 favorites)"
		);
	}
}