/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author eldho
 */
public class BadRequestException extends ResponseStatusException {

    public BadRequestException() {
        super(BAD_REQUEST);
    }

    public BadRequestException(String reason) {
        super(BAD_REQUEST, reason);
    }

    public BadRequestException(String reason, Throwable cause) {
        super(BAD_REQUEST, reason, cause);
    }
}
