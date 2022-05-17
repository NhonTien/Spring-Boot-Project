package com.training.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.training.dao.IOrderDao;
import com.training.dao.IOrderDetailDao;
import com.training.entity.CartItem;
import com.training.entity.Order;
import com.training.entity.OrderDetail;
import com.training.entity.ProductEntity;
import com.training.service.IProductService;
import com.training.service.ShoppingCartService;

@Controller
@RequestMapping("cart")
public class ShoppingCartController {
	
	@Autowired
	IProductService productService;
	
	@Autowired
	ShoppingCartService cartService;
	
    @Autowired
    IOrderDao oderDao;
    
    @Autowired
    IOrderDetailDao orderDetailsDao;
	
	@GetMapping
	public String viewCarts(Model model) {
		model.addAttribute("CART_ITEMS", cartService.getAllItems());
		model.addAttribute("TOTAL", cartService.getAmount());
		model.addAttribute("TOTALQTY", cartService.getCountQty());
		return "cart";
	}
	
	@GetMapping(value = { "checkout" })
	public String initPage4(Model model){
		model.addAttribute("CART_ITEMS", cartService.getAllItems());
		model.addAttribute("TOTAL", cartService.getAmount());
		model.addAttribute("TOTALQTY", cartService.getCountQty());
		return "checkout";
	}
	
	@GetMapping(value = { "thankyou" })
	public String initPage5(Model model){
		model.addAttribute("CART_ITEMS", cartService.getAllItems());
		model.addAttribute("TOTAL", cartService.getAmount());
		model.addAttribute("TOTALQTY", cartService.getCountQty());
		return "thanks-order";
	}
	
	@PostMapping(value = {"/save/order"})
    public String saveorder(Model model ,@ModelAttribute("item") Order item){
        oderDao.save(item);
        Collection<CartItem>  carts = cartService.getAllItems();
        ProductEntity productEntity = new ProductEntity();
        for(CartItem cartItem : carts){
            OrderDetail items = new OrderDetail();
            productEntity.setProductId(cartItem.getProductId());
            items.setPrice(cartItem.getPrice() * (1 - cartItem.getDiscount()/100));
            items.setQuantity(cartItem.getQty());
            items.setAmount(cartItem.getAmount());
            items.setOrder(item);
            items.setProductEntity(productEntity);
            orderDetailsDao.save(items);
            ProductEntity productEntity1 = productService.findByProductId(cartItem.getProductId());
            productEntity1.setQuantity(productEntity1.getQuantity() - items.getQuantity());
			productService.updateApi(productEntity1);
        }
        cartService.clear();
        return "redirect:/cart/thankyou";
    }
	
	@GetMapping("add/{productId}")
	public String addCart(@PathVariable("productId") Long productId, RedirectAttributes redirectAttributes) {
		ProductEntity productEntity = productService.findByProductId(productId);
		if(productEntity != null) {
			CartItem item = new CartItem();
			if(productEntity.getQuantity() >= item.getQty()) {
			item.setProductId(productEntity.getProductId());
			item.setImage(productEntity.getImage());
			item.setName(productEntity.getProductName());
			item.setPrice(productEntity.getPrice());
			item.setDiscount(productEntity.getDiscount());
			item.setCategory(productEntity.getCategoryEntity().getCategoryName());
			item.setCategorySlug(productEntity.getCategoryEntity().getCategorySlug());
			item.setProductSlug(productEntity.getProductSlug());
			item.setQty(1);
			cartService.add(item);
			} else {
				redirectAttributes.addFlashAttribute("message","Sản phẩm " + productEntity.getProductName() + " tạm hết hàng. Quý khách vui lòng chọn sản phẩm khác.");
				return "redirect:/cart";
			}
		}
		return "redirect:/cart";
	}
	
	@GetMapping("clear")
	public String clearCart() {
		cartService.clear();
		return "redirect:/cart";
	}
	
	@GetMapping("del/{productId}")
	public String removeCart(@PathVariable("productId") Long productId) {
		cartService.remove(productId);
		return "redirect:/cart";
	}
	
	@PostMapping("update")
	public String update(@RequestParam("productId") Long productId, @RequestParam("qty") int qty) {
		cartService.update(productId, qty);
		return "redirect:/cart";
	}
}
