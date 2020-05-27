package com.siit.sbnz.sbnztim14.exceptions;

import lombok.NoArgsConstructor;

/**
 * This exception is thrown in case the access to the requested resource or operation is forbidden for some reason.
 */
@NoArgsConstructor
public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message) {
        super(message);
    }
}
