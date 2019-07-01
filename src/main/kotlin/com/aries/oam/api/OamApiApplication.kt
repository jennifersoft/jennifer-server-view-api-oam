package com.aries.oam.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import com.aries.extension.starter.PluginStarter
import org.springframework.web.servlet.config.annotation.InterceptorRegistry

@SpringBootApplication(scanBasePackages =  [ "com.aries.view", "com.aries.oam" ])
class OamApiApplication: WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(PluginStarter()).addPathPatterns("/plugin/**")
    }
}

fun main(args: Array<String>) {
    runApplication<OamApiApplication>(*args)
}
