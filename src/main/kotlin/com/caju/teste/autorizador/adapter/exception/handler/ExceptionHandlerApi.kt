package com.caju.teste.autorizador.adapter.exception.handler

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.web.bind.annotation.ControllerAdvice

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ExceptionHandlerApi {

}
