package com.berkay22demirel.basiccart.dao.impl;

import org.springframework.stereotype.Repository;

import com.berkay22demirel.basiccart.dao.IShoppingCartItemDao;
import com.berkay22demirel.basiccart.entity.ShoppingCartItem;

@Repository
public class ShoppingCartItemDao extends DaoSupoort<ShoppingCartItem> implements IShoppingCartItemDao {

}
