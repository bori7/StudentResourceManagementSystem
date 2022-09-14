package com.ecobank.srms.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/* @author sa_oladipo created on 5/18/22 */
@Slf4j
public class TokenUtils {


    @Data
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AdminToken {


        /**
         {
         "aud": "api://f6c04f4a-de38-4842-8ae4-76e38b03ef72",
         "iss": "https://sts.windows.net/2ad72c11-ca14-4743-95d6-fbbe0f001056/",
         "iat": 1652891087,
         "nbf": 1652891087,
         "exp": 1652896579,
         "acr": "1",
         "aio": "ASQA2/8TAAAA5e8y24OivtdJ1Ab9KenESe8gCkTroYEMMHReaYzS0KY=",
         "amr": [
         "pwd"
         ],
         "appid": "f6c04f4a-de38-4842-8ae4-76e38b03ef72",
         "appidacr": "1",
         "family_name": "Lawal",
         "given_name": "Babatunde",
         "ipaddr": "41.76.195.201",
         "name": "Babatunde Lawal",
         "oid": "23b82327-eb21-4163-aaaa-c701cbed28a4",
         "rh": "0.AS8AESzXKhTKQ0eV1vu-DwAQVkpPwPY43kJIiuR244sD73IvANA.",
         "scp": "tasks.read",
         "sub": "oHCySA0aP5EqjcpwREWWpL84suNZYZ7aM_tnsEhMdoY",
         "tid": "2ad72c11-ca14-4743-95d6-fbbe0f001056",
         "unique_name": "blawal@mariofc.onmicrosoft.com",
         "upn": "blawal@mariofc.onmicrosoft.com",
         "uti": "FUBBYYxJakSmuGs4-eAcAA",
         "ver": "1.0"
         }
         */
        private String aud;
        private String iss;
        private Long exp;
        private String aio;
        private String family_name;
        private String given_name;
        private String ipaddr;
        private String name;
        private String sub;
        private String tid;
        private String unique_name;
        private String upn;
        private String uti;
    }


    /**
     * Returns an objectified representation of decoded token
     * @param token: the token {@link String}
     * @return the token object {@link AdminToken}
     */
    public static AdminToken getTokenBody(String token){
        DecodedJWT decodedJWT = JWT.decode(token);

        Map<String, Claim> claimMap = decodedJWT.getClaims();

        try {
            return new AdminToken()
                    .setAud(claimMap.get("acr").asString())
                    .setIss(claimMap.get("iss").asString())
                    .setExp(claimMap.get("exp").asLong())
                    .setAio(claimMap.get("aio").asString())
                    .setFamily_name(claimMap.get("family_name").asString())
                    .setGiven_name(claimMap.get("given_name").asString())
                    .setIpaddr(claimMap.get("ipaddr").asString())
                    .setName(claimMap.get("name").asString())
                    .setSub(claimMap.get("sub").asString())
                    .setTid(claimMap.get("tid").asString())
                    .setUnique_name(claimMap.get("unique_name").asString())
                    .setUpn(claimMap.get("upn").asString())
                    .setUti(claimMap.get("uti").asString());

        }catch (Exception e){
            log.error("Couldn't extract token");
            log.error(e.getMessage(), e);
            return  null;
        }
    }


    /**
     * Returns the userid embedded in a token after decode
     * @param token: the token {@link String}
     * @return the userid from the decoded token {@link String}
     */
    public static String getUserIdFromToken(String token) {

        AdminToken adminToken= getTokenBody(token);
        if(adminToken == null) return null;

        String userId = adminToken.sub;
        return (StringUtils.isBlank(userId)) ? null : userId.substring(userId.lastIndexOf(":"));
    }


    /**
     * Returns the userid embedded in a token after decode
     * @param token: the token object {@link AdminToken}
     * @return the userid from the decoded token {@link String}
     */
    public static String getUserIdFromToken(AdminToken token) {

        String userId = token.sub;

        return userId.substring(userId.lastIndexOf(":")+1);
    }


    /*public static void main(String [] args){
        AdminToken adminToken = TokenUtils.getTokenBody("eyJ0eXAiOiJKV1QiLCJub25jZSI6IllnT0FFdEFFRHFUclZxWDdhMkZUWDlOLUpWTkpIczlnUWVpNkpLWEtldDQiLCJhbGciOiJSUzI1NiIsIng1dCI6ImpTMVhvMU9XRGpfNTJ2YndHTmd2UU8yVnpNYyIsImtpZCI6ImpTMVhvMU9XRGpfNTJ2YndHTmd2UU8yVnpNYyJ9.eyJhdWQiOiJodHRwczovL2dyYXBoLm1pY3Jvc29mdC5jb20iLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC8yYWQ3MmMxMS1jYTE0LTQ3NDMtOTVkNi1mYmJlMGYwMDEwNTYvIiwiaWF0IjoxNjUyODk5ODA0LCJuYmYiOjE2NTI4OTk4MDQsImV4cCI6MTY1MjkwNDc4NSwiYWNjdCI6MCwiYWNyIjoiMSIsImFpbyI6IkUyWmdZSGc5UjlMaU0xL3B3bC85b25ONi9sZzVYMThrZElqcG1KTFJ2Z2JXUXVHRWU3c0EiLCJhbXIiOlsicHdkIl0sImFwcF9kaXNwbGF5bmFtZSI6Ik1vYmlsZSBBcHAgNS4wIEFkbWluIFBvcnRhbCBTUEEiLCJhcHBpZCI6IjcxN2U0YjJhLWMxMzctNDgxNy05MWE3LTkzMDE1NTNlYTcyMSIsImFwcGlkYWNyIjoiMCIsImZhbWlseV9uYW1lIjoiV2hpdGUiLCJnaXZlbl9uYW1lIjoiUHJpbmNlIiwiaWR0eXAiOiJ1c2VyIiwiaXBhZGRyIjoiNDEuNzYuMTk1LjIwMSIsIm5hbWUiOiJQcmluY2UgV2hpdGUiLCJvaWQiOiIyMDBlNjBiNC00MTM4LTRjZDEtYWNkMy00ODM0Nzk4MzdhNTQiLCJwbGF0ZiI6IjUiLCJwdWlkIjoiMTAwMzIwMDE5NUFBQzJEMyIsInJoIjoiMC5BUzhBRVN6WEtoVEtRMGVWMXZ1LUR3QVFWZ01BQUFBQUFBQUF3QUFBQUFBQUFBQXZBTUkuIiwic2NwIjoib3BlbmlkIHByb2ZpbGUgVXNlci5SZWFkIGVtYWlsIiwic3ViIjoiaDBROWxMQm00Szljd2xfN3pCYmtmd09DTDNxM1lDdlZtby01M3E5SEZIOCIsInRlbmFudF9yZWdpb25fc2NvcGUiOiJBRiIsInRpZCI6IjJhZDcyYzExLWNhMTQtNDc0My05NWQ2LWZiYmUwZjAwMTA1NiIsInVuaXF1ZV9uYW1lIjoiUHJpbmNlQG1hcmlvZmMub25taWNyb3NvZnQuY29tIiwidXBuIjoiUHJpbmNlQG1hcmlvZmMub25taWNyb3NvZnQuY29tIiwidXRpIjoiRERDS005ZVBqRTZBdWVySHpWd2ZBQSIsInZlciI6IjEuMCIsIndpZHMiOlsiYjc5ZmJmNGQtM2VmOS00Njg5LTgxNDMtNzZiMTk0ZTg1NTA5Il0sInhtc19zdCI6eyJzdWIiOiJfVEtsR3dZM2ZtQmFlZjg0MXNSOWM5UFRYRkI4MEFZYXJMa1VNckVHaHlzIn0sInhtc190Y2R0IjoxNjI5MzI3MzIzfQ.BnV8SANgQ_g4JOv0TH3p8nXuEgHk4L-pgUMw0n3BZsGuwP1a5-3eUcl_uV14Kv2Aazxdt4dp3hwVwnb-3wV5qvIKNVdRI9cwPMHm6lQ73_H4jCG2mCPCLFhxemQfEnlQ9VAsXrN__LvArdbLtGDD34VFtDTLkZuUhpOEB1X06uIGeDWuqzPej8Y7aCNT4xbBPDNiFHmDp_Sn4s-cOuyxi0HBStwvL8039bdrYm3tyvIPEgzc5omkXpGnIPgO5lUEIs951IJhvjMtwggQ2iQNhPpT5Nrk1z4pFJV2hml_kxgL-j7wR1DwBVyNIxWIyR5WhwdgW9cILjaeQGqVuZ7jYw");

        System.out.println(adminToken);
        System.out.println(TokenUtils.getUserIdFromToken(adminToken));

    }*/


}
