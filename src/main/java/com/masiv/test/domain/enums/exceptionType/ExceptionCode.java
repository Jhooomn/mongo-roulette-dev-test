package com.masiv.test.domain.enums.exceptionType;

public enum ExceptionCode {
    UNCONTROLLED("AFE-001", "EXCEPCION_NO_CONTROLADA"),
    BUSINESS("AFE-002", "EXCEPCION_NEGOCIO"),
    TECHNICAL("AFE-003", "EXCEPCION_TECNICA"),
    DATA_ESTRUCTURE("AFE-005", "ESTRUCTURA_DATO_NO_PROCESABLE"),
    INACTIVE_FUNCTION("AFE-006","INACTIVE_FUNCTION"),
    PARSE_MESSAGE("AFE-007","PARSE_INPUT_DOCUMENT_MESSAGE"),
    MALFORMED_MESSAGE("AFE-008","MALFORMED_DOCUMENT_MESSAGE"),
    UBL_ATTACHMENT_ELECTRONIC_DOCUMENT("AFE-009","UBL_ATTACHMENT_ELECTRONIC_DOCUMENT"),
    PROCESSOR_ELECTRONIC_DOCUMENT("AFE-010","PROCESSOR_ELECTRONIC_DOCUMENT_MESSAGE"),
    EVENT_TYPE("AFE-011","NULL_OR_EMPTY_EVENT_TYPE"),
    LOADING_EVENT_PROCESSOR("AFE-012","NOT_ALLOCATED_EVENT_PROCESSOR"),
    UNPARSEABLE_INVOICE_XML_DOCUMENT("AFE-013","UNPARSEABLE_INVOICE_XML_DOCUMENT");

    private String code;
    private String type;

    ExceptionCode(String code, String type) {
        this.code = code;
        this.type = type;
    }

    public String getCodigo() {
        return code;
    }

    public String getType() {
        return type;
    }

}
