package com.ecobank.srms.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(includeFieldNames=true)
	public class UtilsResponse<T> {
	    private String responseCode;
	    private String responseMessage;

	    @JsonInclude(JsonInclude.Include.NON_NULL)
	    private T data;
	}



