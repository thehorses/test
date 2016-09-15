package br.com.ayto.base.service;

import java.io.Serializable;

import br.com.ayto.base.model.BaseModel;

public interface BaseService extends Serializable {
	
	<T extends BaseModel> T findById(Class<T> clazz, Object id);

}
