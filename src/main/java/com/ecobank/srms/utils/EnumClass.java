package com.ecobank.srms.utils;

/* @author sa_oladipo created on 6/10/22 */
public class EnumClass {

    public enum SearchParamEnum{
        MODULE,
        USERS,
        AFFILIATE
    }

    public enum ModuleSearchParamEnum{
        DOC_UPLOAD,
        TRANSACTION,
        USER,
        ROLE,
        AFFILIATE,
        TXN_TYPES,
        TXN_DOC
    }

    public enum UsersSearchParamEnum {
        ALL,
        user_id
    }

    public enum AffiliateSearchParamEnum {
        ALL,
        aff_code
    }
}
