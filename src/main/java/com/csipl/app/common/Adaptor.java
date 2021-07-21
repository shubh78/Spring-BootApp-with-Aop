package com.csipl.app.common;

import java.util.List;

public interface Adaptor<dto, dbtable> {

	public List<dbtable> uiDtoToDatabaseModelList(List<dto> list);

	public List<dto> databaseModelToUiDtoList(List<dbtable> list);

	public dbtable uiDtoToDatabaseModel(dto dto);

	public dto databaseModelToUiDto(dbtable dbobj);
}
