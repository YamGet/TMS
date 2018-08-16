package org.fidel.tms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fidel.tms.model.Files;
import org.fidel.tms.model.Report;
import org.fidel.tms.service.FilesService;
import org.fidel.tms.utils.CurrentURL;
import org.fidel.tms.utils.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

@Controller
@RequestMapping("files")
public class FilesController extends MultiActionController {
	
	@Autowired
	FilesService filesService;
	
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public ModelAndView upload(@ModelAttribute("files") Files files, HttpServletRequest request, HttpServletResponse response){
		
		try{
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			
			MultipartFile multipartFile = multipartRequest.getFile("files");
			
			Files file = new Files();
			
			files.setFilename(multipartFile.getOriginalFilename());
			files.setNotes(ServletRequestUtils.getStringParameter(request, "notes"));
			files.setType(multipartFile.getContentType());
			files.setFile(multipartFile.getBytes());
			
			filesService.save(file);
		
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		return new ModelAndView("admin/expenseType/expenseTypeTemplate");
	}
	
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public ModelAndView download(@ModelAttribute("files") Files files, HttpServletRequest request, HttpServletResponse response){
		
		try{
			
			int id = ServletRequestUtils.getRequiredIntParameter(request, "id");
			
			Files file = filesService.find(id);
			
			response.setContentType(file.getType());
			response.setContentLength(file.getFile().length);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getFilename() + "\"");
			
			FileCopyUtils.copy(file.getFile(), response.getOutputStream());
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("files") Files files, HttpServletRequest request, HttpServletResponse response){
		
		try{
			
			int id = ServletRequestUtils.getRequiredIntParameter(request, "id");
			
			filesService.delete(id);
			
		} catch(Exception ex){
			ex.printStackTrace();
		}

		List<Files> fileList = filesService.listAllActiveFiles();
		
		ModelAndView modelandview = new ModelAndView("index");
		
		modelandview.addObject("files", fileList);
		
		return modelandview;
	}
	
	@RequestMapping(value="/getDocumentDownloadLinkList", method=RequestMethod.GET)
	public ModelAndView getDocumentDownloadLinkList(@ModelAttribute("files") Files files, HttpServletRequest request, HttpServletResponse response){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<Files> fileList = filesService.listAllActiveFiles();
		
		ModelAndView modelandview = new ModelAndView("admin/securePage/file_download");
		
		modelandview.addObject("files", fileList);
		
		return modelandview;
	}

}
