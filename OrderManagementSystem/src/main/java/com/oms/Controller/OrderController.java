package com.oms.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oms.DTO.OrderDTO;
import com.oms.Service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("order")
@Tag(name = "Order Controller", description = "Order Info.")
public class OrderController {

	//private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService os;
	
	@PostMapping("saveorder")
	@Operation(summary = "Save order details.", description = "Save order info.")
	public ResponseEntity<OrderDTO> saveOrder(@Validated @RequestBody OrderDTO op) {
		ResponseEntity<OrderDTO> rs = null;
		//logger.info("I am in controller save order method started"+op);
		System.out.println(op);
		os.saveOrder(op);
		rs = new ResponseEntity<OrderDTO>(op, HttpStatus.CREATED);
		//logger.info("I am in controller save order method end"+op);
		return rs;
	}
	
	@PutMapping("updateorder")
	@Operation(summary = "Update order details.", description = "Update order info.")
	public ResponseEntity<OrderDTO> updateOrder(@Validated @RequestBody OrderDTO op) {
		ResponseEntity<OrderDTO> rs = null;
		//logger.info("I am in controller save order method started : "+op);
		os.updateOrder(op);
		rs = new ResponseEntity<OrderDTO>(op, HttpStatus.CREATED);
		//logger.info("I am in controller save order method end : "+op);
		return rs;
	}
	
	@DeleteMapping("deleteorder/{oid}")
	@Operation(summary = "Delete order details.", description = "Delete order info.")
	public ResponseEntity<Object> deleteOrder(@PathVariable("id") Integer oid) {
		ResponseEntity<Object> rs = null;
		//logger.info("I am in controller delete order started");
		//logger.info("I am in controller delete order method started");
		os.deleteOrder(oid);
		rs = new ResponseEntity<Object>(oid+" : order deleted succesfully.", HttpStatus.CREATED);
		//logger.info("I am in controller delete order method end");
		//logger.info("I am in controller delete order end");
		return rs;
	}
	
	@Cacheable("OrderPojo")
	@GetMapping("getorderbyid/{id}")
	@Operation(summary = "Get order details using Order ID.", description = "Fetch order details using Order ID.")
	public ResponseEntity<OrderDTO> getByOrderId(@PathVariable("id") Integer oid) {
		ResponseEntity<OrderDTO> rs = null;
		//logger.info("I am in controller getbyid order method started");
		OrderDTO d = os.getByOrderId(oid);
		rs = new ResponseEntity<OrderDTO>(d, HttpStatus.OK);
		//logger.info("I am in controller getbyid order method end");
		return rs;
	}
	
	//org.modelmapper.MappingException: ModelMapper mapping errors:
	//Caused by: org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.oms.Entity.ProductPojo.vpo: could not initialize proxy - no Sessions
	
	//while using schedule it will expect for eager loading because order associated with orderprocesspojo (one to many)
	//by default one to many has lazy loading, change fetch type to eager loading.
	
	@Scheduled(cron = "0 * * * * *")
	@GetMapping("getall")
	@Operation(summary = "Get all orders.", description = "Fetch all order info.")
	public ResponseEntity<List<OrderDTO>> getAll() {
		ResponseEntity<List<OrderDTO>> rs = null;	
		//logger.info("I am in controller getall order started");
		//logger.info("I am in controller getall order method started");
		List<OrderDTO> op = os.getAllOrders();
		rs = new ResponseEntity<List<OrderDTO>>(op, HttpStatus.OK);
		//logger.info("I am in controller getall order method end");
		//logger.info("I am in controller getall order end");
		return rs;
	}
}
