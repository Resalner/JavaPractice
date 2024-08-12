package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.Address;
import com.github.resalner.javapractice.model.Order;
import com.github.resalner.javapractice.model.OrderItem;
import com.github.resalner.javapractice.model.Status;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.repository.AddressRepository;
import com.github.resalner.javapractice.repository.OrderItemRepository;
import com.github.resalner.javapractice.repository.OrderRepository;
import com.github.resalner.javapractice.repository.UserRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final AddressRepository addressRepository;
	private final OrderItemRepository orderItemRepository;

	@Override
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order saveOrder(Order order) {
		User user = order.getUser();
		if (user != null && user.getId() != null) {
			userRepository.findById(user.getId()).orElseThrow(
					() -> new EntityNotFoundException("Пользователь с id = " + user.getId() + " не найден"));
		}

		Address address = order.getAddress();
		if (address != null && address.getId() != null) {
			addressRepository.findById(address.getId())
					.orElseThrow(() -> new EntityNotFoundException("Адрес с id = " + address.getId() + " не найден"));
		}

		order = orderRepository.save(order);
		return order;
	}

	@Override
	public Order getOrder(long id) {
		return getOrderIfExists(id);
	}

	@Override
	public void deleteOrder(long id) {
		if (!orderRepository.existsById(id)) {
			throw new EntityNotFoundException("Не найден продукт с id = " + id);
		}
		orderRepository.deleteById(id);
	}

	@Override
	public Order updateOrder(long id, Order orderForUpdate) {
		Order order = getOrderIfExists(id);

		User newUser = orderForUpdate.getUser();
		Date newOrderDate = orderForUpdate.getOrderDate();
		Double newTotalPrice = orderForUpdate.getTotalPrice();
		Status newStatus = orderForUpdate.getStatus();
		Address newAddress = orderForUpdate.getAddress();
		String newComments = orderForUpdate.getComments();

		order.setUser(newUser);
		order.setOrderDate(newOrderDate);
		order.setTotalPrice(newTotalPrice);
		order.setStatus(newStatus);
		order.setAddress(newAddress);
		order.setComments(newComments);

		order = orderRepository.save(order);

		return order;
	}

	private Order getOrderIfExists(long id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найден заказ с id = " + id));
	}

	 @Override
	    public List<OrderItem> getOrderItemByOrderId(long orderId) {
		 return orderItemRepository.findAllById(Collections.singletonList(orderId));
	    }
}