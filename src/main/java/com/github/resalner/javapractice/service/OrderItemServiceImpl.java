package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.Order;
import com.github.resalner.javapractice.model.OrderItem;
import com.github.resalner.javapractice.model.Product;
import com.github.resalner.javapractice.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

	private final OrderItemRepository orderItemRepository;

	@Override
	public List<OrderItem> getOrderItems() {
		return orderItemRepository.findAll();
	}

	@Override
	public OrderItem saveOrderItem(OrderItem orderItem) {
		orderItem = orderItemRepository.save(orderItem);
		return orderItem;
	}

	@Override
	public OrderItem getOrderItem(long id) {
		return orderItemRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найден элемент заказа с id = " + id));
	}

	@Override
	public void deleteOrderItem(long id) {
		orderItemRepository.deleteById(id);
	}

	@Override
	public OrderItem updateOrderItem(long id, OrderItem orderItemForUpdate) {
		OrderItem orderItem = orderItemRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найден элемент заказа с id = " + id));

		Order newOrder = orderItemForUpdate.getOrder();
		Product newProduct = orderItemForUpdate.getProduct();
		Integer newCount = orderItemForUpdate.getCount();
		Double newPrice = orderItemForUpdate.getPrice();

		if (Objects.nonNull(newOrder)) {

			orderItem.setOrder(newOrder);
		}
		if (Objects.nonNull(newProduct)) {

			orderItem.setProduct(newProduct);
		}
		if (Objects.nonNull(newCount)) {

			orderItem.setCount(newCount);
		}
		if (Objects.nonNull(newPrice)) {

			orderItem.setPrice(newPrice);
		}
		orderItem = orderItemRepository.save(orderItem);
		return orderItem;
	}
}