package com.training.service;

import java.util.Collection;

import com.training.entity.CartItem;

public interface ShoppingCartService {

	void add(CartItem item);

	double getAmount();

	int getCount();

	Collection<CartItem> getAllItems();

	void clear();

	CartItem update(Long proID, int qty);

	void remove(Long id);

	int getCountQty();

}
