package com.furni.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.furni.dto.DetailorderDTO;
import com.furni.dto.OrderpageDTO;
import com.furni.dto.ShipDTO;
import com.furni.service.DetailorderService;
import com.furni.service.OrderpageService;
import com.furni.service.ReviewService;
import com.furni.service.ShipService;




@Controller
@RequestMapping("/order")
public class JController {
	
	String dir = "order/"; //폴더
	
	@Autowired
	OrderpageService service;

	@Autowired
	DetailorderService service1;
	
	@Autowired
	ShipService service2;
	
	@Autowired
	ReviewService service3;
	
	@RequestMapping("/insert")
	public String insert(Model model, OrderpageDTO order) {
		 OrderpageDTO list = null;
		 DetailorderDTO list1= null;
		try {
			service.register(order);
			int i = order.getOrderno();
			
			list1= new DetailorderDTO(0, i, order.getItem_no(), order.getItem_name(), order.getItem_color(), order.getItemcnt(), order.getItem_img(), order.getCustid(), 0);
			service1.register(list1);
			
			list=service.get(i);

			model.addAttribute("list",list);
			model.addAttribute("center",dir+"insert");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "main";
	}
	@RequestMapping("/insertimpl")
	public String insertimpl(Model model, OrderpageDTO order) throws Exception {
		try {
			//service.insert1(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(order);
		return "redirect:detail";	//insert후 주문상세로 가라?
	}
	@RequestMapping("/detail")
	public String detail(Model model, String id) {
		List<DetailorderDTO> list = null;
		try {
			list = service1.detailall(id);
			model.addAttribute("list", list);
			model.addAttribute("center",dir+"detail");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "main";
	
	}
	
	@RequestMapping("/ordershipdetail")
	public String ordershipdetail(Model model, String id) {
		List<ShipDTO> list = null;
		try {
			list = service2.shipall(id);
			model.addAttribute("list", list);
			model.addAttribute("center",dir+"detail");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "main";
	
	}
}
