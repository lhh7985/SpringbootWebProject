package com.ho.hwang.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ho.hwang.dto.Activity.SelectCustomerActivityDto;
import com.ho.hwang.dto.Activity.SelectVisitDto;
import com.ho.hwang.dto.Customer.*;
import com.ho.hwang.dto.ManagerHistory.SelectManagerDto;
import com.ho.hwang.dto.Product.SelectDeliveryDto;
import com.ho.hwang.dto.Product.SelectTotalOsDto;
import com.ho.hwang.dto.Sr.SelectSrListDto;
import com.ho.hwang.paging.Page;
import com.ho.hwang.service.*;
import com.ho.hwang.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/*")
public class CustomerController {

	private final UserService userService;
	private final CustomerService customerService;
	private final SrService srService;
	private final ActivityService activityService;
	private final ProductService productService;

	@GetMapping("/detail")
	public String getDetail(int customerId, Model model) {
		CustomerVo customerDetail = customerService.selectCustomerDetail(customerId);
		Map<String, Object> managers = userService.selectEmployee(customerDetail.getEmployeeIdManager(),customerDetail.getEmployeeIdSe(),customerDetail.getEmployeeIdSales());

		model.addAttribute("customer", customerDetail);
		model.addAttribute("managers", managers);

		return "customer/detail";
	}

	@GetMapping("/delivery")
	public String getDelivery(Model model, HttpServletRequest req) {
		int customerId = Integer.parseInt(req.getParameter("customerId"));
		List<SelectDeliveryDto> list = productService.selectDelivery(customerId);
		model.addAttribute("list", list);
		List<SelectTotalOsDto> list2 = productService.selectOS(customerId);
		model.addAttribute("list2", list2);

		return "customer/delivery";
	}

	@GetMapping("/manager")
	public String getManagerHistory(Model model, HttpServletRequest req) {
		int customerId = Integer.parseInt(req.getParameter("customerId"));
		List<SelectManagerDto> list = customerService.selectManager(customerId);
		model.addAttribute("list", list);
		return "customer/manager";
	}

	@GetMapping("/list")
	public String getCustomerList(@RequestParam(defaultValue = "1") int page, Model model) {

		int listCnt = customerService.selectCustomerTotalCount();
		Page paging = new Page(listCnt, page);

		List<CustomerListVo> list = customerService.selectCustomerList(paging.getStartIndex(),paging.getPageSize());
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);

		return "customer/list";
	}

	@GetMapping("/sr")
	public String getSrList(Model model, HttpServletRequest req) {
		int customerId = Integer.parseInt(req.getParameter("customerId"));
		List<SelectSrListDto> srList = srService.selectSRList(customerId);
		model.addAttribute("srList", srList);

		return "customer/sr";
	}

	@GetMapping("/sr-detail")
	public String getSrDetail(Model model, int srId) {
		SrVo srVo = srService.selectSRDetail(srId);
		List<SelectCustomerActivityDto> selectCustomerActivityDto = activityService.selectCustomerActivity(srId);
		model.addAttribute("srvo", srVo);
		model.addAttribute("acvo", selectCustomerActivityDto);

		return "customer/sr_detail";
	}

	@GetMapping("/activity")
	public String getCustomerActivity(Model model, HttpServletRequest req) {
		int customerId = Integer.parseInt(req.getParameter("customerId"));
		List<SelectVisitDto> list = activityService.selectVisit(customerId);
		model.addAttribute("list", list);

		return "/customer/activity";
	}

	@GetMapping(value = "/modify")
	public String modifyCustomerDetail() {
		return "/customer/modify";
	}

	@PostMapping(value = "/modify")
	public void modifyCustomerDetail(UpdateCustomerDetailDto updateCustomerDetailDto) {
		customerService.updateCustomerDetail(updateCustomerDetailDto);
	}

	@PostMapping("/delete")
	@ResponseBody
	public int deleteCustomer(@RequestParam(value = "chbox[]") List<Integer> charr) throws Exception {
		int result = customerService.deleteCustomer(charr);
		return result;
	}

	@GetMapping("/enroll")
	public String enrollCustomer(Model model) {
		return "/customer/enroll";
	}

	// 고객사 등록부분
	@PostMapping("/enroll")
	public void enrollCustomer(InsertCustomerDto insertCustomerDto) {
		customerService.insertCustomer(insertCustomerDto);
	}
}
