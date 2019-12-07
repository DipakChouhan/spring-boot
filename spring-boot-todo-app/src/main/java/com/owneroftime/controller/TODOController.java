package com.owneroftime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
	
	/**
	 * This method handles the add request for a TODO
	 * @param todo : TODO to be added
	 * @return String : JSON representation of added TODO
	 * @apiNote use Postman "http://localhost:7075/TODO/add"
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addTODO(@RequestBody TODOModel todo) {
		todo.setTodoId(null);
		todoDataBean.setTodo(todoService.addTODO(todo));
		return gson.toJson(todoDataBean.getTodo());
	}
	
	/**
	 * This method handles request to get all the TODOs
	 * @return String : JSON representation of list of TODOs
	 * @apiNote use Postman "http://localhost:7075/TODO/viewAll"
	 */
	@RequestMapping(value = "/viewAll", method = RequestMethod.GET)
	public String viewAllTODO() {
		List<TODOModel> todoList = todoService.getTodoList();
		todoDataBean.setTodoList(todoList);
		return gson.toJson(todoDataBean.getTodoList());
	}
	
	/**
	 * This method handles the request for TODO update
	 * @param todo : TODO to be updated
	 * @return String : JSON representation of updated TODO
	 * @apiNote use Postman "http://localhost:7075/TODO/update"
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateTODO(@RequestBody TODOModel todo) {
		todoDataBean.setTodo(todoService.updateTODO(todo));
		return gson.toJson(todoDataBean.getTodo());
	}
	
	/**
	 * This method handles the request for viewing a single TODO
	 * @param todo : ID of the TODO to be viewed
	 * @return String : JSON representation of requested TODO
	 * @apiNote use Postman "http://localhost:7075/TODO/view/{id}"
	 */
	@RequestMapping(value = "/view/{id}",method = RequestMethod.GET)
	public String viewTODO(@PathVariable("id") long todoId) {
		todoDataBean.setTodo(todoService.findTODOById(todoId));
		return gson.toJson(todoDataBean.getTodo());
	}
	
	/**
	 * This method handles the request for TODO delete
	 * @param todo : ID of the TODO to be deleted
	 * @return String : JSON representation of all TODOs
	 * @apiNote use Postman "http://localhost:7075/TODO/delete/{id}"
	 */
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
