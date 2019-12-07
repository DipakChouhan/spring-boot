package com.owneroftime.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.owneroftime.bean.TODODataBean;
import com.owneroftime.model.TODOModel;
import com.owneroftime.service.TODOService;

@RestController
@RequestMapping(value = "/TODO")
public class TODOController {
	@Autowired
	private TODODataBean todoDataBean;
	@Autowired
	private TODOService todoService;
	@Autowired
	Gson gson;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addTODO(@RequestBody TODOModel todo) {
		todo.setTodoId(null);
		todoDataBean.setTodo(todoService.addTODO(todo));
		return gson.toJson(todoDataBean.getTodo());
	}
	
	@RequestMapping(value = "/viewAll", method = RequestMethod.GET)
	public String viewAllTODO() {
		List<TODOModel> todoList = todoService.getTodoList();
		todoDataBean.setTodoList(todoList);
		return gson.toJson(todoDataBean.getTodoList());
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateTODO(@RequestBody TODOModel todo) {
		todoDataBean.setTodo(todoService.updateTODO(todo));
		return gson.toJson(todoDataBean.getTodo());
	}
	
	@RequestMapping(value = "/view/{id}",method = RequestMethod.GET)
	public String viewTODO(@PathVariable("id") long todoId) {
		todoDataBean.setTodo(todoService.findTODOById(todoId));
		return gson.toJson(todoDataBean.getTodo());
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String deleteTODO(@PathVariable("id") long todoId) {
		todoService.deleteTODOById(todoId);
		return gson.toJson(todoDataBean.getTodoList());
	}
	
//	@RequestMapping(value = "/batch/batchProcessing", method = RequestMethod.GET)
//	public String batchProcessingTODO() {
//		return "batch";
//	}
	
//	@RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
//	public String batchUploadTODO(@RequestParam("filename") String filename, @RequestParam("file") MultipartFile file, Model model) {
//		if (!file.isEmpty()) {
//			try {
//				byte[] bytes = file.getBytes();
//
//				// Creating the directory to store file
//				String rootPath = System.getProperty("catalina.home");
//				File dir = new File(rootPath + File.separator + "tmpFiles");
//				if (!dir.exists())
//					dir.mkdirs();
//
//				// Create the file on server
//				File serverFile = new File(dir.getAbsolutePath()
//						+ File.separator + filename);
//				BufferedOutputStream stream = new BufferedOutputStream(
//						new FileOutputStream(serverFile));
//				stream.write(bytes);
//				stream.close();
//
//				System.out.println("Server File Location="
//						+ serverFile.getAbsolutePath());
//
//				return "batch";
//			} catch (Exception e) {
//				return "index";
//			}
//		} else {
//			return "batch";
//		}
//	}
}
