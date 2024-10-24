package com.caju.teste.autorizador.config.security

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*

@Service
class BasicAuthFilter : OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val headerAuthorization = request.getHeader("Authorization")

        if (headerAuthorization == null || !headerAuthorization.startsWith("Basic ")) {
            filterChain.doFilter(request, response)
            return
        }
        val basicToken = headerAuthorization.substring(BASIC_LENGTH)
        val basicTokenDecoded = Base64.getDecoder().decode(basicToken)

        val basicTokenValue = String(basicTokenDecoded)

        val basicAuthsSplit = basicTokenValue.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        if (basicAuthsSplit[0] == USERNAME && basicAuthsSplit[1] == PASSWORD) {
            val authToken = UsernamePasswordAuthenticationToken(basicAuthsSplit[0], null, null)
            SecurityContextHolder.getContext().authentication = authToken
        }
        filterChain.doFilter(request, response)
    }

    companion object {
        private const val BASIC_LENGTH = 6
        private const val USERNAME = "usuario"
        private const val PASSWORD = "123456"
    }
}
