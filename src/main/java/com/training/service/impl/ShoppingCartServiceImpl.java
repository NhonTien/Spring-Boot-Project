package com.training.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import com.training.entity.CartItem;
import com.training.service.ShoppingCartService;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	Map<Long, CartItem> maps = new HashMap<>();

	@Override
	public void add(CartItem item) {
		CartItem cartItem = maps.get(item.getProductId());
		if (cartItem == null) {
			maps.put(item.getProductId(), item);
		} else {
			cartItem.setQty(cartItem.getQty() + 1);
		}
	}

	@Override
	public void remove(Long id) {
		maps.remove(id);
	}

	@Override
	public CartItem update(Long proID, int qty) {
		CartItem cartItem = maps.get(proID);
		cartItem.setQty(qty);
		return cartItem;
	}

	@Override
	public void clear() {
		maps.clear();
	}

	@Override
	public Collection<CartItem> getAllItems() {
		return maps.values();
	}
	
	@Override
	public int getCount() {
		return maps.values().size();
	}
	
	@Override
	public double getAmount() {
		return maps.values().stream()
				.mapToDouble(item -> item.getQty() * (item.getPrice()*(1 - item.getDiscount()/100)))
				.sum();
	}

	@Override
	public int getCountQty() {
		return maps.values().stream()
				.mapToInt(item -> item.getQty())
				.sum();
	}
}
