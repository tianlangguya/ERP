package com.mxp.erp.exception;

public class ERPException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public ERPException() {
    super();
  }

  public ERPException(String msg) {
    super(msg);
  }

  public ERPException(Exception e) {
    super(e);
  }

  public ERPException(String msg, Exception e) {
    super(msg, e);
  }

}
