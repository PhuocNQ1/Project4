package com.example.estateagency1.Service.Impl;

import com.example.estateagency1.DTO.PropertyDTO;
import com.example.estateagency1.DTO.PropertyFavourite;
import com.example.estateagency1.DTO.PropertyFavouriteLine;
import com.example.estateagency1.Mapping.PropertyMapping;
import com.example.estateagency1.Repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PropertyFavouriteService {
    @Autowired
    PropertyRepository propertyRepository;
    @Autowired
    PropertyMapping propertyMapping;


    public void addToCart(Integer id, HttpSession session, String loginName) {
        String nameSession = "CART"+loginName;
        HashMap<Integer, PropertyFavouriteLine> cart;
        var rawCart = session.getAttribute(nameSession);
        if (rawCart instanceof HashMap) {
            cart = (HashMap<Integer, PropertyFavouriteLine>) rawCart;
        } else {
            cart = new HashMap<>();
        }
//
//       PropertyDTOO product = propertyMapping.getPropertyDTOOByID(id);
        PropertyDTO product = propertyMapping.getPropertyDTOByID(id);
        if (product!=null) {
            PropertyFavouriteLine cartItem = cart.get(id);
            if (cartItem == null) {
                cart.put(id, new PropertyFavouriteLine(product, 1));
            } else {
               cartItem.increaseByOne();
                cart.put(id, cartItem);
            }
        }
        session.setAttribute("CART"+loginName,cart);
    }

    public int countItemInCart(HttpSession session,String loginName) {
        String nameSession = "CART"+loginName;
        HashMap<Long, PropertyFavouriteLine> cart;

        var rawCart = session.getAttribute(nameSession);

        if (rawCart instanceof HashMap) {
            cart = (HashMap<Long, PropertyFavouriteLine>) rawCart;
            return  cart.values().stream().mapToInt(PropertyFavouriteLine::getCount).sum();
        } else {
            return 0;
        }
    }

    public PropertyFavourite getCart(HttpSession session,String loginName) {
        String nameSession = "CART"+loginName;
        HashMap<Integer, PropertyFavouriteLine> cart;

        var rawCart = session.getAttribute(nameSession);

        if (rawCart instanceof HashMap) {
            cart = (HashMap<Integer, PropertyFavouriteLine>) rawCart;
            return new PropertyFavourite(cart.values().stream().collect(Collectors.toList())); //danh sách các mặt hàng mua);
        } else {
            return new PropertyFavourite();
        }
    }
}
