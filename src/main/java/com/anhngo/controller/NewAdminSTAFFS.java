package com.anhngo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anhngo.dao.NhanVienDAO;
import com.anhngo.entity.NhanVienEntity;

@Controller
public class NewAdminSTAFFS {
	@Autowired
	private NhanVienDAO nhanvienDAO;

	@RequestMapping({ "/admin/staffs1/index", "/admin/staffs1/edit/{id}" })
	public String index(Model model, @PathVariable(required = false) Integer id) {
		NhanVienEntity item;
		if (id != null) {
			item = nhanvienDAO.findById(id).orElse(new NhanVienEntity());
		} else {
			item = new NhanVienEntity();
		}
		model.addAttribute("item", item);
		List<NhanVienEntity> items = nhanvienDAO.findAll();
		model.addAttribute("items", items);
		return "html/staffs";
	}

	@RequestMapping("/admin/staffs1/save")
	public String save(NhanVienEntity item) {
		nhanvienDAO.save(item);
		return "redirect:/admin/staffs1/index";
	}

	@RequestMapping("/admin/staffs1/update")
	public String update(NhanVienEntity item) {
		nhanvienDAO.save(item);
		return "redirect:/admin/staffs1/edit/" + item.getMaNhanVien();
	}

	@RequestMapping("/admin/staffs1/delete/{id}")
	public String delete(@PathVariable Integer id) {
		nhanvienDAO.deleteById(id);
		return "redirect:/admin/staffs1/index";
	}
}
