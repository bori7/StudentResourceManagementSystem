package com.ecobank.srms.utils;


import com.ecobank.srms.exceptions.CustomAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* @author sa_oladipo created on 5/17/22 */


@Service
@Slf4j
public class Utils {

	private static Logger logger = LoggerFactory.getLogger(Utils.class);
	private static final String LOCALHOST_IPV4 = "127.0.0.1";
	private static final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
	private static final String UNKNOWN = "unknown";

	public static String getClientIp(HttpServletRequest request) {
//
		String cliIPAddress = request.getHeader("X-Forward-For");
		if (!org.springframework.util.StringUtils.hasLength(cliIPAddress) || UNKNOWN.equals((cliIPAddress))) {
			cliIPAddress = request.getHeader("Proxy-Client-IP");
		}
		if (!org.springframework.util.StringUtils.hasLength(cliIPAddress) || UNKNOWN.equals((cliIPAddress))) {
			cliIPAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (!org.springframework.util.StringUtils.hasLength(cliIPAddress) || UNKNOWN.equals((cliIPAddress))) {
			cliIPAddress = request.getRemoteAddr();
			if ("127.0.0.1".equals(cliIPAddress) || "0:0:0:0:0:0:0:1".equals(cliIPAddress)) {
				try {
					InetAddress inetAddress = InetAddress.getLocalHost();
					cliIPAddress = inetAddress.getHostAddress();
				} catch (UnknownHostException e) {
					logger.error(e.getMessage());
				}
			}
		}
		if (!org.springframework.util.StringUtils.hasLength(cliIPAddress) && cliIPAddress.length() > 15 && cliIPAddress.indexOf(",") > 0) {
			cliIPAddress = cliIPAddress.substring(0, cliIPAddress.indexOf(","));
		}
		return cliIPAddress;

//		 String remoteAddr = "";
//		 if (request != null) {
//		 remoteAddr = request.getHeader("X-FORWARDED-FOR");
//		 if (remoteAddr == null || "".equals(remoteAddr)) {
//		 remoteAddr = request.getRemoteAddr();
//		 }
//
//		 }
//		 return remoteAddr;
	}


	public static boolean stringIsBlank(String text){

		return text == null || text.isEmpty();
	}

	/**
	 * Gets token claims for auth token
	 * @param request: the request body
	 * @return the response
	 */
	public static TokenUtils.AdminToken getTokenClaims(HttpServletRequest request){

		if(StringUtils.isBlank(request.getHeader("Authorization")))
			throw new CustomAuthenticationException("Invalid user");

		try {
			return TokenUtils.getTokenBody(getAccessTokenFromServletRequest(request));
		}catch (Exception e){
			log.error(e.getMessage(), e);
			throw new CustomAuthenticationException("Invalid user");
		}
	}


	/**
	 * Gets access token from servlet request
	 * @param request: the http servlet request
	 * @return the response
	 */
	public static  String getAccessTokenFromServletRequest(HttpServletRequest request){

		try {
			return request.getHeader("Authorization").replace("Bearer ", "");
		}catch (Exception e){
			log.error(e.getMessage(), e);
			throw new CustomAuthenticationException("Invalid user");
		}
	}


	public static String getMessageFromOracleDb(String message){

		if(stringIsBlank(message))
			return "Operation Failed!";

		if(!message.contains(":"))
			return message;

		return message.substring(message.lastIndexOf(":")+1).trim();
	}


	public static String getValidUserFromRequest(String userId, HttpServletRequest httpServletRequest){

		return (Utils.stringIsBlank(userId)) ? Utils.getTokenClaims(httpServletRequest).getUnique_name() : userId;
	}


	public static boolean isValidDbResponse(String code){

		return code.equalsIgnoreCase("0") || code.equalsIgnoreCase("00") || code.equalsIgnoreCase("000");
	}


//	public String getAffiliateAccountEnquiryUrl(String affiliate) {
//
//		String accountEnquiryUrl = "";
//
//		logger.info("affiliate" + affiliate);
//
//		try {
//
//			String clusterName = env.getProperty(affiliate.toUpperCase());
//			System.out.println(clusterName + "clustername");
//			logger.info(clusterName + "clustername");
////					String accountEnquiryUrlClusterPrefix = env.getRequiredProperty(clusterName);
//			String accountEnquiryUrlClusterPropertyName = clusterName.concat(".CLUSTER");
//			System.out.println(accountEnquiryUrlClusterPropertyName + "      accountEnquiryUrlClusterPropertyName");
//			logger.info(accountEnquiryUrlClusterPropertyName + "      accountEnquiryUrlClusterPropertyName");
//			accountEnquiryUrl = env.getProperty(accountEnquiryUrlClusterPropertyName);
//			return accountEnquiryUrl;
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			e.printStackTrace();
//		}
//		return accountEnquiryUrl;
//	}
}
