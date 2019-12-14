package com.owneroftime.validators;

import java.io.FileDescriptor;

import org.apache.commons.compress.compressors.FileNameUtil;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.owneroftime.databean.ExcelDatabaseDataBean;

@Component
public class ExcelDatabaseValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ExcelDatabaseDataBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ExcelDatabaseDataBean bean = (ExcelDatabaseDataBean) target;
		if (bean.getFile().getSize() == 0) {
			errors.rejectValue("file", "Please upload a file to continue" ,"Please upload a file to continue");
		}
		
		System.out.println(bean.getFile().getOriginalFilename());
		
//		if (bean.getDownUpModel().getUploadTableSchemaName().isEmpty()) {
//			errors.rejectValue("downUpModel.uploadTableSchemaName", "Please select a schema", "Please select a schema");
//		}
		
		if (bean.getDownUpModel().getUploadTableName().isEmpty()) {
			errors.rejectValue("downUpModel.uploadTableSchemaName", "Please select a table", "Please select a table");
		}
	}

}
