package com.ecobank.srms.utils;


import com.ecobank.srms.exceptions.NrhException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResponseUtils {

	private final AccountJSONUtils jsonUtils;

	private ResponseEntity<UtilsResponse> getSuccess(String code, String message, Object data) {
		UtilsResponse response = new UtilsResponse();
		response.setResponseCode(code);
		response.setResponseMessage(message);

		response.setData(data);

		return ResponseEntity.ok(response);
	}

	public ResponseEntity<UtilsResponse> getSuccess(String code, String message) {
		UtilsResponse response = new UtilsResponse();

		response.setResponseCode(code);
		response.setResponseMessage(message);

		return ResponseEntity.ok(response);
	}

	public ResponseEntity<UtilsResponse> getSuccess(ResponseEnum responseEnum) {
		UtilsResponse response = new UtilsResponse();
		response.setResponseCode(responseEnum.getCode());
		response.setResponseMessage(responseEnum.getMessage());

		return ResponseEntity.ok(response);
	}

	public ResponseEntity<UtilsResponse> getSuccess(ResponseEnum responseEnum, Object data) {
		UtilsResponse response = new UtilsResponse();

		response.setResponseCode(responseEnum.getCode());
		response.setResponseMessage(responseEnum.getMessage());

		response.setData(data);

		return ResponseEntity.ok(response);
	}

	public <T> ResponseEntity<UtilsResponse<T>> getSuccessTarget(ResponseEnum responseEnum, T data) {
		UtilsResponse<T> response = new UtilsResponse<T>().setResponseCode(responseEnum.getCode())
				.setResponseMessage(responseEnum.getMessage()).setData(data);

		return ResponseEntity.ok(response);
	}

	/**
	 * Returns successful encrypted response for controllers with method return holders
	 * @param payload: the payload
	 * @return the response
	 */
	public ResponseEntity<String> getSuccessfulControllerResponseEncrypt(Object payload){


		try {
			return ResponseEntity.ok().body(
					jsonUtils.encryptObjectToString(new UtilsResponse<>()
							.setResponseCode(ResponseEnum.SUCCESS.getCode())
							.setResponseMessage(ResponseEnum.SUCCESS.getMessage())
							.setData(payload)
					));
		}catch (Exception  e){
			log.info("Couldn't encrypt response payload");
			log.error(e.getMessage(), e);
			throw new NrhException(ResponseEnum.ERROR, "Error occurred while encrypting payload");
		}
	}

	/**
	 * Returns successful encrypted response for controllers with method return holders
	 * @param payload: the payload
	 * @return the response
	 */
	public ResponseEntity<String> getSuccessfulControllerResponseEncrypt(UtilsResponse payload){


		try {

			return ResponseEntity.ok().body(jsonUtils.encryptObjectToString(payload));
		}catch (Exception  e){
			log.info("Couldn't encrypt response payload");
			log.error(e.getMessage(), e);
			throw new NrhException(ResponseEnum.ERROR, "Error occurred while encrypting payload");
		}
	}



	/**
	 * Returns successful encrypted response for controllers with method return holders
	 * @param payload: the payload
	 * @return the response
	 */
	public ResponseEntity<String> getSuccessfulControllerResponseEncryptPlainObject(Object payload){


		try {
			return ResponseEntity.ok().body(
					jsonUtils.encryptObjectToString(payload)
			);
		}catch (Exception  e){
			log.info("Couldn't encrypt response payload");
			log.error(e.getMessage(), e);
			throw new NrhException(ResponseEnum.ERROR, "Error occurred while encrypting payload");
		}
	}



	/**
	 * Used to throw appropriate nrh exception upon throwing Exception
	 *
	 * @param e: the exception object
	 */
	public static void throwNrhExceptionHelper(Exception e) {

		String message = (e instanceof NrhException) ? e.getMessage() : ResponseEnum.ERROR.getMessage();
		ResponseEnum responseEnum = (e instanceof NrhException) ? ((NrhException) e).getResponseEnum()
				: ResponseEnum.ERROR;

		throw new NrhException(responseEnum, message);
	}

	public static void throwNrhExceptionHelper(Exception e, String msg) {
		String message = (e instanceof NrhException) ? e.getMessage() : msg;
		ResponseEnum responseEnum = (e instanceof NrhException) ? ((NrhException) e).getResponseEnum()
				: ResponseEnum.ERROR;

		throw new NrhException(responseEnum, message);
	}

	public static void throwNrhExceptionByResponseCode(String responseCode, String responseMessage) {
		if (StringUtils.isEmptyBlank(responseCode))
			throw new NrhException(ResponseEnum.API_PROCESSING_ERROR);

		if (!Objects.equals("000", responseCode))
			throw new NrhException(responseCode, responseMessage);
	}

}
